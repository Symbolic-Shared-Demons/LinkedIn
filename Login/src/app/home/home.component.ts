import { Component } from '@angular/core';

import { User } from '@app/_models';
import { AccountService } from '@app/_services';
import { Router } from '@angular/router';

@Component({ templateUrl: 'home.component.html' })
export class HomeComponent {
    user: User;
    router: Router;

    constructor(private accountService: AccountService) {
        this.user = this.accountService.userValue;
    }
}