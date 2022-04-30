import { Component, OnInit } from '@angular/core';
import { IConta } from 'src/app/interfaces/conta';
import { FormControl, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-contas-cadastrar-editar',
  templateUrl: './contas-cadastrar-editar.component.html',
  styleUrls: ['./contas-cadastrar-editar.component.css']
})
export class ContasCadastrarEditarComponent implements OnInit {

  emptyConta: IConta = {
    agencia:"",
    numero:"",
    cliente : {
      id: 0,
      nome: '',
      cpf: '',
      email: '',
      observacoes: '',
      ativo: true
    },
    id: 0,
    saldo: 0
  }

  formConta: FormGroup = this.preencheFormGroup(this.emptyConta);

  constructor() { }

  ngOnInit(): void {
  }

  preencheFormGroup(conta: IConta): FormGroup {
    return new FormGroup({
      id:new FormControl(conta.id?conta.id:null),
      agencia: new FormControl(conta.agencia, Validators.required),
      cliente: new FormControl(conta.cliente, Validators.required),
      saldo: new FormControl(conta.saldo, Validators.required),
      numero: new FormControl(conta.numero),
    });
  }

  estaEditando(){
    return !!this.formConta.get("id")?.value;
  }

  enviar(){

  }

}
