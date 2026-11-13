package com.alan.javatest.movies.data;

import com.alan.javatest.movies.model.Genre;
import com.alan.javatest.movies.model.Movie;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

public class MovieRepositoryJDBCTest {

    @Test
    public void load_all_movies() throws SQLException {

        DataSource dataSource =
                new DriverManagerDataSource("jdbc:h2:mem:test;MODE=MYSQL", "root", "root");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("sql-scripts/tests-data.sql"));

        MovieRepositoryJDBC movieRepositoryJDBC = new MovieRepositoryJDBC(jdbcTemplate);

        Collection<Movie>  movies = movieRepositoryJDBC.findAll();

        assertThat(movies, CoreMatchers.is(Arrays.asList(
                new Movie(1, "Dark Knight", 152, Genre.ACTION) ,
                new Movie(2, "Memento", 113, Genre.DRAMA) ,
                new Movie(3, "Matrix", 136, Genre.ACTION)
        )));
    }
}