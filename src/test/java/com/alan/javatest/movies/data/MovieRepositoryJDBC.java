package com.alan.javatest.movies.data;

import com.alan.javatest.movies.model.Genre;
import com.alan.javatest.movies.model.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Collection;
import java.util.Collections;

public class MovieRepositoryJDBC implements MovieRepository {
    private JdbcTemplate jdbcTemplate;

    public MovieRepositoryJDBC(JdbcTemplate jdbcTemplate) {
        // Corrección: Inicializar la variable de instancia
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Movie findById(long id) {
        // Corrección: Usar queryForObject para un solo resultado filtrando por id
        Object[] args = { id };
        return jdbcTemplate.queryForObject("select * from movies WHERE id = ?", args, movieMapper);
    }

    @Override
    public Collection<Movie> findAll() {
        // Agregamos ORDER BY id para garantizar el orden de los registros
        return jdbcTemplate.query("select * from movies ORDER BY id", movieMapper);
    }

    @Override
    public void saveOrUpdate(Movie movie) {

    }

    private static RowMapper<Movie> movieMapper = (rs, rowNum) -> new Movie(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getInt("minutes"),
            Genre.valueOf(rs.getString("genre"))
    );
}
