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
  menuActivo: boolean = false;
  filtroTotal:boolean = true;
  filtroPilotos:boolean = false;
  filtroCircuitos:boolean = false;

  constructor(private cartaService: CartaService) {}

  ngOnInit() {
    this.cartaService.getRanking().subscribe((data) => {
      this.ranking = data;
      this.originalRanking = [...data];
    });
  }

  ordenarPorTotal() {
    this.ranking = [...this.originalRanking];
    this.activarFiltroTotal();
  }

  ordenarPorPilotos() {
    this.ranking.sort((a, b) => b.cantidadPilotos - a.cantidadPilotos);
    this.activarFiltroPilotos();
  }

  ordenarPorCircuitos() {
    this.ranking.sort((a, b) => b.cantidadCircuitos - a.cantidadCircuitos);
    this.activarFiltroCircuitos();
  }

  activarMenu() {
    this.menuActivo = !this.menuActivo;
  }

  cerrarSesion(): void {
    localStorage.clear();
  }

  activarFiltroTotal() {
    this.filtroTotal = true;
    this.filtroPilotos = false;
    this.filtroCircuitos = false;
  }

  activarFiltroPilotos() {
    this.filtroTotal = false;
    this.filtroPilotos = true;
    this.filtroCircuitos = false;
  }

  activarFiltroCircuitos() {
    this.filtroTotal = false;
    this.filtroPilotos = false;
    this.filtroCircuitos = true;
  }
  
}
