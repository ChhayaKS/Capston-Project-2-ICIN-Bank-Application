import { UsersService } from 'src/app/Service/users.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Users } from 'src/app/Model/users';

@Component({
  selector: 'app-admin-account-manage',
  templateUrl: './admin-account-manage.component.html',
  styleUrls: ['./admin-account-manage.component.css']
})
export class AdminAccountManageComponent implements OnInit{

  user:Users[]=[]
  users: Array<Users>;
  toShowUsers: Array<Users>;
  allowBack: boolean = false;
  allowNext: boolean = false;
  current: number;

  constructor(private userService: UsersService, private router: Router) { }

  ngOnInit(): void {
    // this.userService.getAllBlockedUser().subscribe( users => {
    //   console.log(users)
    //       if (users.length > 5) {
    //         this.allowNext = true;
    //       }
    //       this.users = users;
    //       this.current = 0;
    //       this.toShowUsers = this.users.slice(this.current, this.current + 5);
    //     }
    //   )
    this.getAll();
  }
getAll()
{
  this.userService.getAllBlockedUser().subscribe(users=>
    {
      users.forEach(data=>{
        this.user.push(data);
      })
    })
}
  unblock(userId: string) {
    this.userService.unblockUser(userId).subscribe( message => {
          alert(message.message)
          this.userService.getAllBlockedUser()
            .subscribe(
              users => {
                if (users.length > 5) {
                  this.allowNext = true;
                }
                this.users = users;
                this.toShowUsers = this.users.slice(this.current, this.current + 5);
              },
              error => console.log(error)
            )
        },
        error => console.log(error)
      )
  }

  onBack() {
    this.router.navigate(['admin-dashboard']);
  }

}
