import { Injectable } from '@angular/core';
import { Transacao } from 'src/app/transacoes/model/transacao.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ExtratoBancario } from 'src/app/extrato-bancario/model/extrato-bancario.model';

@Injectable({
  providedIn: 'root'
})
export class TransacaoService {

  constructor(private httpClient: HttpClient) { }

  apiUrl = "http://localhost:8080/api/v1/transacao";

  public novaTransacao(transacao: Transacao): Observable<Transacao>{
    return this.httpClient.post<Transacao>(`${this.apiUrl}/nova-transacao/`, transacao);
  }

  public consultarTransacoes(contaId: string): Observable<Transacao[]>{
    return this.httpClient.get<Transacao[]>(`${this.apiUrl}/transacoes/${contaId}`);
  }

  public consultarTransacoesComFiltro(contaId: string,
    dataInicial: Date, dataFinal: Date): Observable<ExtratoBancario>{
      const dataInicialFormatada = dataInicial.toLocaleDateString('pt-BR');
      const dataFinalFormatada = dataFinal.toLocaleDateString('pt-BR');
    return this.httpClient.get<ExtratoBancario>(`${this.apiUrl}/transacoes/gerar-extrato-bancario`, {
      params: {
        contaId: contaId,
        dataInicial: dataInicialFormatada,
        dataFinal: dataFinalFormatada
      }
    },
      );
  }

  public obterSaldo(contaId: string): Observable<number> {
    return this.httpClient.get<number>(`${this.apiUrl}/transacoes/obter-saldo/${contaId}`);
  }


}
