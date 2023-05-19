import { Component } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  hasSent: boolean = false;

  constructor(private authService: AuthService,
              private router: Router) {
  }

  ngOnInit(): void {
    if (this.authService.username.getValue() != null) {
      this.router.navigate(['/api/cars']);
    }
  }

  submit() {
    let name = (document.getElementById('name') as HTMLInputElement).value.trim();
    let password = (document.getElementById('password') as HTMLInputElement).value.trim();
    let violations = '';
    if (name.length == 0 || name.length > 50) {
      violations = violations.concat('Nickname must be 1-50 chars\n');
    }
    if (password.length == 0 || password.length > 256) {
      violations = violations.concat('Password must be 1-256 chars\n');
    }
    if (violations.length == 0) {
      this.sendRequest(name, password);
    }
  }

  private sendRequest(name: string, password: string) {
    this.hasSent = true;
    this.authService.loginUser({ username: name, password: password })
      .subscribe({
        next: value => {
          this.router.navigate(['/main']);
        }
      });
  }
}
