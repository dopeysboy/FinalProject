import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PublicCalculatorComponent } from './public-calculator.component';

describe('PublicCalculatorComponent', () => {
  let component: PublicCalculatorComponent;
  let fixture: ComponentFixture<PublicCalculatorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PublicCalculatorComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PublicCalculatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
