import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EaxmResultListComponent } from './eaxm-result-list.component';

describe('EaxmResultListComponent', () => {
  let component: EaxmResultListComponent;
  let fixture: ComponentFixture<EaxmResultListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EaxmResultListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EaxmResultListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
