import { DadosClienteLogado } from './../service/cliente/dados-cliente-logado.model';
import { Component, OnInit } from '@angular/core';
import { Cliente } from '../cadastro/model/cliente.model';
import { ClienteService } from '../service/cliente/cliente.service';
import { HttpErrorResponse } from '@angular/common/http';
import { ContaService } from '../service/conta/conta.service';
import { AutenticacaoService } from '../service/autenticacao/autenticacao.service';

@Component({
  selector: 'app-dados-pessoais',
  standalone: false,
  templateUrl: './dados-pessoais.component.html',
  styleUrls: ['./dados-pessoais.component.css'],
})
export class DadosPessoaisComponent implements OnInit {
  cliente: Cliente = {
    clienteId: '',
    nome: '',
    idade: 0,
    email: '',
    contaRegistroDto: { agencia: '' },
  };

  displayedColumns: string[] = ['nome', 'idade', 'email'];

  dadosClienteLogado: DadosClienteLogado ={
    clienteId: '',
    contaId: '',
  };

  clienteId: string = '';
  username: string = '';

  constructor(
    private contaService: ContaService,
    private clienteService: ClienteService,
    private autenticacaoService: AutenticacaoService
  ) {}

  ngOnInit(): void {
    this.username = this.autenticacaoService.getUsernameFromToken();

    if (this.username) {
      this.obtemDadosClienteLogado(this.username);
      console.log('Username obtido do token:', this.username);
    } else {
      console.error('Username não encontrado no token JWT!');
    }

  }


  buscarCliente(clientId: string): void {
    this.clienteService.buscarCliente(clientId).subscribe({
      next: (res: Cliente) => {
        console.log('Cliente carregado:', res);
        this.cliente = res;
      },
      error: (err: HttpErrorResponse) => {
        console.error('Erro ao buscar cliente:', err);
      },
    });
  }

  obtemDadosClienteLogado(username: string): void {
    this.clienteService.obtemDadosClienteLogado(username).subscribe({
      next: (res: DadosClienteLogado) => {
        console.log('Dados do cliente logado:', res);
        this.dadosClienteLogado = res;
        this.buscarCliente(this.dadosClienteLogado.clienteId);
      },
      error: (err: HttpErrorResponse) => {
        console.error('Erro ao buscar cliente:', err);
      },
    });
  }


}
