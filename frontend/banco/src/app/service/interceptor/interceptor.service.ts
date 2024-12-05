import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent,
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AutenticacaoService } from '../autenticacao/autenticacao.service';

@Injectable({
  providedIn: 'root',
})
export class InterceptorService implements HttpInterceptor {
  constructor(private authService: AutenticacaoService) {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    console.log(`Interceptando requisição para: ${req.url}`);

    // Log do método HTTP e detalhes da requisição
    console.log(`Método HTTP: ${req.method}`);
    console.log('Headers da requisição:', req.headers);

    if (req.url.includes('/login')) {
      console.log('Requisição de login detectada, não será adicionado token.');
      return next.handle(req);
    }

    const token = this.authService.getToken();

    if (token) {
      console.log(`Token encontrado: ${token}`);
      const clonedRequest = req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`,
        },
      });
      // Log da requisição clonada
      console.log('Requisição clonada com token:', clonedRequest);
      console.log('Headers após adição do token:', clonedRequest.headers);
      return next.handle(clonedRequest);
    }

    console.log('Nenhum token encontrado para essa requisição.');
    return next.handle(req);
  }
}
