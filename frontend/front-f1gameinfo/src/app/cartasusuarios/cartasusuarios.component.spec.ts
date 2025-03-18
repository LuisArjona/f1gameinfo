import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CartasusuariosComponent } from './cartasusuarios.component';

describe('CartasusuariosComponent', () => {
  let component: CartasusuariosComponent;
  let fixture: ComponentFixture<CartasusuariosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CartasusuariosComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CartasusuariosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
