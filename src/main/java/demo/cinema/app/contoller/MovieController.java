package demo.cinema.app.contoller;

import demo.cinema.app.dto.request.NewMovieCreationRequest;
import demo.cinema.app.dto.request.UpdateMovieRequest;
import demo.cinema.app.dto.response.MovieResponse;
import demo.cinema.app.dto.response.NewMovieCreationResponse;
import demo.cinema.app.dto.response.UpdateMovieResponse;
import demo.cinema.app.model.Movie;
import demo.cinema.app.service.MovieService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<NewMovieCreationResponse> createNewMovie(
            @RequestBody NewMovieCreationRequest newMovieCreationRequest) {
        NewMovieCreationResponse createdMovie = movieService.createMovie(newMovieCreationRequest);
        return ResponseEntity.ok(new NewMovieCreationResponse(createdMovie.getMovieId()));
    }


    @PutMapping("updateMovie/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UpdateMovieResponse> updateMovie(@PathVariable Long id,
                                                                   @RequestBody UpdateMovieRequest updateMovieRequest) {
        UpdateMovieResponse updatedMovie = movieService.updateMovie(id, updateMovieRequest);
        return ResponseEntity.ok(new UpdateMovieResponse(updatedMovie.getMovieId()));
    }

    @GetMapping("/all-with-sessions")
    public ResponseEntity<List<MovieResponse>> getAllMoviesWithSessions() {
        List<MovieResponse> movies = movieService.getAllMoviesWithSessions();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/getAllMovies")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<MovieResponse>> getAllMovies(@RequestParam(required = false) String title,
                                                            @RequestParam(required = false) String genre) {
        List<MovieResponse> movies = movieService.getAllMovies(title, genre);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/getMovieById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable Long id) {
        MovieResponse movie = movieService.getMovieById(id);
        if (movie != null) {
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteMovieById/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteMovieById(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAllMovies")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteAllMovies() {
        movieService.deleteAllMovies();
        return ResponseEntity.noContent().build();
    }

}

