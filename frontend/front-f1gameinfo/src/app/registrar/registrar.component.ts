import { Component } from '@angular/core';
import { FormBuilder,FormGroup,Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { UsuarioServicioService } from '../usuario-servicio.service';
import { Router } from '@angular/router';

@Component({
    selector: 'app-registrar',
    imports: [ReactiveFormsModule],
    templateUrl: './registrar.component.html',
    styleUrl: './registrar.component.css'
})
export class RegistrarComponent {
  form:FormGroup;
  registrado:boolean=false;

  constructor(private fb:FormBuilder, private usuServicio:UsuarioServicioService, private router:Router){
    this.form=this.fb.group({
      email:["", [Validators.required, Validators.email]],
      pass:["",[Validators.required, Validators.minLength(8)]],
      confirm_pass:["", [Validators.required]]
    })
  }

  registrarUsuario(){
    this.usuServicio.registrarUsuarioDos(this.form.get('email')?.value, this.form.get('pass')?.value);
    this.router.navigate(["/login"]);
  }

}
