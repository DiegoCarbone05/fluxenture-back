import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StatusChip } from './status-chip';

describe('StatusChip', () => {
  let component: StatusChip;
  let fixture: ComponentFixture<StatusChip>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [StatusChip]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StatusChip);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
