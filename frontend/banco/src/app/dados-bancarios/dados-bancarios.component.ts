import { ContaService } from './../service/conta/conta.service';
import { Component, OnInit } from '@angular/core';
import { Conta } from '../cadastro/model/conta.model';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-dados-bancarios',
  standalone: false,
  templateUrl: './dados-bancarios.component.html',
  styleUrls: ['./dados-bancarios.component.css']
})
export class DadosBancariosComponent implements OnInit {

  conta: Conta ={
    contaId: '',
    numeroConta: '',
    digitoConta: '',
    agencia: '',
    dataAbertura: new Date(),
    transacoes: []
  };

  contaId: string = '';

  displayedColumns: string[] = ['numeroConta', 'digitoConta', 'agencia'];

  constructor(private contaService: ContaService){

  }
  ngOnInit(): void {
    this.contaId = 'f4d44022-00c0-4bef-b724-e1f3a45c8ac0';
    this.buscarConta(this.contaId);
  }

  buscarConta(contaId: string): void {
    this.contaService.buscarConta(contaId).subscribe({
      next: (conta: Conta) => {
        console.log('Conta carregada:', conta);
        this.conta = conta;
      },
      error: (err: HttpErrorResponse) => {
        console.error('Erro ao buscar conta:', err);
      },
    });
  }
}
