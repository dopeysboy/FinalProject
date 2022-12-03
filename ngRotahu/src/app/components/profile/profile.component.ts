import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';
import { ModalDismissReasons, NgbModal, NgbDropdownModule } from '@ng-bootstrap/ng-bootstrap';
import { Expense } from 'src/app/models/expense';
import { Income } from 'src/app/models/income';
import { IncomeService } from 'src/app/services/income.service';
import { ExpenseService } from 'src/app/services/expense.service';
import { Debt } from 'src/app/models/debt';
import { DebtService } from 'src/app/services/debt.service';
import { Frequency } from 'src/app/models/frequency';
import { Category } from 'src/app/models/category';
import { CategoryService } from 'src/app/services/category.service';
import { FrequencyService } from 'src/app/services/frequency.service';
import { DebtLender } from 'src/app/models/debt-lender';
import { DebtType } from 'src/app/models/debt-type';
import { DebtLenderService } from 'src/app/services/debt-lender.service';
import { DebtTypeService } from 'src/app/services/debt-type.service';
import { ChartData, ChartEvent, ChartType } from 'chart.js';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  loggedInUser: User = new User();
  editExpense: Expense | null = null;
  newExpense: Expense | null = null;
  editIncome: Income | null = null;
  newIncome: Income | null = null;
  editDebt: Debt | null = null;
  newDebt: Debt | null = null;
  newDebtLender: DebtLender = new DebtLender();
  newDebtType: DebtType = new DebtType();
  newCategory: Category = new Category();
  newFrequency: Frequency = new Frequency();

  lenders : DebtLender[] = [];
  types : DebtType[] = [];
  expenses: Expense[] = [];
  incomes: Income[] = [];
  debts: Debt[] = [];
  frequencies: Frequency[] = [];
  categories: Category[] = [];

  passwordNotMatch: string = '';
  oldPassword: string = '';
  newPassword1: string = '';
  newPassword2: string = '';

  submittable: boolean = false;

  closeResult = '';

  constructor(
    private router: Router,
    private userService: UserService,
    private authService: AuthService,
    private incomeService: IncomeService,
    private expenseService: ExpenseService,
    private debtService: DebtService,
    private frequencyService: FrequencyService,
    private categoryService: CategoryService,
    private typeService : DebtTypeService,
    private lenderService: DebtLenderService,
    private modalService: NgbModal
    ) { }

  ngOnInit(): void {
    this.getUser();
    this.getExpenses();
    this.getIncomes();
    this.getDebts();
    this.getLenders();
    this.getTypes();
    this.getCategories();
    this.getFrequencies();
  }

  getUser() {
    this.authService.getLoggedInUser().subscribe({
      next: (user) => {
        this.loggedInUser = user;
      },
      error: (problem) => {
        console.error('ProfileComponent.getUser(): Error getting User');
      }
    });
  }

  getExpenses() {
    this.expenseService.index().subscribe({
      next: (expenses) => {
        this.expenses = expenses;
        this.generateChartData();
      },
      error: (problem) => {
        console.error('ProfileComponent.getExpenses(): Error getting Expenses');
      }
    });
  }

  getIncomes() {
    this.incomeService.index().subscribe({
      next: (incomes) => {
        this.incomes = incomes;
        this.generateChartData();
      },
      error: (problem) => {
        console.error('ProfileComponent.getIncomes(): Error getting Incomes');
      }
    });
  }

  getDebts() {
    this.debtService.index().subscribe({
      next: (debts) => {
        this.debts = debts;
        this.generateChartData();
      },
      error: (problem) => {
        console.error('ProfileComponent.getIncomes(): Error getting Incomes');
      }
    });
  }

  getFrequencies() {
    this.frequencyService.index().subscribe({
      next: (frequencies) => {
        this.frequencies = frequencies;
      },
      error: (problem) => {
        console.error('ProfileComponent.getFrequencies(): Error getting Frequencies');
      }
    });
  }

  getCategories() {
    this.categoryService.index().subscribe({
      next: (categories) => {
        this.categories = categories;
      },
      error: (problem) => {
        console.error('ProfileComponent.getCategories(): Error getting Categories');
      }
    });
  }

  getLenders() {
    this.lenderService.index().subscribe({
      next: (lenders) => {
        this.lenders = lenders;
      },
      error: (problem) => {
        console.error('ProfileComponent.getLenders(): Error getting Lenders');
      }
    });
  }

  getTypes() {
    this.typeService.index().subscribe({
      next: (types) => {
        this.types = types;
      },
      error: (problem) => {
        console.error('ProfileComponent.getTypes(): Error getting Types');
      }
    });
  }

  disable() {
    this.userService.disable(this.loggedInUser).subscribe({
      next: () => {
        console.log('Deleted');
        this.authService.logout();
        this.router.navigate(['/home'])
      },
      error (problem) {
        console.error('ProfileComponent.disable(): Problem disabling user');
      }
    });
  }

  pwordModalSubmit(oldPassword: string, newPassword1: string, newPassword2: string) {
    if (newPassword1 === newPassword2) {
      this.passwordNotMatch = "";
      let hashedPassword = this.authService.generateBasicAuthCredentials(this.loggedInUser.username, oldPassword);
      let realPassword = localStorage.getItem('credentials');
      if (hashedPassword === realPassword) {
        this.submittable = true;
        this.userService.changePassword(newPassword1).subscribe({
          next: (user) => {
            this.oldPassword = '';
            this.newPassword1 = '';
            this.newPassword2 = '';
            this.authService.login(this.loggedInUser.username, newPassword1).subscribe({
              next: () => {
                this.getUser();
              },
              error: () => {
                console.log('ProfileComponent.modalSubmit(): Problem logging in');
              }
            });
          },
          error: (problem) => {
            console.log('ProfileComponent.modalSubmit(): Problem changing password');
          }
        });
      } else {
        this.passwordNotMatch = "Incorrect Password. Try again.";
      }
    } else {
      this.passwordNotMatch = "Passwords don't match. Try again.";
    }
  }

  updateAccount() {
    this.userService.updateAccount(this.loggedInUser).subscribe({
      next: (user) => {
        console.log('updated');
        this.getUser();
      },
      error: (problem) => {
        console.log('ProfileComponent.update(): Problem updating Account');
      }
    });
  }

  open(content: any) {
		this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then(
			(result) => {
				this.closeResult = `Closed with: ${result}`;
			},
			(reason) => {
				this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
			},
		);
	}

	private getDismissReason(reason: any): string {
		if (reason === ModalDismissReasons.ESC) {
			return 'by pressing ESC';
		} else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
			return 'by clicking on a backdrop';
		} else {
			return `with: ${reason}`;
		}
	}

  showMainOptions() {
    this.editExpense = null;
    this.newExpense = null;
    this.editIncome = null;
    this.newIncome = null;
    this.editDebt = null;
    this.newDebt = null;
    this.newDebtLender = new DebtLender();
    this.newDebtType = new DebtType();
    this.newCategory = new Category();
    this.newFrequency = new Frequency();
  }

  setEditDebt(debt: Debt): void {
    this.editDebt = Object.assign({}, debt);
  }

  setEditExpense(expense: Expense): void {
    this.editExpense = Object.assign({}, expense);
  }

  setEditIncome(income: Income): void {
    this.editIncome = Object.assign({}, income);
  }

  setNewDebt(): void {
    this.newDebt = new Debt();
  }

  setNewExpense(): void {
    this.newExpense = new Expense();
  }

  setNewIncome(): void {
    this.newIncome = new Income();
  }

  updateDebt(debt: Debt) {
    this.debtService.update(debt).subscribe({
      next: (debt) => {
        console.log(debt);
        this.getDebts();
        this.showMainOptions();
      },
      error: (problem) => {
        console.log('ProfileComponent.updateDebt(): Problem updating Debt');
      }
    });
  }

  updateExpense(expense: Expense) {
    this.expenseService.update(expense).subscribe({
      next: (expense) => {
        console.log(expense);
        this.getExpenses();
        this.showMainOptions();
      },
      error: (problem) => {
        console.log('ProfileComponent.updateExpense(): Problem updating Expense');
      }
    });
  }

  updateIncome(income: Income) {
    this.incomeService.update(income).subscribe({
      next: (income) => {
        console.log(income);
        this.getIncomes();
        this.showMainOptions();
      },
      error: (problem) => {
        console.log('ProfileComponent.updateIncome(): Problem updating Income');
      }
    });
  }

  deleteDebt(debt: Debt) {
    if (debt.id) {
      this.debtService.destroy(debt.id).subscribe({
        next: (debt) => {
          console.log(debt);
          this.getDebts();
          this.showMainOptions();
        },
        error: (problem) => {
          console.log('ProfileComponent.deleteDebt(): Problem deleting Debt');
        }
      });
    }
  }

  deleteExpense(expense: Expense) {
    if (expense.id) {
      this.expenseService.destroy(expense.id).subscribe({
        next: (expense) => {
          console.log(expense);
          this.getExpenses();
          this.showMainOptions();
        },
        error: (problem) => {
          console.log('ProfileComponent.updateExpense(): Problem deleting Expense');
        }
      });
    }
  }

  deleteIncome(income: Income) {
    if (income.id) {
      this.incomeService.destroy(income.id).subscribe({
        next: (income) => {
          console.log(income);
          this.getIncomes();
          this.showMainOptions();
        },
        error: (problem) => {
          console.log('ProfileComponent.updateIncome(): Problem deleting Income');
        }
      });
    }
  }

  addDebt(debt: Debt, debtLender : DebtLender, debtType : DebtType){
    debt.debtLender = debtLender;
    debt.debtType = debtType;
    this.debtService.create(debt).subscribe({
      next: (createdDebt) => {
            this.getDebts();
            this.showMainOptions();
          },
          error: (problem) => {
            console.error('ProfileComponent.addDebt(): Error adding debt:');
            console.error(problem);
          }
        });
  }

  addExpense(expense: Expense, category: Category, frequency: Frequency) {
    expense.category = category;
    expense.frequency = frequency
    this.expenseService.create(expense).subscribe({
      next: (expense) => {
        console.log(expense);
        this.getExpenses();
        this.showMainOptions();
      },
      error: (problem) => {
        console.log('ProfileComponent.addExpense(): Problem adding Expense');
      }
    });
  }

  addIncome(income: Income, category: Category, frequency: Frequency) {
    income.category = category;
    income.frequency = frequency
    this.incomeService.create(income).subscribe({
      next: (income) => {
        console.log(income);
        this.getIncomes();
        this.showMainOptions();
      },
      error: (problem) => {
        console.log('ProfileComponent.addIncome(): Problem adding Income');
      }
    });
  }

  // Doughnut chart stuff -----------------------------------------------------------------

  public doughnutChartData: ChartData<'doughnut'> = {
    labels: [],
    datasets: []
  };

  chartLabels: string[] = [];
  debtData: number[] = [];
  incomeData: number[] = [];
  expenseData: number[] = [];

  generateChartData() {
    this.chartLabels = [];
    this.debtData = [];
    this.incomeData = [];
    this.expenseData = [];
    for (let debt of this.debts) {
      if (debt.name) {
        this.chartLabels.push(debt.name);
      } else {
        this.chartLabels.push(' ');
      }
      if (debt.currentBalance) {
        this.debtData.push(debt.currentBalance);
      } else {
        this.debtData.push(0);
      }
      this.incomeData.push(0);
      this.expenseData.push(0);
    }
    for (let income of this.incomes) {
      if (income.description) {
        this.chartLabels.push(income.description);
      } else {
        this.chartLabels.push(' ');
      }
      if (income.amount) {
        this.incomeData.push(income.amount);
      } else {
        this.incomeData.push(0);
      }
      this.debtData.push(0);
      this.expenseData.push(0);
    }
    for (let expense of this.expenses) {
      if (expense.description) {
        this.chartLabels.push(expense.description);
      } else {
        this.chartLabels.push(' ');
      }
      if (expense.amount) {
        this.expenseData.push(expense.amount);
      } else {
        this.expenseData.push(0);
      }
      this.debtData.push(0);
      this.incomeData.push(0);
    }
    this.doughnutChartData = {
      labels: this.chartLabels,
      datasets: [
        { data: this.debtData },
        { data: this.incomeData },
        { data: this.expenseData }
      ]
    };
  }

  public doughnutChartType: ChartType = 'doughnut';

  // events
  public chartClicked({ event, active }: { event: ChartEvent, active: {}[] }): void {
    console.log(event, active);
  }

  public chartHovered({ event, active }: { event: ChartEvent, active: {}[] }): void {
    console.log(event, active);
  }

}
