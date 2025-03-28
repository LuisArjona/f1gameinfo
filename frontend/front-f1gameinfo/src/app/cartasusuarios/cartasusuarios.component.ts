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
  menuActivo: boolean = false;

  constructor(private cartaService: CartaService) {}

  ngOnInit() {
    this.cartaService.getCartasCustoms().subscribe((data) => {
      this.cartas = data;
    });
  }

  activarMenu() {
    this.menuActivo = !this.menuActivo;
  }

  cerrarSesion(): void {
    localStorage.clear();
  }

}
