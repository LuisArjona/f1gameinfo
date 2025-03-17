import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoguearComponent } from './loguear.component';

describe('LoguearComponent', () => {
  let component: LoguearComponent;
  let fixture: ComponentFixture<LoguearComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LoguearComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoguearComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
