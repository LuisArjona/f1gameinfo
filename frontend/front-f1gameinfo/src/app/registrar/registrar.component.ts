import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { UsuarioServicioService } from '../usuario-servicio.service';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import { KeyFilterModule } from 'primeng/keyfilter';
import { AbstractControl } from '@angular/forms';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-registrar',
  imports: [ReactiveFormsModule, RouterModule, InputTextModule, PasswordModule, KeyFilterModule],
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
  bloquearEspacios: RegExp = /^[^\s]*$/;
  cargando: boolean = false;

  constructor(private fb: FormBuilder, private usuServicio: UsuarioServicioService, private router: Router) {
    this.form = this.fb.group({
      email: ["", [Validators.required, Validators.email]],
      pass: ["", [Validators.required, this.validarPass.bind(this)]],
      confirm_pass: ["", [Validators.required]]
    });
  }

  validarPass(control: AbstractControl): { [key: string]: boolean } | null {
    const value = control.value;
    if (!value) return null;
  
    const hasUpperCase = /[A-Z]/.test(value);
    const hasLowerCase = /[a-z]/.test(value);
    const hasNumber = /[0-9]/.test(value);
    const hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/.test(value);
    const isValid = hasUpperCase && hasLowerCase && hasNumber && hasSpecialChar;
  
    return !isValid ? { passwordComplexity: true } : null;
  }
  

  registrarUsuario() {
    this.cargando = true;
    this.usuServicio.registrarUsuarioDos(
      this.form.get('email')?.value, 
      this.form.get('pass')?.value
    ).subscribe({
      next: (data) => {
        this.cargando = false;
        this.exito = true;
        this.registrado = true;
        Swal.fire({ 
          title: "Perfecto", 
          text: "Has creado tu cuenta", 
          icon: "success",
          timer: 2000,
          timerProgressBar: true,
          showConfirmButton: false
        });
        this.qr = data.qr;
        this.secreto = data.secreto;
      },
      error: (error) => {
        this.cargando = false;
        this.exito = false;
        this.intentado = true;

        let errorMessage = "Ocurrió un error inesperado";
        
        if (error.status === 400) {
          errorMessage = "El correo ya está registrado";
        } 
        else if (error.status === 0 || error.message?.includes('Connection refused')) {
          errorMessage = "No se puede establecer la conexión.";
        }
  
        Swal.fire({ 
          title: "Algo ha ido mal", 
          text: errorMessage, 
          icon: "error",
          confirmButtonColor: "#d71920",
          confirmButtonText: "Entendido"
        });
      }
    });
  }

  tieneMayus(): boolean {
    const value = this.form.get('pass')?.value || '';
    return /[A-Z]/.test(value);
  }
  
  tieneMinus(): boolean {
    const value = this.form.get('pass')?.value || '';
    return /[a-z]/.test(value);
  }
  
  tieneNumero(): boolean {
    const value = this.form.get('pass')?.value || '';
    return /[0-9]/.test(value);
  }
  
  tieneCaracterEspecial(): boolean {
    const value = this.form.get('pass')?.value || '';
    return /[!@#$%^&*(),.?":{}|<>]/.test(value);
  }
  
  tiene8Caracteres(): boolean {
    const value = this.form.get('pass')?.value || '';
    return value.length >= 8;
  }
}