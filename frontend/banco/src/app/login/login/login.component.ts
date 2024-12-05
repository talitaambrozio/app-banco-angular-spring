import { Component } from '@angular/core';
import { AutenticacaoService } from 'src/app/service/autenticacao/autenticacao.service';
import { Router } from '@angular/router';
import { Login } from './login.model';

@Component({
  selector: 'app-login',
  standalone: false,
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  login: Login = {
    email: '',
    password: ''
  };

  constructor(
    private autenticacaoService: AutenticacaoService,
    private router: Router
  ) {}

  onSubmit(): void {
    const { email, password } = this.login;
    this.autenticacaoService.login(email, password).subscribe({
      next: (response) => {
        const token = response.token;
        if (token) {
          this.autenticacaoService.storeToken(token);
          console.log('Login bem-sucedido!');
          console.log('Token', token);


          this.router.navigate(['/']);
        }
      },
      error: (err) => {
        console.error('Erro ao fazer login:', err);
        alert('Erro ao fazer login. Verifique suas credenciais.');
      }
    });
  }
}
