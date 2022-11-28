import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserDebtComponent } from './user-debt.component';

describe('UserDebtComponent', () => {
  let component: UserDebtComponent;
  let fixture: ComponentFixture<UserDebtComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserDebtComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserDebtComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
