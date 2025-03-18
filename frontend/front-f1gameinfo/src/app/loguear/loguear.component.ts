import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UsuarioServicioService } from '../usuario-servicio.service';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import Swal from 'sweetalert2';

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
  showPassword: boolean = false;


  constructor(private usuService: UsuarioServicioService, private router: Router) {}

  togglePasswordVisibility() {
    this.showPassword = !this.showPassword;
  }

  loguearUsuario() {
    this.usuService.loguearUsuarioDos(this.email, this.pass).subscribe({
      next: (data) => {
        const token = data;
        if (token) {
          localStorage.setItem("token", JSON.stringify(token));
          this.exito = true;
          this.router.navigate(["/info"]);
          Swal.fire({title: "Bienvenido", text: "Disfruta de F1GameInfo", icon: "success"});
        }
      },
      error: (error) => {
        Swal.fire({title: "Algo ha ido mal", text: "El usuario o la contraseña no son correctos", icon: "error"});
        this.exito = false;
      }
    });

    this.intentado = true;
  }
}
