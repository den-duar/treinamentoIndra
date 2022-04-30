import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ContasService } from 'src/app/services/contas.service';

@Component({
  selector: 'app-contas-saque-deposito',
  templateUrl: './contas-saque-deposito.component.html',
  styleUrls: ['./contas-saque-deposito.component.css']
})
export class ContasSaqueDepositoComponent implements OnInit {

  constructor(
    private contasService: ContasService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {

  }

}
