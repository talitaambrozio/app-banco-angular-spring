import { CurrencyPipe } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

import { Conta } from '../cadastro/model/conta.model';
import { TransacaoService } from '../service/transacao/transacao.service';
import { ContaService } from './../service/conta/conta.service';
import { Transacao } from './model/transacao.model';

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

  contaId: string = '';
  numeroConta: string = '';

  constructor(
    private transacaoService: TransacaoService,
    private contaService: ContaService,
    private currencyPipe: CurrencyPipe
  ) {}

  ngOnInit(): void {
    this.contaId = 'f4d44022-00c0-4bef-b724-e1f3a45c8ac0';

    this.buscarConta(this.contaId);
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
      },
      error: (err: HttpErrorResponse) => {
        console.error('Erro ao salvar transação:', err);
      },
    });
  }

  formatCurrency(value: number): string {
    if (typeof value === 'number') {
      return this.currencyPipe.transform(value, 'BRL', 'symbol', '1.0-0') || '';
    }
    return '';
  }
}
