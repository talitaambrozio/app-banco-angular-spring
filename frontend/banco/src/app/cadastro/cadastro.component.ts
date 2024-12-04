import { ClienteService } from '../service/cliente/cliente.service';
import { Component, OnInit } from '@angular/core';
import { FormControl, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import { Cliente } from './model/cliente.model';
import { HttpErrorResponse } from '@angular/common/http';


@Component({
  selector: 'app-cadastro',
  standalone: false,
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})
export class CadastroComponent implements OnInit{

  cliente: Cliente ={
    clienteId: '',
    nome: '',
    idade: 0,
    email: '',
    contaRegistroDto: { agencia: '' }
  };

  emailFormControl = new FormControl('', [Validators.required, Validators.email]);
  matcher = new ErrorStateMatcher();

  constructor(private cadastroService: ClienteService){

  }
  ngOnInit(): void {

  }

  salvarCliente(cadastroForm: NgForm): void{
    if(!cadastroForm.valid){
      console.error('O formulário contém campos inválidos.')
      return;
    }
    this.cadastroService.salvarCliente(this.cliente).subscribe({
      next: (res: Cliente) => {
      console.log('Cliente salvo com sucesso:', res);
      cadastroForm.reset();
    },
    error: (err: HttpErrorResponse) =>{
      console.error('Erro ao salvar cliente:', err);
    }
  })
  }

}
