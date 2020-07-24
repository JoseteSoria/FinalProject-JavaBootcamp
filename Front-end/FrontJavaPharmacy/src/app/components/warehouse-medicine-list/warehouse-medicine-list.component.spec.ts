import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WarehouseMedicineListComponent } from './warehouse-medicine-list.component';

describe('WarehouseMedicineListComponent', () => {
  let component: WarehouseMedicineListComponent;
  let fixture: ComponentFixture<WarehouseMedicineListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WarehouseMedicineListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WarehouseMedicineListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
