import { MonoTypeOperatorFunction } from "rxjs";

import { Money } from './money.model';

export interface WarehouseMedicine {
    id: number;
    name: string;
    monthDuration: number;
    generic: boolean;
    minimumPrice: Money;
    expirationDate: string;
    price: Money;
}
