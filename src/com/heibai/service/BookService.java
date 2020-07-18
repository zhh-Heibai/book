package com.heibai.service;

import com.heibai.pojo.Book;
import com.heibai.pojo.Page;

import java.util.List;

public interface BookService {
     void addBook(Book book);
     void deleteBookById(Integer id);
     void updateBook(Book book);
     Book queryBookById(Integer id);
     List<Book> queryBook();
     Page<Book> page(int pageNo, int pageSize);
     Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
