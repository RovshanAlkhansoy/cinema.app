package demo.cinema.app.service.impl;

import demo.cinema.app.dto.request.NewMovieCreationRequest;
import demo.cinema.app.dto.request.UpdateMovieRequest;
import demo.cinema.app.dto.response.MovieResponse;
import demo.cinema.app.enums.SessionType;
import demo.cinema.app.exception.MovieNotFound;
import demo.cinema.app.model.Movie;
import demo.cinema.app.model.Session;
import demo.cinema.app.repository.MovieRepository;
import demo.cinema.app.service.MovieService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public MovieResponse createMovie(NewMovieCreationRequest newMovieCreationRequest) {
        Movie movie = new Movie();
        movie.setTitle(newMovieCreationRequest.getTitle());
        movie.setGenre(newMovieCreationRequest.getGenre());
        movie.setDuration(newMovieCreationRequest.getDuration());
        movie.setReleaseDate(newMovieCreationRequest.getReleaseDate());
        movie.setRating(newMovieCreationRequest.getRating());

        // Create and associate sessions with the movie
        List<SessionType> sessionTypes = newMovieCreationRequest.getSessionTypes().stream()
                .map(this::mapSessionTypeFromString)
                .toList();

        List<Session> sessions = sessionTypes.stream()
                .map(sessionType -> createSessionForMovie(movie, sessionType))
                .toList();

        movie.setSessions(sessions);

        Movie savedMovie = movieRepository.save(movie);

        return mapMovieToMovieResponse(savedMovie);
    }


    private Session createSessionForMovie(Movie movie, SessionType sessionType) {
        Session session = new Session();
        session.setSessionType(sessionType);
        session.setMovie(movie);

        return session;
    }


    @Override
    public MovieResponse updateMovie(Long id, UpdateMovieRequest updateMovieRequest) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            movie.setTitle(updateMovieRequest.getTitle());
            movie.setGenre(updateMovieRequest.getGenre());
            movie.setDuration(updateMovieRequest.getDuration());
            movie.setReleaseDate(updateMovieRequest.getReleaseDate());
            movie.setRating(updateMovieRequest.getRating());

            Movie updatedMovie = movieRepository.save(movie);

            return mapMovieToMovieResponse(updatedMovie);
        } else {
            throw new MovieNotFound();
        }
    }

    @Override
    public List<MovieResponse> getAllMoviesWithSessions() {
        List<Movie> movies = movieRepository.findAllMoviesWithSessions();
        return movies.stream()
                .map(this::mapMovieToMovieResponse)
                .toList();
    }

    @Override
    public List<MovieResponse> getAllMovies(String titleQuery, String genreQuery) {
        List<Movie> movies;
        if ((titleQuery != null && !titleQuery.isEmpty()) || (genreQuery != null && !genreQuery.isEmpty())) {
            movies = movieRepository.findByTitleIgnoreCaseContainingOrGenreIgnoreCaseContaining(titleQuery, genreQuery);
        } else {

            movies = movieRepository.findAll();
        }
        return movies.stream()
                .map(this::mapMovieToMovieResponse)
                .toList();
    }


    @Override
    public MovieResponse getMovieById(Long id) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            return mapMovieToMovieResponse(movie);
        } else {
            throw new MovieNotFound();
        }
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public void deleteAllMovies() {
        movieRepository.deleteAll();
    }

    private SessionType mapSessionTypeFromString(String sessionTypeString) {
        try {
            return SessionType.valueOf(sessionTypeString.toUpperCase());
        } catch (IllegalArgumentException e) {
            // Handle invalid session type string
            throw new IllegalArgumentException("Invalid session type entered: " + sessionTypeString);
        }
    }

    private MovieResponse mapMovieToMovieResponse(Movie movie) {
        MovieResponse response = new MovieResponse();
        response.setId(movie.getId());
        response.setTitle(movie.getTitle());
        response.setGenre(movie.getGenre());
        response.setDuration(movie.getDuration());
        response.setReleaseDate(movie.getReleaseDate());
        response.setRating(movie.getRating());
        return response;
    }

}
