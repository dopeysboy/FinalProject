import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ThrivingComponent } from './thriving.component';

describe('ThrivingComponent', () => {
  let component: ThrivingComponent;
  let fixture: ComponentFixture<ThrivingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ThrivingComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ThrivingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
