import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {SessionComponent} from './session.component';
import {SessionService} from "./session.service";
import {Subject} from "rxjs";
import {Session} from "./session";
import {Venue} from "../venue/venue";

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
    const venue = <Venue>{name: 'Pivotal', address: '75009 Paris'};
    const sessions = [
      <Session>{title: 'Session #1', date: "2019-06-11T22:35:39.000+0000", venue: venue},
      <Session>{title: 'Session #2', date: "2019-09-11T22:35:39.000+0000", venue: venue}
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
