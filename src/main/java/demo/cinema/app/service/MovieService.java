package demo.cinema.app.service;

import demo.cinema.app.dto.request.NewMovieCreationRequest;
import demo.cinema.app.dto.request.UpdateMovieRequest;
import demo.cinema.app.dto.response.MovieResponse;
import java.util.List;

public interface MovieService {

    MovieResponse createMovie(NewMovieCreationRequest newMovieCreationRequest);

    MovieResponse updateMovie(Long id, UpdateMovieRequest updateMovieRequest);

    List<MovieResponse> getAllMoviesWithSessions();

    List<MovieResponse> getAllMovies(String titleQuery, String genreQuery);

    MovieResponse getMovieById(Long id);

    void deleteMovie(Long id);

    void deleteAllMovies();

}
