package com.alan.javatest.movies.data;

import com.alan.javatest.movies.model.Genre;
import com.alan.javatest.movies.model.Movie;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MovieRepositoryJDBCTest {

    private MovieRepositoryJDBC movieRepositoryJDBC;
    private DataSource dataSource;
    @Before
    public void setUp() throws Exception {
        dataSource = new DriverManagerDataSource("jdbc:h2:mem:test;MODE=MYSQL", "root", "root");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("sql-scripts/tests-data.sql"));

        movieRepositoryJDBC = new MovieRepositoryJDBC(jdbcTemplate);
    }

    @Test
    public void load_all_movies() throws SQLException {

        Collection<Movie>  movies = movieRepositoryJDBC.findAll();

        assertThat(movies, is(Arrays.asList(
                new Movie(1, "Dark Knight", 152, Genre.ACTION) ,
                new Movie(2, "Memento", 113, Genre.DRAMA) ,
                new Movie(3, "Matrix", 136, Genre.ACTION)
        )));
    }

    @Test
    public void load_movie_by_id() {
        Movie movie = movieRepositoryJDBC.findById(1);

        assertThat(movie, is(new Movie(1, "Dark Knight", 152, Genre.ACTION)));
    }

    @Test
    public void isnert_a_movie() {
        Movie movie = new Movie("Super 8", 112, Genre.THRILLER);
        movieRepositoryJDBC.saveOrUpdate(movie);

        Movie movieFromBd = movieRepositoryJDBC.findById(4);
        assertThat(movieFromBd, is(new Movie(4, "Super 8", 112, Genre.THRILLER)));
    }

    @After
    public void tearDown() throws Exception {
        final Statement s = dataSource.getConnection().createStatement();
        s.execute("drop all objects delete files");
    }
}