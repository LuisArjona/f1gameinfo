import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { UsuarioServicioService } from '../usuario-servicio.service';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-registrar',
  imports: [ReactiveFormsModule, RouterModule, InputTextModule, PasswordModule],
  templateUrl: './registrar.component.html',
  styleUrls: ['./registrar.component.css']
})
export class RegistrarComponent {
  form: FormGroup;
  registrado: boolean = false;
  exito: boolean = false;
  intentado: boolean = false;
  qr: string = "";
  secreto: string = "";

  constructor(private fb: FormBuilder, private usuServicio: UsuarioServicioService, private router: Router) {
    this.form = this.fb.group({
      email: ["", [Validators.required, Validators.email]],
      pass: ["", [Validators.required, Validators.minLength(8)]],
      confirm_pass: ["", [Validators.required]]
    });
  }

  registrarUsuario() {
    this.usuServicio.registrarUsuarioDos(this.form.get('email')?.value, this.form.get('pass')?.value).subscribe({
      next: (data) => {
        this.exito = true;
        this.registrado = true;
        Swal.fire({ title: "Perfecto", text: "Has creado tu cuenta", icon: "success" });
        this.qr = data.qr;
        this.secreto = data.secreto;
      },
      error: (error) => {
        Swal.fire({ title: "Algo ha ido mal", text: "El correo ya está registrado", icon: "error" });
        this.exito = false;
      }
    });
    this.intentado = true;
  }
}