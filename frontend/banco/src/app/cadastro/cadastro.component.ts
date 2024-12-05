import { ClienteService } from '../service/cliente/cliente.service';
import { Component, OnInit } from '@angular/core';
import { FormControl, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import { HttpErrorResponse } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Cliente } from './model/cliente.model';


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
    password: '',
    contaRegistroDto: { agencia: '' }
  };


  emailFormControl = new FormControl('', [Validators.required, Validators.email]);
  matcher = new ErrorStateMatcher();

  constructor(private cadastroService: ClienteService, private snackBar: MatSnackBar){

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

      this.snackBar.open('Cadastro realizado com sucesso!', 'Fechar', {
        duration: 3000,
        horizontalPosition: 'center',
        verticalPosition: 'top',
      });
    },
    error: (err: HttpErrorResponse) =>{
      console.error('Erro ao salvar cliente:', err);
    }
  })
  }

}
