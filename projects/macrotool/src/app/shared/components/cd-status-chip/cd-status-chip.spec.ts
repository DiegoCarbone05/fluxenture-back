import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CdStatusChip } from './cd-status-chip';

describe('CdStatusChip', () => {
  let component: CdStatusChip;
  let fixture: ComponentFixture<CdStatusChip>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CdStatusChip]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CdStatusChip);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
