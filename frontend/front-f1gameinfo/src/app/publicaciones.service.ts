import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PublicacionesService {

  constructor(private http: HttpClient) { }

  conseguirPublicaciones(id:number):Observable<any>{
    return this.http.get(`https://67174bf1b910c6a6e02761c0.mockapi.io/user/${id}/post`);
  }

  crearPost(id:string,titulo:string,texto:string){
    this.http.post(`https://67174bf1b910c6a6e02761c0.mockapi.io/user/${id}/post`,{titulo,texto}).subscribe(()=>{
      return true;
    })
    return false;
  }
}
