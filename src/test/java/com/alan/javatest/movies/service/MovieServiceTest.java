package com.alan.javatest.movies.service;

import com.alan.javatest.movies.data.MovieRepository;
import com.alan.javatest.movies.model.Movie;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.alan.javatest.movies.model.Genre.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {

    @InjectMocks
    MovieService movieService;

    @Mock
    MovieRepository movieRepository;

    @Before
    public void setUp() {
        movieService = new MovieService(movieRepository);
        when(movieRepository.findAll()).thenReturn(Arrays.asList(
                new Movie(1, "Dark Knight", 152, ACTION),
                new Movie(2, "Memento", 113, THRILLER),
                new Movie(3, "There's Something About Mary", 119, COMEDY),
                new Movie(4, "Super 8", 112, THRILLER),
                new Movie(5, "Scream", 111, HORROR),
                new Movie(6, "Home Alone", 103, COMEDY),
                new Movie(7, "Matrix", 136, ACTION)
        ));
    }

    @Test
    public void returnMoviesByGenre() {
//    MovieRepository movieRepository = Mockito.mock(MovieRepository.class);
        Collection<Movie> movies = movieService.findMoviesByGenre(COMEDY);
        List<Integer> movieIds = movies.stream().map(Movie::getId).collect(Collectors.toList());

        assertThat(movieIds, is(Arrays.asList(3, 6)));
    }
}