import { TestBed } from '@angular/core/testing';

import { CreditResourceService } from './credit-resource.service';

describe('CreditResourceService', () => {
  let service: CreditResourceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreditResourceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
