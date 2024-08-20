import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EaxmResultFormComponent } from './eaxm-result-form.component';

describe('EaxmResultFormComponent', () => {
  let component: EaxmResultFormComponent;
  let fixture: ComponentFixture<EaxmResultFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EaxmResultFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EaxmResultFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
