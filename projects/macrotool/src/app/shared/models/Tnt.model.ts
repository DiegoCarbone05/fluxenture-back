export class Tnt {
    date: string;
    plant:string;
    history:string;
    status:string;

    constructor(date: string, plant: string, history: string, status: string) {
        this.date = date;
        this.plant = plant;
        this.history = history;
        this.status = status;
    }
}

export enum ETrackingStatus {
    ADMITIDO,               // El correo recibió el paquete
    EN_PROCESO_DE_CLASIFICACION, 
    LLEGADA_A_CENTRO_LOGISTICO,
    EN_CAMINO_A_SUCURSAL_DESTINO,
    EN_PODER_DEL_CARTERO,   // Ya salió a reparto
    INTENTO_DE_ENTREGA,     // Fue pero no encontró a nadie
    DOMICILIO_CERRADO,
    PLAZO_VENCIDO_NO_RECLAMADO,
    ENTREGADO,              // Finalizado con éxito
    DEVUELTO_AL_REMITENTE   // Algo salió mal
}