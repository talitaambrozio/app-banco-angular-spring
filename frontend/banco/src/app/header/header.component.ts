import { Component, OnInit } from '@angular/core';
import { AutenticacaoService } from '../service/autenticacao/autenticacao.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  standalone: false,
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit{

  showLogoutButton: boolean = true;

  constructor(
    private autenticacaoService: AutenticacaoService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.verificarVisibilidadeBotaoLogout();
  }

  logout() {
    this.autenticacaoService.logout();
    this.router.navigate(['/login']);
  }

  verificarVisibilidadeBotaoLogout(): void {
    const rotaAtual = this.router.url;

    if (rotaAtual === '/login' || rotaAtual === '/cadastro') {
      this.showLogoutButton = false;
    } else {
      this.showLogoutButton = true;
    }
  }
}
