import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DadosBancariosComponent } from './dados-bancarios.component';

describe('DadosBancariosComponent', () => {
  let component: DadosBancariosComponent;
  let fixture: ComponentFixture<DadosBancariosComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DadosBancariosComponent]
    });
    fixture = TestBed.createComponent(DadosBancariosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
