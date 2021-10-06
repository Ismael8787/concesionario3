import * as dayjs from 'dayjs';
import { IUser } from 'app/entities/user/user.model';
import { Coche } from '../coche/coche.model';

export interface IVenta {
  id?: number;
  fecha?: dayjs.Dayjs;
  numFactura?: string;
  precioTotal?: number;
  compradorId?: IUser | null;
  vendedorId?: IUser | null;
  coche?: number;
}

export class Venta implements IVenta {
  constructor(
    public id?: number,
    public fecha?: dayjs.Dayjs,
    public numFactura?: string,
    public precioTotal?: number,
    public compradorId?: IUser | null,
    public vendedorId?: IUser | null,
    public coche?: number
  ) {}
}

export function getVentaIdentifier(venta: IVenta): number | undefined {
  return venta.id;
}
