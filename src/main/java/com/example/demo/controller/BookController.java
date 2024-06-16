package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.BookDao;
import com.example.demo.entity.Book;
import com.example.demo.entity.CId;
import com.example.demo.entity.CIds;
import com.example.demo.entity.CString;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class BookController {
    private final BookDao bookDao;

    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    // 模糊查找
    // 参数：{text：书名} 返回：书对象列表
    @PostMapping("/searchbook")
    public List<Book> searchbook(@RequestBody CString data) {
        System.out.println("Received search parameter: " + data.getText());
        return bookDao.searchBook(data);
    }

    // 获取书信息
    // 参数：{id：书id} 返回：书对象
    @PostMapping("/detail")
    public Book detail(@RequestBody CId id) {
        System.out.println("Received search parameter: " + id);
        return bookDao.findBook(id);
    }

    // 添加书
    // 参数：书对象 返回：非0成功、0失败
    @PostMapping("/newbook")
    public boolean newBook(@RequestBody Book book) {

        return bookDao.newBook(book);
    }

    // 添加书
    // 参数：书对象 返回：非0成功、0失败
    @PostMapping("/modbook")
    public boolean modBook(@RequestBody Book book) {

        return bookDao.modBook(book);
    }

    // 获取最新添加的24本书
    // 无参数 返回：书对象列表
    @PostMapping("/lastitems")
    public List<Book> lastItems() {

        return bookDao.latestItems();
    }

    // 查询书是否借出
    // 参数：{id：书id} 返回：1 未借出 0 已借出
    @PostMapping("/isborrowed")
    public int isBorrowed(@RequestBody CId id) {
        Integer status = bookDao.isBorrowed(id);
        if (status == 0 || status == null) {
            return 1;
        } else {
            return 0;
        }
    }

    // 借书
    // 参数：{id:书id,uid:用户id} 返回：1 成功、2失败
    @PostMapping("/borrow")
    public int borrow(@RequestBody CIds ids) {
        if (bookDao.borrow(ids) > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    // 查询用户借书的列表
    // 参数：用户id 返回：用户借书列表
    @PostMapping("/bbooks")
    public List<Book> bbooks(@RequestBody CId id) {
        return bookDao.bbooks(id);
    }

}
