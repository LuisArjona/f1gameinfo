import { Component } from '@angular/core';
import { CartaService } from '../carta.service';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { DrawerModule } from 'primeng/drawer';

@Component({
  selector: 'app-ranking',
  imports: [RouterModule, CommonModule, DrawerModule],
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
  visible:boolean = false;
  usuarioSeleccionado: any = null;
  drawerHeader: string = 'Colección del Usuario';

  constructor(private cartaService: CartaService) {}

  ngOnInit() {
    this.cartaService.getRanking().subscribe((data) => {
      this.ranking = data;
      this.originalRanking = [...data];
    });
  }

  mostrarColeccion(user: any) {
    this.usuarioSeleccionado = user;
    this.drawerHeader = `Colección de ${user.username.split('@')[0]}`;
    this.visible = true;
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
