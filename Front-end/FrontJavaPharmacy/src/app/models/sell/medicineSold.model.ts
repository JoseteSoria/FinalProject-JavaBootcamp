import { Money } from '../money.model';

export interface MedicineSold {
    id: number;
    medicineId: number;
    medicineName: string;
    price: Money;
    salesId: number;
}
