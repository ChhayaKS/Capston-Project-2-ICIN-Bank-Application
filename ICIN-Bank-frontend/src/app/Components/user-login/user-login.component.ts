import { DataServiceService } from './../../Service/data-service.service';
import { UsersService } from './../../Service/users.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit{

  userId: string = '';
  password: string = '';
  currentLoginCount: number = 3;

  constructor(private router: Router, private userService: UsersService, private dataService: DataServiceService) { }

  ngOnInit(): void {
  }

  loginUser() {
    if(this.userId ==='' || this.password === '') {
      alert('Please enter your credentials!');
      return;
    }

    this.userService.checkUserByIdAndPassword(this.userId, this.password).subscribe(
      message => {
        if(message.message === 'success') {
          this.userService.getUser(this.userId).subscribe(
            user => {
              this.dataService.setUser(user);
              this.dataService.setIsSafe(true);
              this.router.navigate(['user-dashboard']);
            }
          )
        } else if(message.message === 'failure') {
          alert(`Your account was blocked in attempt for multiple wrong logins.\nPlease contact your nearest ICIN bank!`);
          return;
        } else if(message.message === 'no-user') {
          alert('Username is not registered yet!');
          return;
        } 
        else {
          if(this.currentLoginCount === 1) {
            this.userService.blockUser(this.userId)
            .subscribe(
              message => alert(message.message),
              error => console.log(error)
            )
            return;
          }
          this.currentLoginCount--;
          alert(`${message.message}\nYou have ${this.currentLoginCount} attempts left!`);
        }
      },
      error => console.log(error),
      () => {
        this.userId = '';
        this.password = '';
      }
    )
  }

}
