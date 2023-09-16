package demo.cinema.app.service;

import demo.cinema.app.dto.request.NewSessionCreationRequest;
import demo.cinema.app.dto.request.UpdateSessionRequest;
import demo.cinema.app.dto.response.SessionResponse;
import demo.cinema.app.model.Session;
import java.util.List;

public interface SessionService {


    SessionResponse createSession(NewSessionCreationRequest newSessionCreationRequest);

    SessionResponse updateSession(Long sessionId, UpdateSessionRequest updateSessionRequest);

    List<SessionResponse> getAllSessions();

    SessionResponse getSessionById(Long sessionId);

    void deleteSessionById(Long sessionId);

    void deleteAllSessions();


}
