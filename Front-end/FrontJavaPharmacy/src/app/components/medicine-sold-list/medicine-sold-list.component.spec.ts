import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicineSoldListComponent } from './medicine-sold-list.component';

describe('MedicineSoldListComponent', () => {
  let component: MedicineSoldListComponent;
  let fixture: ComponentFixture<MedicineSoldListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MedicineSoldListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MedicineSoldListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
