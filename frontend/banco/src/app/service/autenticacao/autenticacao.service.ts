import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AutenticacaoService {

  private apiUrl = 'http://localhost:8080/api/v1/users/login';

  constructor(private http: HttpClient) { }

  login(email: string, password: string): Observable<any> {
    return this.http.post<any>(this.apiUrl, { email, password });
  }
  storeToken(token: string): void {
    localStorage.setItem('jwtToken', token);
  }

  getToken(): string | null {
    return localStorage.getItem('jwtToken');
  }

  logout(): void {
    localStorage.removeItem('jwtToken');
  }

    getUsernameFromToken(): string {
      const token = this.getToken();
      if (token) {
        try {
          const payload = token.split('.')[1];
          const decodedPayload = atob(payload);
          const payloadObj = JSON.parse(decodedPayload);
          return payloadObj.sub;

        } catch (e) {
          console.error('Erro ao decodificar o token:', e);
          return '';
        }
      }
      return '';
    }


    getRolesFromToken(): string[] {
      const token = this.getToken();
      if (token) {
        try {
          const payload = token.split('.')[1];
          const decodedPayload = atob(payload);
          const payloadObj = JSON.parse(decodedPayload);
          return payloadObj.roles || [];
        } catch (error) {
          console.error('Erro ao decodificar o token:', error);
          return [];
        }
      }
      return [];
    }
}
