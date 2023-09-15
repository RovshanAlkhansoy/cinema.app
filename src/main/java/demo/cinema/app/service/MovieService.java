package demo.cinema.app.service;

import demo.cinema.app.dto.request.NewMovieCreationRequest;
import demo.cinema.app.dto.request.UpdateMovieRequest;
import demo.cinema.app.dto.response.MovieResponse;
import demo.cinema.app.dto.response.NewMovieCreationResponse;
import demo.cinema.app.dto.response.UpdateMovieResponse;
import java.util.List;

public interface MovieService {

    NewMovieCreationResponse createMovie(NewMovieCreationRequest newMovieCreationRequest);

    UpdateMovieResponse updateMovie(Long id, UpdateMovieRequest updateMovieRequest);

    List<MovieResponse> getAllMovies(String titleQuery, String genreQuer);

    MovieResponse getMovieById(Long id);

    void deleteMovie(Long id);

    void deleteAllMovies();

}
