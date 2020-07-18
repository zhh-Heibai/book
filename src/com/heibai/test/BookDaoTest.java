package com.heibai.test;

import com.heibai.dao.BookDao;
import com.heibai.dao.impl.BookDaoImpl;
import com.heibai.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;

public class BookDaoTest {
    private BookDao bookDao=new BookDaoImpl();
    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"入坑321312","恒",new BigDecimal(999),100,10,null));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(24);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(26,"入坑weqeweq","恒",new BigDecimal(999),100,10,null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(20));
    }

    @Test
    public void queryBook() {
        for (Book book:bookDao.queryBook()
             ) {
            System.out.println(book);;
        }
    }
    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());

    }

    @Test
    public void queryForPageItems() {
        for (Book book:bookDao.queryForPageItems(0,7)
        ) {
            System.out.println(book);;
        }
    }
    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(100,1000));
    }

    @Test
    public void queryForPageItemsByPrice() {
        for (Book book:bookDao.queryForPageItemsByPrice(0,7,100,1000)
        ) {
            System.out.println(book);;
        }
    }

}