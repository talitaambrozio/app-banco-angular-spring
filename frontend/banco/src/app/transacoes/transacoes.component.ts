import { CurrencyPipe } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

import { Conta } from '../cadastro/model/conta.model';
import { TransacaoService } from '../service/transacao/transacao.service';
import { ContaService } from './../service/conta/conta.service';
import { Transacao } from './model/transacao.model';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AutenticacaoService } from '../service/autenticacao/autenticacao.service';
import { ClienteService } from '../service/cliente/cliente.service';
import { DadosClienteLogado } from '../service/cliente/dados-cliente-logado.model';

@Component({
  selector: 'app-transacoes',
  standalone: false,
  templateUrl: './transacoes.component.html',
  styleUrls: ['./transacoes.component.css'],
  providers: [CurrencyPipe],
})
export class TransacoesComponent implements OnInit {
  transacao: Transacao = {
    transacaoId: '',
    dataTransacao: new Date(),
    tipoTransacao: '',
    valor: 0,
    descricao: '',
    conta: {
      contaId: '',
    } as Conta,
  };

  dadosClienteLogado: DadosClienteLogado ={
    clienteId: '',
    contaId: '',
  }

  contaId: string = '';
  numeroConta: string = '';
  username: string = '';

  constructor(
    private transacaoService: TransacaoService,
    private autenticacaoService: AutenticacaoService,
    private clienteService: ClienteService,
    private contaService: ContaService,
    private currencyPipe: CurrencyPipe,
    private snackBar: MatSnackBar
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
      next: (conta: Conta) => {
        this.numeroConta = conta.numeroConta;
        this.transacao.conta.contaId = contaId;
        console.log('Conta carregada:', conta);
      },
      error: (err: HttpErrorResponse) => {
        console.error('Erro ao buscar conta:', err);
      },
    });
  }

  novaTransacao(transacaoForm: NgForm): void {
    if (!transacaoForm.valid) {
      console.error('O formulário contém campos inválidos.');
      this.snackBar.open(
        'Erro: Preencha todos os campos corretamente!',
        'Fechar',
        {
          duration: 3000,
          horizontalPosition: 'center',
          verticalPosition: 'top',
        }
      );
      return;
    }

    if (this.transacao.tipoTransacao === 'Débito') {
      this.transacao.tipoTransacao = 'D';
    } else {
      this.transacao.tipoTransacao = 'C';
    }

    this.transacaoService.novaTransacao(this.transacao).subscribe({
      next: (res: Transacao) => {
        console.log('Transação salva com sucesso:', res);
        transacaoForm.reset();

        this.snackBar.open('Transação salva com sucesso!', 'Fechar', {
          duration: 3000,
          horizontalPosition: 'center',
          verticalPosition: 'top',
        });
      },
      error: (err: HttpErrorResponse) => {
        console.error('Erro ao salvar transação:', err);
        this.snackBar.open(
          'Erro ao salvar transação. Tente novamente mais tarde.',
          'Fechar',
          {
            duration: 3000,
            horizontalPosition: 'center',
            verticalPosition: 'top',
          }
        );
      },
    });
  }

  formatCurrency(value: number): string {
    if (typeof value === 'number') {
      return this.currencyPipe.transform(value, 'BRL', 'symbol', '1.0-0') || '';
    }
    return '';
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
