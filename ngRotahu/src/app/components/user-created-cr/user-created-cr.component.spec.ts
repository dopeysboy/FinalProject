import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserCreatedCrComponent } from './user-created-cr.component';

describe('UserCreatedCrComponent', () => {
  let component: UserCreatedCrComponent;
  let fixture: ComponentFixture<UserCreatedCrComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserCreatedCrComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserCreatedCrComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
