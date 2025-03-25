import { Component } from '@angular/core';
import { CartaService } from '../carta.service';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-comprarcartas',
  imports: [RouterModule, CommonModule],
  templateUrl: './comprarcartas.component.html',
  styleUrl: './comprarcartas.component.css',
})
export class ComprarcartasComponent {
  pilotos: any[] = [];
  circuitos: any[] = [];
  pilotosUsu: any[] = [];
  circuitosUsu: any[] = [];
  monedas: number = 0;
  menuActivo: boolean = false;

  constructor(private cartaService: CartaService) {}

  ngOnInit() {
    this.cartaService.getPilotos().subscribe((data) => {
      this.pilotos = data;
    });

    this.cartaService.getCircuitos().subscribe((data) => {
      this.circuitos = data;
    });

    this.cartaService.getMonedasFromUsuario().subscribe((data) => {
      this.monedas = data;
    });

    this.cartaService.getCartasCompradas().subscribe((data) => {
      this.pilotosUsu = Array.from(data.pilotos);
      this.circuitosUsu = Array.from(data.circuitos);
    });
  }

  dividirEnFilas(array: any[], tamanoFila: number): any[][] {
    const filas: any[][] = [];
    for (let i = 0; i < array.length; i += tamanoFila) {
      filas.push(array.slice(i, i + tamanoFila));
    }
    return filas;
  }

  comprarPiloto(piloto: any) {
    if (this.monedas >= piloto.precio) {
      this.cartaService.actualizarCartasUsuario(this.monedas - piloto.precio, piloto.id).subscribe(() => {
        this.pilotosUsu.push(piloto);
        this.monedas -= piloto.precio;
      });
    }
  }

  comprarCircuito(circuito: any) {
    if (this.monedas >= circuito.precio) {
      this.cartaService.actualizarCartasUsuario(this.monedas - circuito.precio, undefined, circuito.id).subscribe(() => {
        this.circuitosUsu.push(circuito);
        this.monedas -= circuito.precio;
      });
    }
  }

  isPilotoDisabled(piloto: any): boolean {
    return this.monedas < piloto.precio || this.pilotosUsu.some(p => p.id === piloto.id);
  }
  
  isCircuitoDisabled(circuito: any): boolean {
    return this.monedas < circuito.precio || this.circuitosUsu.some(c => c.id === circuito.id);
  }

  isCircuitoComprado(circuito: any): boolean {
    return this.circuitosUsu.some(c => c.id === circuito.id);
  }

  isPilotoComprado(piloto: any): boolean {
    return this.pilotosUsu.some(p => p.id === piloto.id);
  }

  incrementarMonedas() {
    this.cartaService.actualizarCartasUsuario(this.monedas + 100).subscribe(() => {
        this.monedas += 100;
    });
  }

  activarMenu() {
    this.menuActivo = !this.menuActivo;
  }

  getPorcentajePilotos(): number {
    return (this.pilotosUsu.length / this.pilotos.length) * 100;
  }

  getPorcentajeCircuitos(): number {
    return (this.circuitosUsu.length / this.circuitos.length) * 100;
  }

  cerrarSesion(): void {
    localStorage.clear();
  }
  
}
