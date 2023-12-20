import { Router } from '@angular/router';
import { UsersService } from './../../Service/users.service';
import { Component, OnInit } from '@angular/core';
import { Users } from 'src/app/Model/users';
import { DataServiceService } from 'src/app/Service/data-service.service';

@Component({
  selector: 'app-user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.css']
})
export class UserRegistrationComponent implements OnInit{

  firstName: string = '';
  lastName: string = '';
  accountNumber: string = '';
  userId: string = '';
  password1: string = '';
  password2: string = '';

  constructor(
    private userService: UsersService,
    private router: Router,
    private dataService: DataServiceService
  ) { }

  ngOnInit(): void {
  }

  generateID() {
    if (this.firstName === '' ||
      this.lastName === '' ||
      this.accountNumber === '') {
      alert('Please Enter Above Credentials');
      return;
    }
    let temp = this.firstName+this.lastName+this.accountNumber;
    let result = [];
    for(let i = 0; i<8; i++) {
      result.push(temp.charAt(Math.floor(Math.random() * temp.length)));
    }
    this.userId = result.join('');
  }

  register() {
    if (this.firstName === '' ||
      this.lastName === '' ||
      this.accountNumber === '' ||
      this.userId === '' ||
      this.password1 === '' || this.password2 === '') {
      alert('Please enter the required details!');
      return;
    }

    if (!(this.password1 === this.password2)) {
      alert('Entered login passwords do not match!');
      return;
    }

    let user:Users = {
      accountNumber: this.accountNumber,
      userName: this.firstName + ' ' + this.lastName,
      userId: this.userId,
      password: this.password1,
      accountIsBlocked: 0
    } as Users

    this.userService.putUser(user).subscribe(
      message => {
        console.log(message)
        if (message.message === 'Successfully registered!') {
          let mySuccessMessage = message.message + '\n' + 'Press OK to login and enjoy the services.'
          if (confirm(mySuccessMessage)) {
            
              this.userService.getUser(this.userId).subscribe(user=>
                {
                this.dataService.setUser(user);
                 this.router.navigate(['user-dashboard']);

                }) 
            // this.router.navigate(['user-dashboard']);
          }
        } else {
          alert(message.message);
        }
      },
      error => console.log(error),
      () => {
        this.accountNumber = '';
        this.firstName = '';
        this.lastName = '';
        this.userId = '';
        this.password1 = '';
        this.password2 = '';
      }
    )

  }

}
