import { Component } from '@angular/core';
import { FormBuilder,FormGroup,Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { UsuarioServicioService } from '../usuario-servicio.service';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
    selector: 'app-registrar',
    imports: [ReactiveFormsModule, RouterModule],
    templateUrl: './registrar.component.html',
    styleUrl: './registrar.component.css'
})
export class RegistrarComponent {
  form:FormGroup;
  registrado:boolean=false;
  exito: boolean = false;
  intentado: boolean = false;

  constructor(private fb:FormBuilder, private usuServicio:UsuarioServicioService, private router:Router){
    this.form=this.fb.group({
      email:["", [Validators.required, Validators.email]],
      pass:["",[Validators.required, Validators.minLength(8)]],
      confirm_pass:["", [Validators.required]]
    })
  }

  registrarUsuario(){
    this.usuServicio.registrarUsuarioDos(this.form.get('email')?.value, this.form.get('pass')?.value).subscribe({
          next: (data) => {
            Swal.fire({title: "Perfecto", text: "Has creado tu cuenta", icon: "success"});
            this.router.navigate(["/login"]);
          },
          error: (error) => {
            Swal.fire({title: "Algo ha ido mal", text: "El correo ya está registrado", icon: "error"});
            this.exito = false;
          }
        });
    this.intentado = true;
  }

}
