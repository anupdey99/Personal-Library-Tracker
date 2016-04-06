package com.adlabsbd.anup.personal_library_tracker.database;

/**
 * Created by Sudarshan Biswas on 19-Mar-16.
 */
public class Book {
    private int bookId;
    private String bookISBN;
    private String bookName;
    private String bookType;
    private String bookAuthor;
    private int bookQuantity;
    private String bookPublisher;
    private String bookEdition;
    private String bookLocation;

    public Book() { }

    public Book(String bookISBN, String bookName, int bookQuantity) {
        this.bookISBN = bookISBN;
        this.bookName = bookName;
        this.bookQuantity = bookQuantity;
        this.bookType = "Not Available";
        this.bookAuthor = "Not Available";
        this.bookPublisher = "Not Available";
        this.bookEdition = "Not Available";
        this.bookLocation = "Not Available";
    }

    public Book(int bookId, String bookISBN, String bookName, String bookType, String bookAuthor, int bookQuantity, String bookPublisher, String bookEdition, String bookLocation) {
        this.bookId = bookId;
        this.bookISBN = bookISBN;
        this.bookName = bookName;
        this.bookType = bookType;
        this.bookAuthor = bookAuthor;
        this.bookQuantity = bookQuantity;
        this.bookPublisher = bookPublisher;
        this.bookEdition = bookEdition;
        this.bookLocation = bookLocation;
    }

    public Book(String bookISBN, String bookName, String bookType, String bookAuthor, int bookQuantity, String bookPublisher, String bookEdition, String bookLocation) {
        this.bookISBN = bookISBN;
        this.bookName = bookName;
        this.bookType = bookType;
        this.bookAuthor = bookAuthor;
        this.bookQuantity = bookQuantity;
        this.bookPublisher = bookPublisher;
        this.bookEdition = bookEdition;
        this.bookLocation = bookLocation;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    public int getBookId() {
        return bookId;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getBookQuantity() {
        return bookQuantity;
    }

    public void setBookQuantity(int bookQuantity) {
        this.bookQuantity = bookQuantity;
    }

    public String getBookPublisher() {
        return bookPublisher;
    }

    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher = bookPublisher;
    }

    public String getBookEdition() {
        return bookEdition;
    }

    public void setBookEdition(String bookEdition) {
        this.bookEdition = bookEdition;
    }

    public String getBookLocation() {
        return bookLocation;
    }

    public void setBookLocation(String bookLocation) {
        this.bookLocation = bookLocation;
    }
}

