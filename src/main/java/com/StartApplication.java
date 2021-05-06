package com;

import com.entity.Book;
import com.service.ExcelBook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class StartApplication {

    public static void main(String[] args) {

        SpringApplication.run(StartApplication.class, args);
        ExcelBook excelBook = new ExcelBook();

        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("Head First Java", "Kathy Serria", 79));
        bookList.add(new Book("Effective Java", "Joshua Bloch", 36));
        bookList.add(new Book("Clean Code", "Robert martin", 42));
        bookList.add(new Book("Thinking in Java", "Bruce Eckel", 35));

        String excelFilePath = "NiceJavaBooks.xls";

        try {
            excelBook.writeExcel(bookList, excelFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
