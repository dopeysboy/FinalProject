import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DrowningComponent } from './drowning.component';

describe('DrowningComponent', () => {
  let component: DrowningComponent;
  let fixture: ComponentFixture<DrowningComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DrowningComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DrowningComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
