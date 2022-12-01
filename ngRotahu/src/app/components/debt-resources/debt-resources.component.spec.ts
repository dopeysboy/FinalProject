import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DebtResourcesComponent } from './debt-resources.component';

describe('DebtResourcesComponent', () => {
  let component: DebtResourcesComponent;
  let fixture: ComponentFixture<DebtResourcesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DebtResourcesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DebtResourcesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
