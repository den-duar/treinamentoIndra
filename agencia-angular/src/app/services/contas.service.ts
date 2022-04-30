import { HttpClient } from '@angular/common/http';
import { isNgContainer } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { IConta } from '../interfaces/conta';

@Injectable({
  providedIn: 'root'
})
export class ContasService {
  api = environment.api;
  endpoint = "contas";
  constructor(private http: HttpClient) { }

  listarTodasContas(){
    return this.http.get<IConta[]>(`${this.api}/${this.endpoint}/`)
  }

  listarContaporId(id: number){
    return this.http.get<IConta>(`${this.api}/${this.endpoint}/${id}`)
  }

  remover(id: number) {
    return this.http.delete(`${this.api}/${this.endpoint}/${id}`);
  }

  cadastrar(conta: IConta) {
    return this.http.post(`${this.api}/${this.endpoint}/`, conta);
  }

  sacar(id: number, valor: number){
    return this.http.put(`${this.api}/${this.endpoint}/saque/${id}`, valor);
  }


  depositar(id: number, valor: number){
    return this.http.put(`${this.api}/${this.endpoint}/deposito/${id}`, valor);
  }

  transferir(contaOrigem: IConta, contaDestino: IConta, valor: number){
    return this.http.put(`${this.api}/${this.endpoint}/transferencia/${contaOrigem.id}`, contaDestino.id)
  }

}
