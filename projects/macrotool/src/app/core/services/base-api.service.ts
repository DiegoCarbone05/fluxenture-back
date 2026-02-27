import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export abstract class BaseApiService<T> {
  protected http = inject(HttpClient);
  protected abstract readonly endpoint: string; // Cada hijo define su URL
  protected api = "http://localhost:8080";
  //protected api = "http://56.125.1.30:8080";

  //https://fluxenture-back-production.up.railway.app

  getAll() {
    return this.http.get<T[]>(this.endpoint);
  }
}
