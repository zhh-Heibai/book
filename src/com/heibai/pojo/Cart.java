package com.heibai.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**购物车对象
 * @author heibai
 * @date 2020/7/1 11:01
 */
public class Cart {
    private Map<Integer,CartItem> items=new LinkedHashMap<>();

    public Integer getTotalCount() {
        Integer totalCount=0;
        for (Map.Entry<Integer,CartItem> entry:items.entrySet()
             ) {
            totalCount+=entry.getValue().getCount();
        }

        return totalCount;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice=new BigDecimal(0);
        for (Map.Entry<Integer,CartItem> entry:items.entrySet()
        ) {
            totalPrice =totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    /**
     * 增加商品项
     * @param cartItem
     */
    public void addItem(CartItem cartItem){
        //修改数量、修改总价
        CartItem item = items.get(cartItem.getId());
        if (item==null){
            //之前没添加过
            items.put(cartItem.getId(),cartItem);
        }else {
            //之前添加过,修改数量，和总价
            item.setCount(item.getCount()+1);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }
    /**
     * 删除商品
     * @param id
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }
    /**
     * 清空购物车
     * @param
     */
    public void clear(){
        items.clear();
    }

    /**
     * 修改商品数量
     * @param id
     * @param count
     */
    public void updateItem(Integer id,Integer count){
        //先查看购物车中是否有此商品，如果有，则修改数量，总金额
        CartItem item = items.get(id);
        if (item!=null){
            item.setCount(count);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }
    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }


}
