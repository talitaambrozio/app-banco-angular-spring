import { AutenticacaoService } from './../autenticacao/autenticacao.service';
import { Cliente } from '../../cadastro/model/cliente.model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DadosClienteLogado } from './dados-cliente-logado.model';


@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  constructor(private httpClient: HttpClient, private authService: AutenticacaoService) { }

  apiUrl = "http://localhost:8080/api/v1/cliente";
  urlCadastro = "http://localhost:8080/api/v1/users/salvar-cliente";

  public salvarCliente(cliente: Cliente): Observable<Cliente>{
    return this.httpClient.post<Cliente>(this.urlCadastro, cliente);
  }

  public buscarCliente(clienteId: string): Observable<Cliente>{
    return this.httpClient.get<Cliente>(`${this.apiUrl}/buscar-cliente-id/${clienteId}`);
  }


  public obtemDadosClienteLogado(username: string): Observable<DadosClienteLogado> {
    return this.httpClient.get<DadosClienteLogado>(`${this.apiUrl}/dados-cliente-logado?username=${username}`);
  }


}
