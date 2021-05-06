package com;

import com.arca.repository.BookRepository;
import com.entity.Book;
import com.service.ExcelBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class StartApplication {

    @Autowired
    public BookRepository bookRepository;

    public static void main(String[] args) {

        SpringApplication.run(StartApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {


        ExcelBook excelBook = new ExcelBook();

//        List<Book> bookList = new ArrayList<>();
//        bookList.add(new Book("Head First Java", "Kathy Serria", 79));
//        bookList.add(new Book("Effective Java", "Joshua Bloch", 36));
//        bookList.add(new Book("Clean Code", "Robert martin", 42));
//        bookList.add(new Book("Thinking in Java", "Bruce Eckel", 35));


        List<Book> bookList = bookRepository.retrive();
        String excelFilePath = "NiceJavaBooks.xls";

        try {
            excelBook.writeExcel(bookList, excelFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
