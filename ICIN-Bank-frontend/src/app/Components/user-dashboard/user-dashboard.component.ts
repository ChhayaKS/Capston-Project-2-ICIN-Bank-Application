import { DataServiceService } from './../../Service/data-service.service';
import { Component, OnInit } from '@angular/core';
import { Router ,ActivatedRoute} from '@angular/router';
import { Users } from 'src/app/Model/users';
import { UsersService } from 'src/app/Service/users.service';


@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent implements OnInit{
  users:Users;
  userName: string = '';
  userId: string;

  constructor(private dataService: DataServiceService, private router:Router,private route :ActivatedRoute,private user:UsersService) { }

  ngOnInit(): void {
     this.userName = this.dataService.getUser().userName;
  }

  onLogout() {
    this.dataService.setIsSafe(false);
    this.dataService.setUser(null);
    this.router.navigate(['']);
  }

}
