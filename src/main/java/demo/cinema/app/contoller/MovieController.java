package demo.cinema.app.contoller;

import demo.cinema.app.dto.request.NewMovieCreationRequest;
import demo.cinema.app.dto.request.UpdateMovieRequest;
import demo.cinema.app.dto.response.MovieResponse;
import demo.cinema.app.dto.response.NewMovieCreationResponse;
import demo.cinema.app.dto.response.UpdateMovieResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<NewMovieCreationResponse> createNewMovie(@RequestBody NewMovieCreationRequest newMovieCreationRequest) {
        return null;
    }

    @PutMapping(value = "/{}")
    public ResponseEntity<UpdateMovieResponse> updateExistingMovie(@RequestBody UpdateMovieRequest updateMovieRequest) {
        return null;
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<MovieResponse>> getAllMovies(@RequestParam(required = false) String searchQuery) {
        return null;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable Long id) {
        return null;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteMovieById(@PathVariable Long id) {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllMovies() {
        return null;
    }

}
