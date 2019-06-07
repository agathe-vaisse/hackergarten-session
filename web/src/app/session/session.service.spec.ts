import {TestBed} from '@angular/core/testing';

import {SessionService} from './session.service';
import {HttpClientTestingModule, HttpTestingController} from "@angular/common/http/testing";
import {Session} from "./session";

describe('SessionService', () => {
  let httpMock: HttpTestingController;
  let service: SessionService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [SessionService]
    });

    httpMock = TestBed.get(HttpTestingController);
    service = TestBed.get(SessionService);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should retrieve sessions from the API', (done: DoneFn) => {
    const session = {
      "title": "Session #1",
      "date": 1559935851,
      "venue": "Somewhere",
      "address": "Over the rainbow"
    };
    const apiResult = {
      "_embedded": {
        "sessions": [
          {
            ...session, ...{
              "_links": {
                "self": {
                  "href": "http://localhost:8080/api/sessions/1"
                },
                "session": {
                  "href": "http://localhost:8080/api/sessions/1"
                }
              }
            }
          },
        ]
      }, "_links": {}, "page": {}
    };
    service.fetchSessions().subscribe((sessions: Session[]) => {
      expect(sessions.length).toEqual(1);
      expect(sessions[0]).toEqual(session);
      done();

    });
    const req = httpMock.expectOne(`/api/sessions`);
    expect(req.request.method).toBe("GET");
    req.flush(apiResult);
  });
});
