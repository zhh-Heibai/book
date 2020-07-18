package com.heibai.web;

import com.google.gson.Gson;
import com.heibai.pojo.Book;
import com.heibai.pojo.Cart;
import com.heibai.pojo.CartItem;
import com.heibai.service.BookService;
import com.heibai.service.impl.BookServiceImpl;
import com.heibai.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet {

    private BookService bookService=new BookServiceImpl();

    /**
     * 修改商品数量
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("进阿里");
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        int count = WebUtils.parseInt(request.getParameter("count"), 1);
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart!=null) {
            cart.updateItem(id,count);
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

    /**
     * 清空购物车
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void clearCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart!=null){
            cart.clear();
            response.sendRedirect(request.getHeader("Referer"));

        }

    }

    /**
     * 删除商品
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart!=null){
        cart.deleteItem(id);
        response.sendRedirect(request.getHeader("Referer"));

        }
    }

    /**
     * 添加商品
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = WebUtils.parseInt(request.getParameter("id"),0);
        Book book = bookService.queryBookById(id);
        CartItem cartItem=new CartItem(id,book.getName(),1,book.getPrice(),book.getPrice());
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart==null) {
            cart=new Cart();
            request.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        //System.out.println(cart);
        request.setAttribute("cartName",book.getName());
        request.setAttribute("cartCount",cart.getTotalCount());
        request.getSession().setAttribute("lastName",cartItem.getName());
        //重定向为商品列表页面
        //System.out.println(request.getHeader("Referer"));
        response.sendRedirect(request.getHeader("Referer"));
    }

    /**
     * ajax请求添加商品项到购物车
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxAddItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = WebUtils.parseInt(request.getParameter("id"),0);
        Book book = bookService.queryBookById(id);
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart==null) {
            cart=new Cart();
            request.getSession().setAttribute("cart",cart);
        }
        CartItem cartItem=null;

        if (book.getStock()>0){
            cartItem=new CartItem(id,book.getName(),1,book.getPrice(),book.getPrice());
            cart.addItem(cartItem);
            request.getSession().setAttribute("lastName",cartItem.getName());
            Gson gson = new Gson();
            Map<String,Object> resultMap=new HashMap<>();
            resultMap.put("totalCount",cart.getTotalCount());
            resultMap.put("lastName",cartItem.getName());
            String mapJsonString = gson.toJson(resultMap);
            response.getWriter().write(mapJsonString);
        }


    }
}
