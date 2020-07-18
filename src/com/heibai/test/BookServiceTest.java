package com.heibai.test;

import com.heibai.pojo.Page;
import com.heibai.service.BookService;
import com.heibai.service.impl.BookServiceImpl;
import org.junit.Test;

public class BookServiceTest {
    private BookService bookService=new BookServiceImpl();
    @Test
    public void page() {
        System.out.println(bookService.page(1, Page.PAGE_SIZE));
    }
    @Test
    public void pageByPrice() {
        System.out.println(bookService.pageByPrice(1, Page.PAGE_SIZE,100,1000));
    }

}