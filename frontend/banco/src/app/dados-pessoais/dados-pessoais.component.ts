import { Component,OnInit } from '@angular/core';
import { Cliente } from '../cadastro/model/cliente.model';
import { ClienteService } from '../service/cliente/cliente.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-dados-pessoais',
  standalone: false,
  templateUrl: './dados-pessoais.component.html',
  styleUrls: ['./dados-pessoais.component.css']
})
export class DadosPessoaisComponent implements OnInit{

  cliente: Cliente ={
    clienteId: '',
    nome: '',
    idade: 0,
    email: '',
    contaRegistroDto: { agencia: '' }
  };
  displayedColumns: string[] = ['nome', 'idade', 'email'];


  constructor(private clienteService: ClienteService){

  }
  ngOnInit(): void {
    const clienteId = '22a0e8c3-6153-49f3-909c-04bbb0cf0acf';
    this.getCliente(clienteId);
  }

  getCliente(clienteId: string): void{
    this.clienteService.buscarCliente(clienteId).subscribe(
      {
        next: (res: Cliente) =>{
          console.log(res);
          this.cliente = res;
        },
        error: (err: HttpErrorResponse) =>{
          console.log(err);
        }
      }
    )
  }

}
