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

public class ClientBookServlet extends BaseServlet {
    private BookService bookService=new BookServiceImpl();

    /**\
     * 实现分页的功能
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
        String url="client/bookServlet?action=page";
        page.setUrl(url);
        request.setAttribute("page",page);
        //4.请求转发到
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);

    }
    protected void pageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求的参数pageNo、pageSize、min和max
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(request.getParameter("min"), 0);
        int max = WebUtils.parseInt(request.getParameter("max"), Integer.MAX_VALUE);
        //2.调用bookService.pageByPrice(pageNo,pageSize,min,max)得到Page对象
        Page<Book> page=bookService.pageByPrice(pageNo,pageSize,min,max);
        //3.保存到request域中
        StringBuilder stringBuilder = new StringBuilder("client/bookServlet?action=pageByPrice");
        //如果有最小价格的参数，则追加到分页条的地址参数中
        if (request.getParameter("min")!=null){
            stringBuilder.append("&min=").append(request.getParameter("min"));
        }
        //如果有最大价格的参数，则追加到分页条的地址参数中
        if (request.getParameter("max")!=null){
            stringBuilder.append("&max=").append(request.getParameter("max"));
        }
        page.setUrl(stringBuilder.toString());
        request.setAttribute("page",page);
        //4.请求转发到
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);

    }

}
