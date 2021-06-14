import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-panel',
  templateUrl: './user-panel.component.html',
  styleUrls: ['./user-panel.component.less']
})

export class UserPanelComponent implements OnInit {

  number = 5;

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  signOut(): void {
    this.router.navigate([''])
  }
}



