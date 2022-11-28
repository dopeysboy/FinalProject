import { TestBed } from '@angular/core/testing';

import { DebtLenderService } from './debt-lender.service';

describe('DebtLenderService', () => {
  let service: DebtLenderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DebtLenderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
