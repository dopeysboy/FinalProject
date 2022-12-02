import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreditResourceCrudComponent } from './credit-resource-crud.component';

describe('CreditResourceCrudComponent', () => {
  let component: CreditResourceCrudComponent;
  let fixture: ComponentFixture<CreditResourceCrudComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreditResourceCrudComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreditResourceCrudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
