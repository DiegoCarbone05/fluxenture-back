import { Injectable } from '@angular/core';
import { Tnt } from '../models/Tnt.model';

export interface TrackAndTraceParams {
  id: string;
  producto?: string;
  pais?: string;
  cookie?: string;
}

@Injectable({
  providedIn: 'root'
})
export class TrackAndTrace {
  private readonly API_URL = 'https://www.correoargentino.com.ar/sites/all/modules/custom/ca_forms/api/wsFacade.php';

  /**
   * Track a package by ID
   * @param params Tracking parameters
   * @returns Promise with the tracking result as text
   */
  async trackPackage(cnNumber: string): Promise<Tnt[]> {
    const cookie = "cookie";
    const pais = "AR";
    const producto = "CD";

    const headers = new Headers();
    headers.append('Accept', 'text/html, */*; q=0.01');
    headers.append('Accept-Language', 'es-ES,es;q=0.9');
    headers.append('Connection', 'keep-alive');
    headers.append('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
    headers.append('Origin', 'https://www.correoargentino.com.ar');
    headers.append('Referer', 'https://www.correoargentino.com.ar/');
    headers.append('Sec-Fetch-Dest', 'empty');
    headers.append('Sec-Fetch-Mode', 'cors');
    headers.append('Sec-Fetch-Site', 'same-origin');
    headers.append('User-Agent', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/141.0.0.0 Safari/537.36');
    headers.append('X-Requested-With', 'XMLHttpRequest');
    headers.append('sec-ch-ua', '"Google Chrome";v="141", "Not?A_Brand";v="8", "Chromium";v="141"');
    headers.append('sec-ch-ua-mobile', '?0');
    headers.append('sec-ch-ua-platform', '"Windows"');

    if (cookie) {
      headers.append('Cookie', cookie);
    }

    const body = `action=ondnc&id=${encodeURIComponent(cnNumber)}&producto=${encodeURIComponent(producto)}&pais=${encodeURIComponent(pais)}`;

    try {
      const response = await fetch(this.API_URL, {
        method: 'POST',
        headers: headers,
        body: body,
        redirect: 'follow'
      });

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }

      //------------------------------------------------------------------------------------------PROCESO DE EXTRACCION DE DATOS------------------------------------------------------------------------------------------


      const html = this.htmlParser(await response.text()).querySelectorAll('table tbody tr');
      const tnts: Tnt[] = [];

      html.forEach((row: any) => {
        const jsonObject = new Tnt(
          row.querySelector('td:nth-child(1)').textContent,
          row.querySelector('td:nth-child(2)').textContent,
          row.querySelector('td:nth-child(3)').textContent,
          row.querySelector('td:nth-child(4)').textContent,
        );
        tnts.push(jsonObject);
      });

      return tnts;
    } catch (error) {
      console.error('Error tracking package:', error);
      throw error;
    }
  }

  htmlParser(html: string): any {
    const parser = new DOMParser();
    const doc = parser.parseFromString(html, 'text/html');
    return doc;
  }

}
