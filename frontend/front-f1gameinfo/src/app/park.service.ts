import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ParkService {

  constructor(private http: HttpClient) { }

  getParkStatus(): Observable<any> {
    const storedToken = localStorage.getItem('token');
    const token = storedToken ? JSON.parse(storedToken).token : null;
    console.log(token);

    const headers = {
      headers: new HttpHeaders({
        "Authorization": `Bearer ${token}`
      })
    };

    return this.http.get('http://localhost:3000/park/status', headers);
  }

  updatePark(userId:String, dinosaurIds:any[], recintosIds:any[], coins:number){

  const storedToken = localStorage.getItem('token');
  const token = storedToken ? JSON.parse(storedToken).token : null;
  console.log(token);

  const headers = {
    headers: new HttpHeaders({
      "Authorization": `Bearer ${token}`
    })
  };

    this.http.put('http://localhost:3000/park/update', {"userId":userId, "dinosaurIds":dinosaurIds, "recintosIds":recintosIds, "coins":coins}, headers).subscribe((data)=>{console.log(data)});
  }

  getPilotos():Observable<any>{
    const storedToken = localStorage.getItem('token');
    const token = storedToken ? JSON.parse(storedToken).token : null;

    const headers = {
      headers: new HttpHeaders({
        "Authorization": `Bearer ${token}`
      })
    };
    return this.http.get('http://localhost:8080/pilotos', headers);
  }

  getCircuitos():Observable<any>{
    const storedToken = localStorage.getItem('token');
    const token = storedToken ? JSON.parse(storedToken).token : null;

    const headers = {
      headers: new HttpHeaders({
        "Authorization": `Bearer ${token}`
      })
    };
    return this.http.get('http://localhost:8080/circuitos', headers);
  }
}
