import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

import { Conta } from '../cadastro/model/conta.model';
import { ContaService } from '../service/conta/conta.service';
import { Transacao } from '../transacoes/model/transacao.model';
import { TransacaoService } from './../service/transacao/transacao.service';
import { ExtratoBancario } from './model/extrato-bancario.model';


@Component({
  selector: 'app-extrato-bancario',
  standalone: false,
  templateUrl: './extrato-bancario.component.html',
  styleUrls: ['./extrato-bancario.component.css'],
})
export class ExtratoBancarioComponent implements OnInit {

  transacoes: Transacao[] = [];
  contaId: string = '';
  displayedColumns: string[] = ['dataTransacao', 'tipoTransacao', 'valor', 'descricao'];
  filtroForm: FormGroup;
  dataInicial: Date = new Date();
  dataFinal: Date = new Date();

  extratoBancario: ExtratoBancario={
    dataInicial: new Date(),
    dataFinal: new Date(),
    saldo: 0,
    transacoes: this.transacoes
  };
  isFiltroAtivo: boolean = false;
  saldoAtual: number = 0;

  constructor(
    private transacaoService: TransacaoService,
    private contaService: ContaService,
    private fb: FormBuilder ) {
      this.filtroForm = this.fb.group({
      dataInicial: [''],
      dataFinal: [''],
    });}

    ngOnInit(): void {
      this.contaId = 'f4d44022-00c0-4bef-b724-e1f3a45c8ac0';
      this.buscarConta(this.contaId);
    }

  buscarConta(contaId: string): void {
    this.contaService.buscarConta(contaId).subscribe({
      next: (conta: Conta) => {
        console.log('Conta carregada:', conta);
        this.consultarTransacoes(contaId);
      },
      error: (err: HttpErrorResponse) => {
        console.error('Erro ao buscar conta:', err);
      },
    });
  }

  consultarTransacoes(contaId: string): void {
    this.transacaoService.consultarTransacoes(contaId).subscribe({
      next: (res: Transacao[]) => {
        this.transacoes = res;
        console.log('Transações carregadas:', this.transacoes);
      },
      error: (err: HttpErrorResponse) => {
        console.log(err);
      },
    });
  }

  consultarTransacoesComFiltro(): void {
    const { dataInicial, dataFinal } = this.filtroForm.value;
    if (dataInicial && dataFinal) {
      this.isFiltroAtivo = true;
      this.transacaoService.consultarTransacoesComFiltro(this.contaId, dataInicial, dataFinal).subscribe({
        next: (extratoBancario: ExtratoBancario) => {
          this.extratoBancario = extratoBancario;
          console.log('Extrato Bancário com filtro:', this.extratoBancario);
        },
        error: (err: HttpErrorResponse) => {
          console.error('Erro ao consultar transações com filtro:', err);
        },
      });
    } else {
      this.consultarTransacoes(this.contaId);
    }
  }


  limparFiltro(): void {
    this.filtroForm.reset();
    this.isFiltroAtivo = false;
    this.consultarTransacoes(this.contaId);
  }
}
