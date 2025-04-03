import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UsuarioServicioService } from '../usuario-servicio.service';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import { InputOtpModule } from 'primeng/inputotp';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import Swal from 'sweetalert2';

@Component({
    selector: 'app-loguear',
    imports: [FormsModule, RouterModule, InputOtpModule, InputTextModule, PasswordModule],
    templateUrl: './loguear.component.html',
    styleUrls: ['./loguear.component.css']
})
export class LoguearComponent {
  pass: string = "";
  email: string = "";
  exito: boolean = false;
  intentado: boolean = false;
  showPassword: boolean = false;
  otp: string = "";
  cargando: boolean = false;


  constructor(private usuService: UsuarioServicioService, private router: Router) {}

  togglePasswordVisibility() {
    this.showPassword = !this.showPassword;
  }

  loguearUsuario() {
    this.cargando = true;
    this.usuService.loguearUsuarioDos(this.email, this.pass, this.otp).subscribe({
      next: (data) => {
        const token = data;
        this.cargando = false;
        if (token) {
          localStorage.setItem("token", JSON.stringify(token));
          this.exito = true;
          
          Swal.fire({
            title: "¡Bienvenido " + this.email.split('@')[0] + "!",
            text: "Inicio de sesión exitoso.",
            icon: "success",
            timer: 2000,
            timerProgressBar: true,
            showConfirmButton: false,
            willClose: () => {
              this.router.navigate(["/info"]);
            }
          });
        }
      },
      error: (error) => {
        this.cargando = false;
        let errorMessage = "Ha ocurrido un error inesperado";
        
        if (error.status === 401) {
          errorMessage = "El usuario o la contraseña no son correctos";
        } else if (error.status === 403) {
          errorMessage = "El código OTP no es válido";
        } else if (error.status === 0 || error.message?.includes('Connection refused')) {
          errorMessage = "No se puede establecer la conexión.";
        }

        Swal.fire({
          title: "Error",
          text: errorMessage,
          icon: "error",
          confirmButtonText: "Entendido",
          confirmButtonColor: "#d71920"
        });
        
        this.exito = false;
        this.intentado = true;
      }
    });
  }
}
