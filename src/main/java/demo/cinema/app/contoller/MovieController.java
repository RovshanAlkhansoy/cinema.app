package demo.cinema.app.contoller;

import demo.cinema.app.dto.request.NewMovieCreationRequest;
import demo.cinema.app.dto.request.UpdateMovieRequest;
import demo.cinema.app.dto.response.MovieResponse;
import demo.cinema.app.service.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
@Api(tags = "Movies") // Add a tag for the controller
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Create a new movie")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Movie created successfully"),
            @ApiResponse(code = 400, message = "Bad request"),
    })
    public ResponseEntity<MovieResponse> createNewMovie(
            @RequestBody NewMovieCreationRequest newMovieCreationRequest) {
        MovieResponse createdMovie = movieService.createMovie(newMovieCreationRequest);
        return ResponseEntity.ok(createdMovie);
    }

    @PutMapping("/updateMovie/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Update a movie by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Movie updated successfully"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Movie not found"),
    })
    public ResponseEntity<MovieResponse> updateMovie(@PathVariable Long id,
                                                     @RequestBody UpdateMovieRequest updateMovieRequest) {
        MovieResponse updatedMovie = movieService.updateMovie(id, updateMovieRequest);
        return ResponseEntity.ok(updatedMovie);
    }

    @GetMapping("/all-with-sessions")
    @ApiOperation("Get all movies with sessions")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Movies retrieved successfully"),
    })
    public ResponseEntity<List<MovieResponse>> getAllMoviesWithSessions() {
        List<MovieResponse> movies = movieService.getAllMoviesWithSessions();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/getAllMovies")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get all movies")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Movies retrieved successfully"),
    })
    public ResponseEntity<List<MovieResponse>> getAllMovies(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre) {
        List<MovieResponse> movies = movieService.getAllMovies(title, genre);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/getMovieById/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get a movie by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Movie retrieved successfully"),
            @ApiResponse(code = 404, message = "Movie not found"),
    })
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable Long id) {
        MovieResponse movie = movieService.getMovieById(id);
        if (movie != null) {
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteMovieById/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Delete a movie by ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Movie deleted successfully"),
            @ApiResponse(code = 404, message = "Movie not found"),
    })
    public ResponseEntity<Void> deleteMovieById(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAllMovies")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Delete all movies")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Movies deleted successfully"),
    })
    public ResponseEntity<Void> deleteAllMovies() {
        movieService.deleteAllMovies();
        return ResponseEntity.noContent().build();
    }
}

