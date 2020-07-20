import { Money } from './money.model';

export interface Medicine {
    id: number;
    name: string;
    monthDuration: number;
    generic: boolean;
    minimumPrice: Money;
}