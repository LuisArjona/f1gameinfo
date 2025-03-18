import { Component, OnInit } from '@angular/core';
import { CartaService } from '../carta.service';
import { HttpErrorResponse } from '@angular/common/http';
import { RouterModule } from '@angular/router'; 
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-crearcarta',
  imports: [RouterModule, CommonModule, FormsModule],
  templateUrl: './crearcarta.component.html',
  styleUrl: './crearcarta.component.css'
})
export class CrearcartaComponent implements OnInit {
  cartaCreada: any = null;
  valoracion: number = 0;
  imagen: File | null = null;

  constructor(private cartaService: CartaService) {}

  ngOnInit() {
    this.cartaService.getCartaCustomFromUsuario().subscribe(
      (data) => {
        this.cartaCreada = data;
      },
      (error: HttpErrorResponse) => {
        if (error.status !== 200) {
          this.cartaCreada = null;
        }
      }
    );
  }

  onFileSelected(event: any) {
    this.imagen = event.target.files[0];
  }

  subirCarta() {
    if (this.valoracion < 1 || this.valoracion > 99) {
      alert('La valoración debe estar entre 1 y 99.');
      return;
    }
    if (this.imagen) {
      this.cartaService.subirCarta(this.valoracion, this.imagen).subscribe({
        next: (data: string) => {
          alert(data);
          this.ngOnInit();
        },
        error: (error) => {
          alert('Error al crear la carta.');
        }
      });
    } else {
      alert('Por favor, selecciona una imagen.');
    }
  }
  
  
}
