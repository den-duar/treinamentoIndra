import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { ClientesComponent } from './pages/clientes/clientes.component';
import { ContasComponent } from './pages/contas/contas.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    ClientesComponent,
    ContasComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
