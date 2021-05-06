package com.arca.repository;

import com.entity.Book;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {

    final JdbcTemplate jdbcTemplate;

    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> retrive(){
        String sql = "SELECT * FROM BOOK";

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) ->
                        new Book(
                                rs.getString("title"),
                                rs.getString("author"),
                                rs.getFloat("price")
                        )
        );
    }


}
