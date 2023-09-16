package demo.cinema.app.service.impl;

import demo.cinema.app.dto.request.NewSessionCreationRequest;
import demo.cinema.app.dto.request.UpdateSessionRequest;
import demo.cinema.app.dto.response.SessionResponse;
import demo.cinema.app.exception.HallNotFound;
import demo.cinema.app.exception.MovieNotFound;
import demo.cinema.app.exception.SessionNotFound;
import demo.cinema.app.model.Hall;
import demo.cinema.app.model.Movie;
import demo.cinema.app.model.Session;
import demo.cinema.app.repository.HallRepository;
import demo.cinema.app.repository.MovieRepository;
import demo.cinema.app.repository.SessionRepository;
import demo.cinema.app.service.SessionService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;
    private final MovieRepository movieRepository;
    private final HallRepository hallRepository;

    @Override
    public SessionResponse createSession(NewSessionCreationRequest newSessionCreationRequest) {
        Movie movie = movieRepository.findById(newSessionCreationRequest.getMovieId())
                .orElseThrow(MovieNotFound::new);

        Hall hall = hallRepository.findById(newSessionCreationRequest.getHallId())
                .orElseThrow(HallNotFound::new);

        Session session = Session.builder()
                .movie(movie)
                .hall(hall)
                .sessionType(newSessionCreationRequest.getSessionType())
                .showtime(newSessionCreationRequest.getShowtime())
                .availableSeatsCount(newSessionCreationRequest.getAvailableSeatsCount())
                .build();

        Session savedSession = sessionRepository.save(session);

        return SessionResponse.builder()
                .id(savedSession.getId())
                .availableSeatsCount(savedSession.getAvailableSeatsCount())
                .showtime(savedSession.getShowtime())
                .sessionType(savedSession.getSessionType())
                .hallId(savedSession.getHall().getId())
                .movieId(savedSession.getMovie().getId())
                .build();
    }

    @Override
    public SessionResponse updateSession(Long sessionId, UpdateSessionRequest updateSessionRequest) {
        Optional<Session> optionalSession = sessionRepository.findById(sessionId);
        return optionalSession.map(session -> {
            session.setAvailableSeatsCount(updateSessionRequest.getAvailableSeatsCount());
            session.setShowtime(updateSessionRequest.getShowtime());
            Session updatedSession = sessionRepository.save(session);

            return SessionResponse.builder()
                    .id(updatedSession.getId())
                    .availableSeatsCount(updatedSession.getAvailableSeatsCount())
                    .showtime(updatedSession.getShowtime())
                    .sessionType(updatedSession.getSessionType())
                    .hallId(updatedSession.getHall().getId())
                    .movieId(updatedSession.getMovie().getId())
                    .build();
        }).orElseThrow(SessionNotFound::new);
    }

    @Override
    public List<SessionResponse> getAllSessions() {
        List<Session> sessions = sessionRepository.findAll();
        return sessions.stream()
                .map(session -> SessionResponse.builder()
                        .id(session.getId())
                        .availableSeatsCount(session.getAvailableSeatsCount())
                        .showtime(session.getShowtime())
                        .sessionType(session.getSessionType())
                        .hallId(session.getHall().getId())
                        .movieId(session.getMovie().getId())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public SessionResponse getSessionById(Long sessionId) {
        Optional<Session> optionalSession = sessionRepository.findById(sessionId);
        return optionalSession.map(session -> SessionResponse.builder()
                        .id(session.getId())
                        .availableSeatsCount(session.getAvailableSeatsCount())
                        .showtime(session.getShowtime())
                        .sessionType(session.getSessionType())
                        .hallId(session.getHall().getId())
                        .movieId(session.getMovie().getId())
                        .build())
                .orElseThrow(SessionNotFound::new);
    }


    @Override
    public void deleteSessionById(Long sessionId) {
        Optional<Session> optionalSession = sessionRepository.findById(sessionId);
        optionalSession.ifPresent(sessionRepository::delete);
    }

    @Override
    public void deleteAllSessions() {
        sessionRepository.deleteAll();
    }

}
