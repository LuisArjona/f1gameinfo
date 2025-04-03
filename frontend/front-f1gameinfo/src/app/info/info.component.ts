import { Component } from '@angular/core';
import { CartaService } from '../carta.service';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-info',
  imports: [RouterModule, CommonModule],
  templateUrl: './info.component.html',
  styleUrl: './info.component.css'
})
export class InfoComponent {
  pilotos: any[] = [];
  circuitos: any[] = [];
  menuActivo: boolean = false;
  filtroVictorias: boolean = false;
  filtroCarreras: boolean = false;
  filtroAntiguos: boolean = false;
  filtroNuevos: boolean = false;

  constructor(private cartaService: CartaService) {}

  ngOnInit() {
    this.cartaService.getPilotos().subscribe((data) => {
      this.pilotos = data;
    });
    this.cartaService.getCircuitos().subscribe((data) => {
      this.circuitos = data;
    });
  }

  ordenarPorVictorias() {
    this.pilotos.sort((a, b) => b.victorias - a.victorias);
    this.filtroVictorias = true;
    this.filtroCarreras = false;
  }

  ordenarPorCarreras() {
    this.pilotos.sort((a, b) => b.carreras - a.carreras);
    this.filtroVictorias = false;
    this.filtroCarreras = true;
  }

  ordenarPorAnhoConstruccionAntiguos() {
    this.circuitos.sort((a, b) => a.anhoConstruccion - b.anhoConstruccion);
    this.filtroAntiguos = true;
    this.filtroNuevos = false;
  }

  ordenarPorAnhoConstruccionNuevos() {
    this.circuitos.sort((a, b) => b.anhoConstruccion - a.anhoConstruccion);
    this.filtroAntiguos = false;
    this.filtroNuevos = true;
  }

  activarMenu() {
    this.menuActivo = !this.menuActivo;
  }

  cerrarSesion(): void {
    localStorage.clear();
  }
}
