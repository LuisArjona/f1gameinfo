import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CartaService {

  constructor(private http: HttpClient) { }

  decodeToken(token: string): any {
    try {
      const payload = token.split('.')[1];
      const decodedPayload = atob(payload);
      return JSON.parse(decodedPayload);
    } catch (error) {
      return null;
    }
  }

  getUserIdFromToken(): string | null {
    const token = localStorage.getItem('token');
    if (!token) {
      return null;
    }
    const decodedToken = this.decodeToken(token);
    if (!decodedToken) {
      return null;
    }

    return decodedToken.sub || null;
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

  getCartasCompradas(): Observable<any> {
    const storedToken = localStorage.getItem('token');
    const token = storedToken ? JSON.parse(storedToken).token : null;

    const id = this.getUserIdFromToken();

    const headers = {
      headers: new HttpHeaders({
        "Authorization": `Bearer ${token}`
      })
    };

    return this.http.get(`http://localhost:8080/cartas/${id}`, headers);
  }

  getCartasCustoms(): Observable<any> {
    const storedToken = localStorage.getItem('token');
    const token = storedToken ? JSON.parse(storedToken).token : null;

    const headers = {
      headers: new HttpHeaders({
        "Authorization": `Bearer ${token}`
      })
    };

    return this.http.get(`http://localhost:8080/cartas/usuarios`, headers);
  }

  getCartaCustomFromUsuario(): Observable<any> {
    const storedToken = localStorage.getItem('token');
    const token = storedToken ? JSON.parse(storedToken).token : null;

    const id = this.getUserIdFromToken();

    const headers = {
      headers: new HttpHeaders({
        "Authorization": `Bearer ${token}`
      })
    };

    return this.http.get(`http://localhost:8080/cartas/usuarios/${id}`, headers);
  }

  actualizarCartasUsuario(monedas: number, idPiloto?: number, idCircuito?: number): Observable<any> {
    const storedToken = localStorage.getItem('token');
    const token = storedToken ? JSON.parse(storedToken).token : null;

    const id = this.getUserIdFromToken();

    const headers = new HttpHeaders({
      "Authorization": `Bearer ${token}`
    });

    let params = new HttpParams()
      .set('monedas', monedas.toString());

    if (idPiloto) {
      params = params.set('idPiloto', idPiloto.toString());
    }

    if (idCircuito) {
      params = params.set('idCircuito', idCircuito.toString());
    }

    return this.http.patch(`http://localhost:8080/cartas/${id}`, {}, { headers, params, responseType: 'text' as 'json' });
  }


  subirCarta(valoracion: number, imagen: File): Observable<any> {
    const storedToken = localStorage.getItem('token');
    const token = storedToken ? JSON.parse(storedToken).token : null;

    const id = this.getUserIdFromToken();

    const headers = new HttpHeaders({
      "Authorization": `Bearer ${token}`
    });

    const formData = new FormData();
    formData.append('valoracion', valoracion.toString());
    formData.append('imagen', imagen);

    return this.http.post(`http://localhost:8080/cartas/uploads/${id}`, formData, { headers,
      responseType: 'text' as 'json' });
  }

  getRanking(): Observable<any> {
    const storedToken = localStorage.getItem('token');
    const token = storedToken ? JSON.parse(storedToken).token : null;

    const headers = {
      headers: new HttpHeaders({
        "Authorization": `Bearer ${token}`
      })
    };

    return this.http.get('http://localhost:8080/usuarios/ranking', headers);
  }

  getMonedasFromUsuario(): Observable<any> {
    const storedToken = localStorage.getItem('token');
    const token = storedToken ? JSON.parse(storedToken).token : null;

    const id = this.getUserIdFromToken();

    const headers = {
      headers: new HttpHeaders({
        "Authorization": `Bearer ${token}`
      })
    };

    return this.http.get(`http://localhost:8080/usuarios/${id}/monedas`, headers);
  }

}
