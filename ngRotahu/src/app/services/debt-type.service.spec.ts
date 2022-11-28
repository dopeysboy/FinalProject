import { TestBed } from '@angular/core/testing';

import { DebtTypeService } from './debt-type.service';

describe('DebtTypeService', () => {
  let service: DebtTypeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DebtTypeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
