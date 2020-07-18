package com.heibai.web;

import com.heibai.pojo.Book;
import com.heibai.pojo.Page;
import com.heibai.service.BookService;
import com.heibai.service.impl.BookServiceImpl;
import com.heibai.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {
    private BookService bookService=new BookServiceImpl();

    /**
     * 处理分页功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求的pageNo和pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);

        //2.调用bookService.page(pageNo,pageSize)得到Page对象
        Page<Book> page=bookService.page(pageNo,pageSize);
        //3.保存到request域中
        String url="manager/bookServlet?action=page";
        page.setUrl(url);
        request.setAttribute("page",page);
        //4.请求转发到
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);

    }
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //将表单项注入book
//        pageNo始终加1，如果大于pageTotal 则是最后一页
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 0);
        pageNo+=1;
        Book book= WebUtils.copyParamToBean(request.getParameterMap(),new Book());
//        System.out.println("注入后"+book);
//        request.setAttribute("name",book.getName());
//        request.setAttribute("price",book.getPrice());
//        request.setAttribute("author",book.getAuthor());
//        request.setAttribute("sales",book.getSales());
//        request.setAttribute("stock",book.getStock());
            bookService.addBook(book);
            response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNo="+pageNo);
    }
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        bookService.deleteBookById(Integer.parseInt(request.getParameter("id")));
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNo="+request.getParameter("pageNo"));

    }
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book=WebUtils.copyParamToBean(request.getParameterMap(),new Book());
        bookService.updateBook(book);
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNo="+request.getParameter("pageNo"));
    }
    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //获取要修改的图书
       Book book=bookService.queryBookById(Integer.parseInt(request.getParameter("id")));
       request.setAttribute("book",book);
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request,response);

    }
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> books = bookService.queryBook();
        request.setAttribute("books",books);
        //请求转发
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }
}
