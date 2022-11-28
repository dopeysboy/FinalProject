import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PublicCalulatorComponent } from './public-calulator.component';

describe('PublicCalulatorComponent', () => {
  let component: PublicCalulatorComponent;
  let fixture: ComponentFixture<PublicCalulatorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PublicCalulatorComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PublicCalulatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
