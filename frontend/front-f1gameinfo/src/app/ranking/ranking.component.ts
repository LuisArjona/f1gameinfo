import { Component } from '@angular/core';
import { CartaService } from '../carta.service';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-ranking',
  imports: [RouterModule, CommonModule],
  templateUrl: './ranking.component.html',
  styleUrl: './ranking.component.css'
})
export class RankingComponent {
  ranking: any[] = [];
  originalRanking: any[] = [];

  constructor(private cartaService: CartaService) {}

  ngOnInit() {
    this.cartaService.getRanking().subscribe((data) => {
      this.ranking = data;
      this.originalRanking = [...data];
    });
  }

  ordenarPorTotal() {
    this.ranking = [...this.originalRanking];
  }

  ordenarPorPilotos() {
    this.ranking.sort((a, b) => b.cantidadPilotos - a.cantidadPilotos);
  }

  ordenarPorCircuitos() {
    this.ranking.sort((a, b) => b.cantidadCircuitos - a.cantidadCircuitos);
  }
}
