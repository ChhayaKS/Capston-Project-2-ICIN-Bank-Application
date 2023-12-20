import { UsersService } from 'src/app/Service/users.service';
import { DataServiceService } from './../../Service/data-service.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit{

  userId: string = '';
  password: string = '';
  accountNumber: string = '';
  userName: string = '';

  constructor(private dataService: DataServiceService, private userService: UsersService) { }

  ngOnInit(): void {
    let user = this.dataService.getUser();
    this.userId = user.userId;
    this.password = user.password;
    this.accountNumber = user.accountNumber;
    this.userName = user.userName;
  }

  updateLoginPassword() {
    if (this.password === '') {
      alert('Please enter a new password!');
      return;
    }
    this.userService
      .updateLoginPassword(this.password, this.accountNumber)
      .subscribe(
        message => {
          let user = this.dataService.getUser();
          user.password = this.password
          this.dataService.setUser(user);
          alert(message.message)
        },
        error => console.log(error)
      )
  }

}
