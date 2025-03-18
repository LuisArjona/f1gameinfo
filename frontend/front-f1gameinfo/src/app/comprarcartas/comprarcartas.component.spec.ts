import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComprarcartasComponent } from './comprarcartas.component';

describe('ComprarcartasComponent', () => {
  let component: ComprarcartasComponent;
  let fixture: ComponentFixture<ComprarcartasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ComprarcartasComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ComprarcartasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
