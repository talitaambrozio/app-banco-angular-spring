import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { CadastroComponent } from './cadastro/cadastro.component';
import { DadosPessoaisComponent } from './dados-pessoais/dados-pessoais.component';
import { DadosBancariosComponent } from './dados-bancarios/dados-bancarios.component';
import { ExtratoBancarioComponent } from './extrato-bancario/extrato-bancario.component';
import { TransacoesComponent } from './transacoes/transacoes.component';

const routes: Routes = [
  {path: 'header', component: HeaderComponent},
  {path: '', component: HomeComponent},
  {path: 'cadastro', component: CadastroComponent},
  {path: 'dados-pessoais', component: DadosPessoaisComponent},
  {path: 'dados-bancarios', component: DadosBancariosComponent},
  {path: 'extrato-bancario', component: ExtratoBancarioComponent},
  {path: 'transacoes-financeiras', component: TransacoesComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
