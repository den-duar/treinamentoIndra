import { Component, OnInit } from '@angular/core';
import { ICliente } from 'src/app/interfaces/cliente';
import { ClientesService } from 'src/app/services/clientes.service';
import Swal from 'sweetalert2';

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

  listarTodos() {
    this.clienteService.listarTodosClientes().subscribe((result: ICliente[]) => {
      this.clientes = result;
    });
  }

  confirmar(id: number) {
    Swal.fire({
      title: 'Cliente será deletado.',
      text: "Tem certeza que deseja remover este cliente?",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sim',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
        this.clienteService.remover(id).subscribe(() => {
          Swal.fire({
            title: 'Operação realizada',
            text: "Cliente removido!",
            icon: 'success',
          });
          this.listarTodos();
        }, error => {
          console.error(error);
        });
      }
    })
  }

}
