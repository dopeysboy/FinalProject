import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';
import { ModalDismissReasons, NgbModal, NgbDropdownModule } from '@ng-bootstrap/ng-bootstrap';
import { Expense } from 'src/app/models/expense';
import { Income } from 'src/app/models/income';
import { IncomeService } from 'src/app/services/income.service';
import { ExpenseService } from 'src/app/services/expense.service';

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

  expenses: Expense[] = [];
  incomes: Income[] = [];

  oldPassword: string = '';
  newPassword1: string = '';
  newPassword2: string = '';

  submittable: boolean = false;

  closeResult = '';

  constructor(
    private userService: UserService,
    private authService: AuthService,
    private incomeService: IncomeService,
    private expenseService: ExpenseService,
    private modalService: NgbModal
    ) { }

  ngOnInit(): void {
    this.getUser();
    this.getExpenses();
    this.getIncomes();
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
      },
      error: (problem) => {
        console.error('ProfileComponent.getIncomes(): Error getting Incomes');
      }
    });
  }

  disable() {
    this.userService.disable(this.loggedInUser).subscribe({
      next: () => {
        console.log('Deleted');
        this.authService.logout();
      },
      error (problem) {
        console.error('ProfileComponent.disable(): Problem disabling user');
      }
    });
  }

  pwordModalSubmit(oldPassword: string, newPassword1: string, newPassword2: string) {
    if (newPassword1 === newPassword2) {
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
      }
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
    this.editIncome = null;
    this.newExpense = null;
    this.newIncome = null;
  }

  setEditExpense(expense: Expense): void {
    this.editExpense = Object.assign({}, expense);
  }

  setEditIncome(income: Income): void {
    this.editIncome = Object.assign({}, income);
  }

  setNewExpense(): void {
    this.newExpense = new Expense();
  }

  setNewIncome(): void {
    this.newIncome = new Income();
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

  deleteExpense(expense: Expense) {
    if (expense.id) {
      this.expenseService.destroy(expense.id).subscribe({
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
          console.log('ProfileComponent.updateIncome(): Problem updating Income');
        }
      });
    }
  }

  addExpense(expense: Expense) {
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

  addIncome(income: Income) {
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

}
