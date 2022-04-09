import { Component, OnInit } from '@angular/core';
import { IConta } from 'src/app/interfaces/conta';
import { ContasService } from 'src/app/services/contas.service';

@Component({
  selector: 'app-contas',
  templateUrl: './contas.component.html',
  styleUrls: ['./contas.component.css']
})
export class ContasComponent implements OnInit {

  constructor(private contasService: ContasService) { }
  contas: IConta[] = [];

  ngOnInit(): void {
    this.listarTodas();

  }

  listarTodas() {
    this.contasService.listarTodasContas().subscribe((result: IConta[]) => {
      this.contas = result;
    });
  }

}
