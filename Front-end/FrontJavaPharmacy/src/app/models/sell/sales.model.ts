import { Money } from '../money.model';

export interface Sales {
    id: number;
    userId: number;
    patientId: number;
    date: string;
    totalPrice: Money;
}
