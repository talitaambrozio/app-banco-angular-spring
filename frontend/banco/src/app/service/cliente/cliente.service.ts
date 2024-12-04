import { Cliente } from '../../cadastro/model/cliente.model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  constructor(private httpClient: HttpClient) { }

  apiUrl = "http://localhost:8080/api/v1/cliente";

  public salvarCliente(cliente: Cliente): Observable<Cliente>{
    return this.httpClient.post<Cliente>(`${this.apiUrl}/salvar-cliente/`, cliente);
  }

  public buscarCliente(clienteId: string): Observable<Cliente>{
    return this.httpClient.get<Cliente>(`${this.apiUrl}/buscar-cliente-id/${clienteId}`);
  }
}
