import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PastTasksComponent } from './past-tasks.component';

describe('PastTasksComponent', () => {
  let component: PastTasksComponent;
  let fixture: ComponentFixture<PastTasksComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PastTasksComponent]
    });
    fixture = TestBed.createComponent(PastTasksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
