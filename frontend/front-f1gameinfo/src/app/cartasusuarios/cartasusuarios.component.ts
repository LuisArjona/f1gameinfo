import { Component} from '@angular/core';
import { CartaService } from '../carta.service';
import { RouterModule } from '@angular/router'; 
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-cartasusuarios',
  imports: [RouterModule, CommonModule],
  templateUrl: './cartasusuarios.component.html',
  styleUrl: './cartasusuarios.component.css'
})
export class CartasusuariosComponent {
  cartas: any[] = [];

  constructor(private cartaService: CartaService) {}

  ngOnInit() {
    this.cartaService.getCartasCustoms().subscribe((data) => {
      this.cartas = data;
    });
  }

  dividirEnFilas(array: any[], tamanoFila: number): any[][] {
    const filas: any[][] = [];
    for (let i = 0; i < array.length; i += tamanoFila) {
      filas.push(array.slice(i, i + tamanoFila));
    }
    return filas;
  }

}
