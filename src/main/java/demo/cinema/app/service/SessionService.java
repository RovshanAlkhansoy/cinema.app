package demo.cinema.app.service;

import demo.cinema.app.enums.SessionType;
import demo.cinema.app.model.Movie;
import demo.cinema.app.model.Session;
import java.util.List;

public interface SessionService {

    List<Session> createSessionsForMovie(Movie movie, List<SessionType> sessionTypes);

}
