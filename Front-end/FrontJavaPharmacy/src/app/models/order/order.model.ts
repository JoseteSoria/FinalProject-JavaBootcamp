import { Money } from '../money.model';

export interface Order {
    id: number;
    date: string;
    totalPrice: Money;
}
