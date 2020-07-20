import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicineOrderedListComponent } from './medicine-ordered-list.component';

describe('MedicineOrderedListComponent', () => {
  let component: MedicineOrderedListComponent;
  let fixture: ComponentFixture<MedicineOrderedListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MedicineOrderedListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MedicineOrderedListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
