import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {Session} from "./session";
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";
import {Venue} from "../venue/venue";

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  constructor(private http: HttpClient) {
  }

  fetchSessions(): Observable<Session[]> {
    return this.http.get("/api/sessions")
      .pipe(
        map((apiResult: any) => {
          return apiResult._embedded.sessions.map((apiSession) => <Session>{
              title: apiSession.title,
              date: apiSession.date,
              venue: <Venue>{
                name: apiSession.venue.name,
                address: apiSession.venue.address
              },

            }
          );
        })
      );
  }
}
