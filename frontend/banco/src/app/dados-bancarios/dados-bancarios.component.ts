import { ContaService } from './../service/conta/conta.service';
import { Component, OnInit } from '@angular/core';
import { Conta } from '../cadastro/model/conta.model';
import { HttpErrorResponse } from '@angular/common/http';
import { ClienteService } from '../service/cliente/cliente.service';
import { AutenticacaoService } from '../service/autenticacao/autenticacao.service';
import { DadosClienteLogado } from '../service/cliente/dados-cliente-logado.model';

@Component({
  selector: 'app-dados-bancarios',
  standalone: false,
  templateUrl: './dados-bancarios.component.html',
  styleUrls: ['./dados-bancarios.component.css'],
})
export class DadosBancariosComponent implements OnInit {
  conta: Conta = {
    contaId: '',
    numeroConta: '',
    digitoConta: '',
    agencia: '',
    dataAbertura: new Date(),
    transacoes: [],
  };

  contaId: string = '';
  username: string = '';

  displayedColumns: string[] = ['numeroConta', 'digitoConta', 'agencia'];

  dadosClienteLogado: DadosClienteLogado ={
    clienteId: '',
    contaId: '',
  }

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

  buscarConta(contaId: string): void {
    this.contaService.buscarConta(contaId).subscribe({
      next: (res: Conta) => {
        console.log('Conta carregada:', res);
        this.conta = res;
      },
      error: (err: HttpErrorResponse) => {
        console.error('Erro ao buscar conta:', err);
      },
    });
  }

  obtemDadosClienteLogado(username: string): void {
    this.clienteService.obtemDadosClienteLogado(username).subscribe({
      next: (res: DadosClienteLogado) => {
        console.log('Dados do cliente logado:', res);
        this.dadosClienteLogado = res;
        this.buscarConta(this.dadosClienteLogado.contaId);
      },
      error: (err: HttpErrorResponse) => {
        console.error('Erro ao buscar cliente:', err);
      },
    });
  }


}
