import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Conta } from 'src/app/cadastro/model/conta.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ContaService {

  constructor(private httpClient: HttpClient) { }

  apiUrl = "http://localhost:8080/api/v1/conta";

  public buscarConta(contaId: string): Observable<Conta>{
    return this.httpClient.get<Conta>(`${this.apiUrl}/dados-bancarios/${contaId}`);
  }
}
