package demo.cinema.app.service.impl;

import demo.cinema.app.dto.request.NewSessionCreationRequest;
import demo.cinema.app.dto.request.UpdateSessionRequest;
import demo.cinema.app.dto.response.SessionResponse;
import demo.cinema.app.exception.HallNotFound;
import demo.cinema.app.exception.MovieNotFound;
import demo.cinema.app.exception.SeatsOutOfRange;
import demo.cinema.app.exception.SessionNotFound;
import demo.cinema.app.model.Hall;
import demo.cinema.app.model.Movie;
import demo.cinema.app.model.Session;
import demo.cinema.app.repository.HallRepository;
import demo.cinema.app.repository.MovieRepository;
import demo.cinema.app.repository.SessionRepository;
import demo.cinema.app.service.SessionService;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;
    private final MovieRepository movieRepository;
    private final HallRepository hallRepository;

    @Override
    public SessionResponse createSession(NewSessionCreationRequest newSessionCreationRequest) throws SeatsOutOfRange {
        Movie movie = movieRepository.findById(newSessionCreationRequest.getMovieId())
                .orElseThrow(MovieNotFound::new);

        Hall hall = hallRepository.findById(newSessionCreationRequest.getHallId())
                .orElseThrow(HallNotFound::new);

        int availableSeatsCount = newSessionCreationRequest.getAvailableSeatsCount();
        int hallCapacity = hall.getCapacity();

        if (availableSeatsCount <= 0 || availableSeatsCount > hallCapacity) {
            throw new SeatsOutOfRange("Available seats count is not within the hall's capacity");
        }

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
    public List<SessionResponse> getSessionByMovie(Long movieId) {
        List<Session> sessions = sessionRepository.findByMovie(movieId);
        return sessions.stream().map(this::mapSessionToSessionResponse).toList();
    }


    @Override
    public List<SessionResponse> getSessionByDate(Date from, Date to) {
        List<Session> sessions = sessionRepository.findByDate(from, to);
        return sessions.stream().map(this::mapSessionToSessionResponse).toList();
    }


    @Override
    public List<SessionResponse> getSessionsByHall(Long hallId) {
        List<Session> sessions = sessionRepository.findByHall(hallId);
        return sessions.stream().map(this::mapSessionToSessionResponse).toList();
    }


    @Override
    public List<SessionResponse> getSessionByMovieAndDate(Long movieId, Date from, Date to) {
        List<Session> sessions = sessionRepository.findByMovieAndDate(movieId, from, to);
        return sessions.stream().map(this::mapSessionToSessionResponse).toList();
    }


    @Override
    public List<SessionResponse> getSessionsByMovieAndHall(Long movieId, Long hallId) {
        List<Session> sessions = sessionRepository.findByMovieAndHall(movieId, hallId);
        return sessions.stream().map(this::mapSessionToSessionResponse).toList();
    }


    @Override
    public Integer getAvailableSeatsCount(Long sessionId) {
        Session session = sessionRepository.findById(sessionId).orElseThrow(SessionNotFound::new);
        return session.getAvailableSeatsCount();
    }


    @Override
    public List<SessionResponse> getAllSessions() {
        List<Session> sessions = sessionRepository.findAll();
        return sessions.stream()
                .map(this::mapSessionToSessionResponse).toList();
    }

    @Override
    public SessionResponse getSessionById(Long sessionId) {
        Optional<Session> optionalSession = sessionRepository.findById(sessionId);
        return optionalSession.map(session -> SessionResponse.builder()
                        .id(session.getId())
                        .movieId(session.getMovie().getId())
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

    private SessionResponse mapSessionToSessionResponse(Session session) {
        return SessionResponse.builder()
                .id(session.getId())
                .movieId(session.getMovie().getId())
                .hallId(session.getHall().getId())
                .sessionType(session.getSessionType())
                .showtime(session.getShowtime())
                .availableSeatsCount(session.getAvailableSeatsCount())
                .build();
    }

}
