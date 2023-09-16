package demo.cinema.app.contoller;

import demo.cinema.app.dto.request.NewSessionCreationRequest;
import demo.cinema.app.dto.request.UpdateSessionRequest;
import demo.cinema.app.dto.response.SessionResponse;
import demo.cinema.app.service.SessionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sessions")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @PostMapping("/createSession")
    public ResponseEntity<SessionResponse> createSession(@RequestBody NewSessionCreationRequest newSessionCreationRequest) {
        SessionResponse createdSession = sessionService.createSession(newSessionCreationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSession);
    }

    @GetMapping("/getSessionById/{sessionId}")
    public ResponseEntity<SessionResponse> getSessionById(@PathVariable Long sessionId) {
        SessionResponse session = sessionService.getSessionById(sessionId);
        return ResponseEntity.ok(session);
    }

    @GetMapping("/getAllSessions")
    public ResponseEntity<List<SessionResponse>> getAllSessions() {
        List<SessionResponse> sessions = sessionService.getAllSessions();
        return ResponseEntity.ok(sessions);
    }

    @PutMapping("/updateSession/{sessionId}")
    public ResponseEntity<SessionResponse> updateSession(
            @PathVariable Long sessionId,
            @RequestBody UpdateSessionRequest updateSessionRequest
    ) {
        SessionResponse updatedSession = sessionService.updateSession(sessionId, updateSessionRequest);
        return ResponseEntity.ok(updatedSession);
    }

    @DeleteMapping("/deleteSessionById/{sessionId}")
    public ResponseEntity<Void> deleteSessionById(@PathVariable Long sessionId) {
        sessionService.deleteSessionById(sessionId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAllSessions")
    public ResponseEntity<Void> deleteAllSessions() {
        sessionService.deleteAllSessions();
        return ResponseEntity.noContent().build();
    }

}
