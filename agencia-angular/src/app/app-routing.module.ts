import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientesCadastrarEditarComponent } from './pages/clientes/clientes-cadastrar-editar/clientes-cadastrar-editar.component';
import { ClientesComponent } from './pages/clientes/clientes.component';
import { ContasCadastrarEditarComponent } from './pages/contas/contas-cadastrar-editar/contas-cadastrar-editar.component';
import { ContasSaqueDepositoComponent } from './pages/contas/contas-saque-deposito/contas-saque-deposito.component';

import { ContasComponent } from './pages/contas/contas.component';

const routes: Routes = [
  { path: 'clientes', component: ClientesComponent },
  { path: 'contas', component: ContasComponent },
  { path: 'clientes/cadastrar', component: ClientesCadastrarEditarComponent },
  { path: 'clientes/editar/:id', component: ClientesCadastrarEditarComponent },
  { path: 'contas/saque/:id', component: ContasSaqueDepositoComponent},
  { path: 'contas/deposito/:id', component: ContasSaqueDepositoComponent},
  { path: 'contas/transferencia/:id', component: ContasSaqueDepositoComponent},
  { path: 'contas/cadastrar', component: ContasCadastrarEditarComponent},
  { path: 'contas/editar/:id', component: ContasCadastrarEditarComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
