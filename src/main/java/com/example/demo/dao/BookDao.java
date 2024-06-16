package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.Book;
import com.example.demo.entity.CId;
import com.example.demo.entity.CIds;
import com.example.demo.entity.CString;

public interface BookDao {
    List<Book> searchBook(CString data);

    Book findBook(CId id);

    boolean newBook(Book book);

    boolean modBook(Book book);

    boolean banBook(Book book);

    List<Book> latestItems();

    Integer isBorrowed(CId id);

    int borrow(CIds cids);

    List<Book> bbooks(CId id);

}
