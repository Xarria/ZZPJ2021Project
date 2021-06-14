import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from "../services/user.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.less']
})
export class LoginComponent implements OnInit {

  login = '';
  password = '';

  constructor(private router: Router, private userService: UserService) { }

  ngOnInit(): void {
  }

  signIn(): void {
    this.userService.auth(this.login, this.password).subscribe(
      (response: string) => {
        this.userService.setSession(response);
          this.router.navigateByUrl('/user');
        this.password = '';
        this.login = '';
      }
    );
  }

}
