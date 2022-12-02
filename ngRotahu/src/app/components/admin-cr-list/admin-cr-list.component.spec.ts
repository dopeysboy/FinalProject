import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminCrListComponent } from './admin-cr-list.component';

describe('AdminCrListComponent', () => {
  let component: AdminCrListComponent;
  let fixture: ComponentFixture<AdminCrListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminCrListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminCrListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
