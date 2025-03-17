import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuarioServicioService {

  constructor(private http: HttpClient) { }

  registrarUsuarioDos(email:string,pass:string):void{
    this.http.post('http://localhost:8080/autentificacion/registro', {"username": email, "password":pass}).subscribe((data)=>{console.log(data)});
  }

  loguearUsuarioDos(email:string,pass:string): Observable<any>{
    return this.http.post('http://localhost:8080/autentificacion/login', {"username": email, "password":pass});
  }


}
