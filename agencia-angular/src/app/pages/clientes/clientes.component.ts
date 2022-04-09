import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ICliente } from 'src/app/interfaces/cliente';
import { ClientesService } from 'src/app/services/clientes.service';
import Swal from "sweetalert2";

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.css']
})
export class ClientesComponent implements OnInit {

  constructor(private clienteService: ClientesService) { }

  clientes: ICliente[] = [];

  ngOnInit(): void {
    this.listarTodos();
  }

  listarTodos(){
    this.clienteService.listarTodosClientes().subscribe((result: ICliente[]) => {
      this.clientes = result
      console.log(this.clientes);
    });
  }

  confirmar(id: number){
    Swal.fire({
      title: 'Você tem certeza?',
      text: "Isso não tera volta!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sim, tenho certeza!',
      cancelButtonText: "Cancelar"
    }).then((result) => {
      if (result.isConfirmed) {
        this.clienteService.remover(id).subscribe(() => {
          Swal.fire({
            title:"Remoção concluida",
            text: "Cliente removido com sucesso",
            icon:"success"
          });
          this.listarTodos();
        }, error =>{
          console.error(error);
        })
      }
    })

  }

}
