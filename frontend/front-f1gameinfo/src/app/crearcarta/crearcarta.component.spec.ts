import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CrearcartaComponent } from './crearcarta.component';

describe('CrearcartaComponent', () => {
  let component: CrearcartaComponent;
  let fixture: ComponentFixture<CrearcartaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CrearcartaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CrearcartaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
