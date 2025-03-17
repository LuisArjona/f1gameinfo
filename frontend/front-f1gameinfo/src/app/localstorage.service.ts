import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LocalstorageService {

  constructor() { }

  getItem(key: string): any | null {
    return JSON.parse(localStorage.getItem(key) || '');
  }
}
