package demo.cinema.app.service.impl;

import demo.cinema.app.enums.SessionType;
import demo.cinema.app.model.Movie;
import demo.cinema.app.model.Session;
import demo.cinema.app.repository.SessionRepository;
import demo.cinema.app.service.SessionService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;

    @Override
    public List<Session> createSessionsForMovie(Movie movie, List<SessionType> sessionTypes) {
        List<Session> sessions = new ArrayList<>();

        for (SessionType sessionType : sessionTypes) {
            Session session = new Session();
            session.setSessionType(sessionType);
            session.setMovie(movie);

            sessions.add(session);
        }

        // Save the sessions to the database
        return sessionRepository.saveAll(sessions);
    }

}
