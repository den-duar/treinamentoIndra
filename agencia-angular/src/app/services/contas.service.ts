import { HttpClient } from '@angular/common/http';
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
}
