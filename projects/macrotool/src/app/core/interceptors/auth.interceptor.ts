import { HttpInterceptorFn } from '@angular/common/http';

const TOKEN_KEY = 'flux_token';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const token = localStorage.getItem(TOKEN_KEY);
  // Solo clonamos si hay un token real
  if (token && token !== 'undefined') {
    const authReq = req.clone({
      setHeaders: { Authorization: `Bearer ${token}` }
    });
    return next(authReq);
  }
  return next(req);
};
