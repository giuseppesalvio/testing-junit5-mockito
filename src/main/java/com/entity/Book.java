package com.entity;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import lombok.Value;

@Value
public class Book {
    private String title;
    private String author;
    private float price;

    public static void writeBook(Book aBook, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue(aBook.getTitle());

        cell = row.createCell(1);
        cell.setCellValue(aBook.getAuthor());

        cell = row.createCell(2);
        cell.setCellValue(aBook.getPrice());
    }

    public static void writeHeaderBook(Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue("Title");

        cell = row.createCell(1);
        cell.setCellValue("Author");

        cell = row.createCell(2);
        cell.setCellValue("Price");
    }
}
