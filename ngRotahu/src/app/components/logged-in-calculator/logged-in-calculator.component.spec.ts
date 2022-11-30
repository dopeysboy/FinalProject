import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoggedInCalculatorComponent } from './logged-in-calculator.component';

describe('LoggedInCalculatorComponent', () => {
  let component: LoggedInCalculatorComponent;
  let fixture: ComponentFixture<LoggedInCalculatorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoggedInCalculatorComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoggedInCalculatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
