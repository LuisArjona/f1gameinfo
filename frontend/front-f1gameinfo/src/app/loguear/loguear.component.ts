import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UsuarioServicioService } from '../usuario-servicio.service';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';

@Component({
    selector: 'app-loguear',
    imports: [FormsModule, RouterModule],
    templateUrl: './loguear.component.html',
    styleUrls: ['./loguear.component.css']
})
export class LoguearComponent {
  pass: string = "";
  email: string = "";
  exito: boolean = false;
  intentado: boolean = false;

  constructor(private usuService: UsuarioServicioService, private router: Router) {}

  loguearUsuario() {
    this.usuService.loguearUsuarioDos(this.email, this.pass).subscribe({
      next: (data) => {
        const token = data;
        if (token) {
          localStorage.setItem("token", JSON.stringify(token));
          this.exito = true;
          this.router.navigate(["/info"]);
        }
      },
      error: (error) => {
        this.exito = false;
      }
    });

    this.intentado = true;
  }
}
