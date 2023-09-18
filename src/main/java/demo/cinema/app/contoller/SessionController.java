package demo.cinema.app.contoller;

import demo.cinema.app.dto.request.NewSessionCreationRequest;
import demo.cinema.app.dto.request.UpdateSessionRequest;
import demo.cinema.app.dto.response.SessionResponse;
import demo.cinema.app.exception.SeatsOutOfRange;
import demo.cinema.app.service.SessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sessions")
@RequiredArgsConstructor
@Api(tags = "Sessions")
public class SessionController {

    private final SessionService sessionService;

    @PostMapping("/createSession")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation("Create a new session")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Session created successfully"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "Forbidden")
    })
    public ResponseEntity<SessionResponse> createSession(@RequestBody NewSessionCreationRequest newSessionCreationRequest)
            throws SeatsOutOfRange {
        SessionResponse createdSession = sessionService.createSession(newSessionCreationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSession);
    }

    @GetMapping("/by-movie/{movieId}")
    @ApiOperation("Get sessions by movie ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Sessions retrieved successfully"),
            @ApiResponse(code = 404, message = "Movie not found")
    })
    public ResponseEntity<List<SessionResponse>> getSessionsByMovie(@PathVariable Long movieId) {
        List<SessionResponse> sessionResponses = sessionService.getSessionByMovie(movieId);
        return ResponseEntity.ok(sessionResponses);
    }

    @GetMapping("/by-date/{date}")
    @ApiOperation("Get sessions by date range")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Sessions retrieved successfully"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<List<SessionResponse>> getSessionsByDate(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date to
    ) {
        List<SessionResponse> sessionResponses = sessionService.getSessionByDate(from, to);
        return ResponseEntity.ok(sessionResponses);
    }

    @GetMapping("/by-hall/{hallId}")
    @ApiOperation("Get sessions by hall ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Sessions retrieved successfully"),
            @ApiResponse(code = 404, message = "Hall not found")
    })
    public ResponseEntity<List<SessionResponse>> getSessionsByHall(@PathVariable Long hallId) {
        List<SessionResponse> sessionResponses = sessionService.getSessionsByHall(hallId);
        return ResponseEntity.ok(sessionResponses);
    }

    @GetMapping("/by-movie-and-date")
    @ApiOperation("Get sessions by movie ID and date range")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Sessions retrieved successfully"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<List<SessionResponse>> getSessionsByMovieAndDate(
            @RequestParam Long movieId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to) {
        List<SessionResponse> sessionResponses = sessionService.getSessionByMovieAndDate(movieId, from, to);
        return ResponseEntity.ok(sessionResponses);
    }

    @GetMapping("/by-movie-and-hall")
    @ApiOperation("Get sessions by movie ID and hall ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Sessions retrieved successfully"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<List<SessionResponse>> getSessionsByMovieAndHall(
            @RequestParam Long movieId,
            @RequestParam Long hallId) {
        List<SessionResponse> sessionResponses = sessionService.getSessionsByMovieAndHall(movieId, hallId);
        return ResponseEntity.ok(sessionResponses);
    }

    @GetMapping("/available-seats/{sessionId}")
    @ApiOperation("Get available seats count for a session")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Available seats count retrieved successfully"),
            @ApiResponse(code = 404, message = "Session not found")
    })
    public ResponseEntity<Integer> getAvailableSeatsCount(@PathVariable Long sessionId) {
        int availableSeatsCount = sessionService.getAvailableSeatsCount(sessionId);
        return ResponseEntity.ok(availableSeatsCount);
    }

    @GetMapping("/getSessionById/{sessionId}")
    @ApiOperation("Get a session by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Session retrieved successfully"),
            @ApiResponse(code = 404, message = "Session not found")
    })
    public ResponseEntity<SessionResponse> getSessionById(@PathVariable Long sessionId) {
        SessionResponse session = sessionService.getSessionById(sessionId);
        return ResponseEntity.ok(session);
    }

    @GetMapping("/getAllSessions")
    @ApiOperation("Get all sessions")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Sessions retrieved successfully")
    })
    public ResponseEntity<List<SessionResponse>> getAllSessions() {
        List<SessionResponse> sessions = sessionService.getAllSessions();
        return ResponseEntity.ok(sessions);
    }

    @PutMapping("/updateSession/{sessionId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation("Update a session by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Session updated successfully"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Session not found"),
            @ApiResponse(code = 403, message = "Access forbidden")
    })
    public ResponseEntity<SessionResponse> updateSession(
            @PathVariable Long sessionId,
            @RequestBody UpdateSessionRequest updateSessionRequest
    ) {
        SessionResponse updatedSession = sessionService.updateSession(sessionId, updateSessionRequest);
        return ResponseEntity.ok(updatedSession);
    }

    @DeleteMapping("/deleteSessionById/{sessionId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation("Delete a session by ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Session deleted successfully"),
            @ApiResponse(code = 404, message = "Session not found"),
            @ApiResponse(code = 403, message = "Access forbidden")
    })
    public ResponseEntity<Void> deleteSessionById(@PathVariable Long sessionId) {
        sessionService.deleteSessionById(sessionId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAllSessions")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation("Delete all sessions")
    @ApiResponses({
            @ApiResponse(code = 204, message = "All sessions deleted successfully"),
            @ApiResponse(code = 403, message = "Access forbidden")
    })
    public ResponseEntity<Void> deleteAllSessions() {
        sessionService.deleteAllSessions();
        return ResponseEntity.noContent().build();
    }

}
