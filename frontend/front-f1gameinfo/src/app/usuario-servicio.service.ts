import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UsuarioServicioService {

  constructor(private http: HttpClient) { }

  registrarUsuarioDos(email:string,pass:string): Observable<any>{
    return this.http.post('http://localhost:8080/autentificacion/registro', {"username": email, "password":pass});
  }

  loguearUsuarioDos(email:string,pass:string,otp:string): Observable<any>{
    const params = new HttpParams()
    .set('username', email)
    .set('password', pass)
    .set('otp', otp);

  return this.http.post('http://localhost:8080/autentificacion/login', null, { params });
  }


}
