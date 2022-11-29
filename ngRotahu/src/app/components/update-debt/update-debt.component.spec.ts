import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateDebtComponent } from './update-debt.component';

describe('UpdateDebtComponent', () => {
  let component: UpdateDebtComponent;
  let fixture: ComponentFixture<UpdateDebtComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateDebtComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateDebtComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
