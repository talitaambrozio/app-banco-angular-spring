import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

import { Conta } from '../cadastro/model/conta.model';
import { ContaService } from '../service/conta/conta.service';
import { Transacao } from '../transacoes/model/transacao.model';
import { TransacaoService } from './../service/transacao/transacao.service';
import { ExtratoBancario } from './model/extrato-bancario.model';
import { ClienteService } from '../service/cliente/cliente.service';
import { AutenticacaoService } from '../service/autenticacao/autenticacao.service';
import { DadosClienteLogado } from '../service/cliente/dados-cliente-logado.model';


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
  saldoAtual: number = 0;

  extratoBancario: ExtratoBancario={
    dataInicial: new Date(),
    dataFinal: new Date(),
    saldoDoPeriodo: 0,
    saldoAtual: 0,
    transacoes: this.transacoes
  };

  dadosClienteLogado: DadosClienteLogado ={
    clienteId: '',
    contaId: '',
  };

  isFiltroAtivo: boolean = false;
  clienteId: string = '';
  username: string = '';

  constructor(
    private transacaoService: TransacaoService,
    private contaService: ContaService,
    private clienteService: ClienteService,
    private autenticacaoService: AutenticacaoService,
    private fb: FormBuilder ) {
      this.filtroForm = this.fb.group({
      dataInicial: [''],
      dataFinal: [''],
    });}

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
        this.consultarTransacoes(contaId);
        this.consultarTransacoesComFiltro();
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
          this.saldoAtual = extratoBancario.saldoAtual;
 
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

  obtemDadosClienteLogado(username: string): void {
    this.clienteService.obtemDadosClienteLogado(username).subscribe({
      next: (res: DadosClienteLogado) => {
        console.log('Dados do cliente logado:', res);
        this.dadosClienteLogado = res;
        this.contaId = this.dadosClienteLogado.contaId;
        this.buscarConta(this.contaId);
        this.obtemSaldoAtual(this.contaId);
      },
      error: (err: HttpErrorResponse) => {
        console.error('Erro ao buscar cliente:', err);
      },
    });
  }

  obtemSaldoAtual(contaId: string): void{
    this.transacaoService.obterSaldoAtual(contaId).subscribe({
      next: (res: number) => {
        this.saldoAtual = res;
        this.contaId = this.dadosClienteLogado.contaId;
      },
      error: (err: HttpErrorResponse) => {
        console.error('Erro ao buscar cliente:', err);
      },
    });
  }

}
