import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';
import { ModalDismissReasons, NgbDatepickerModule, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  oldPassword: string ='';
  newPassword1: string = '';
  newPassword2: string = '';

  submittable: boolean = false;

  closeResult = '';
  loggedInUser: User = new User();

  constructor(
    private userService: UserService,
    private authService: AuthService,
    private modalService: NgbModal
    ) { }

  ngOnInit(): void {
    this.getUser();
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

  disable() {
    this.userService.disable(this.loggedInUser).subscribe({
      next: () => {
        console.log('Deleted');
      },
      error (problem) {
        console.error('ProfileComponent.disable(): Problem disabling user');
      }
    });
  }



  modalSubmit(oldPassword: string, newPassword1: string, newPassword2: string) {
    if (newPassword1 === newPassword2) {
      let hashedPassword = this.authService.generateBasicAuthCredentials(this.loggedInUser.username, oldPassword);
      let realPassword = localStorage.getItem('credentials');
      if (hashedPassword === realPassword) {
        this.submittable = true;


      }
    }
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

}
