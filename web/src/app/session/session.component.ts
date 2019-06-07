import {Component, OnInit} from '@angular/core';
import {SessionService} from "./session.service";
import {Observable} from "rxjs";
import {Session} from "./session";

@Component({
  selector: 'app-session',
  templateUrl: './session.component.html',
  styleUrls: ['./session.component.scss']
})
export class SessionComponent implements OnInit {

  sessions$: Observable<Session[]>;

  constructor(private sessionService: SessionService) {
  }

  ngOnInit() {
    this.sessions$ = this.sessionService.fetchSessions();
  }

}
