import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExpiryListComponent } from './expiry-list.component';

describe('ExpiryListComponent', () => {
  let component: ExpiryListComponent;
  let fixture: ComponentFixture<ExpiryListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExpiryListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExpiryListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
