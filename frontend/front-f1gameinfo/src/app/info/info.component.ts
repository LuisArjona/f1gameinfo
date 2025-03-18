import { Component } from '@angular/core';
import { CartaService } from '../carta.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-info',
  imports: [RouterModule],
  templateUrl: './info.component.html',
  styleUrl: './info.component.css'
})
export class InfoComponent {
  pilotos: any[] = [];
  circuitos: any[] = [];

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
  }

  ordenarPorCarreras() {
    this.pilotos.sort((a, b) => b.carreras - a.carreras);
  }

  ordenarPorAnhoConstruccion() {
    this.circuitos.sort((a, b) => a.anhoConstruccion - b.anhoConstruccion);
  }
}
