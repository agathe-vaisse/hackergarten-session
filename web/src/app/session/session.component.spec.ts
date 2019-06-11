import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {SessionComponent} from './session.component';
import {SessionService} from "./session.service";
import {Subject} from "rxjs";
import {Session} from "./session";

describe('SessionComponent =>', () => {
  let component: SessionComponent;
  let fixture: ComponentFixture<SessionComponent>;
  let dom: any;
  let sessionServiceSpy: jasmine.SpyObj<SessionService>;
  const sessionsSubject = new Subject<Session[]>();

  beforeEach(async(() => {
    sessionServiceSpy = jasmine.createSpyObj<SessionService>(['fetchSessions']);
    sessionServiceSpy.fetchSessions.and.returnValue(sessionsSubject.asObservable());

    TestBed.configureTestingModule({
      declarations: [SessionComponent],
      providers: [
        {provide: SessionService, useValue: sessionServiceSpy}
      ]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SessionComponent);
    component = fixture.componentInstance;
    dom = fixture.debugElement.nativeElement;
    fixture.detectChanges();
  });

  describe('when there are no sessions =>', () => {

    it('displays a message saying so', () => {
      expect(dom.querySelector('#zero-session').textContent).toBe('No sessions found')
    });
  });

  describe('when there are sessions =>', () => {
    const sessions = [
      <Session>{title: 'Session #1', date: 1559935851, venue: 'Pivotal', address: '75009 Paris'},
      <Session>{title: 'Session #2', date: 1559935852, venue: 'Pivotal', address: '75009 Paris'}
    ];

    beforeEach(() => {
      sessionsSubject.next(sessions);
      fixture.detectChanges();
    });

    it('displays the lists', () => {
      const items = Array.from<Node>(dom.querySelectorAll('#sessions li'));
      expect(items.map(item => item.textContent.trim()))
        .toContain('Session #1 at Pivotal', 'Session #2 at Pivotal')
    });
  })

})
;
