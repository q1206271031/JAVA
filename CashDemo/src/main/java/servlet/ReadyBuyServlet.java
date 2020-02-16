package servlet;

import commom.OrderStatus;
import entity.Account;
import entity.Goods;
import entity.Order;
import entity.OrderItem;
import util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/pay")
public class ReadyBuyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String goodsIdAndNum = req.getParameter("goodsIdAndNum");
        //12-2,15-3
        String[] strings = goodsIdAndNum.split(",");
        //12-2  15-3

        List<Goods> goodsList = new ArrayList<>();
        for (String s : strings){
            String[] strings1 = s.split("-");
            //12:id  2:需要购买的商品数量
            Goods goods = this.getGoods(Integer.parseInt(strings1[0]));
            if(goods != null){
                goodsList.add(goods);
                goods.setBuyGoodsNum(Integer.valueOf(strings1[1]));
            }else{
                Writer writer = resp.getWriter();
                writer.write("<h2> 没有此货物 </h2>");
                throw new RuntimeException("商品购买失败");
            }
        }
        System.out.println("当前需要购买的内容"+goodsList);
        HttpSession session = req.getSession();
        Account account  = (Account) session.getAttribute("user");

        //生成订单
        Order order = new Order();
        order.setId(String.valueOf(System.currentTimeMillis()));
        order.setAccount_id(account.getId());
        order.setAccount_name(account.getUsername());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        order.setCreate_time(LocalDateTime.now().format(formatter));
        int totalMoney = 0;
        int actualMoney = 0;

        //遍历goods 每个货物都是一个orderItem
        for (Goods goods: goodsList){
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(order.getId());
            orderItem.setGoodsId(goods.getId());
            orderItem.setGoodsName(goods.getName());
            orderItem.setGoodsIntroduce(goods.getIntroduce());
            orderItem.setGoodsNum(goods.getBuyGoodsNum());
            orderItem.setGoodsUnit(goods.getUnit());
            orderItem.setGoodsPrice(goods.getPriceInt());
            orderItem.setGoodsDiscount(goods.getDiscount());
            //添加了一个订单项
            order.orderItemList.add(orderItem);

            int currentMoney = goods.getBuyGoodsNum()*goods.getPriceInt();
            totalMoney += currentMoney;//当遍历完goodList.
            actualMoney += currentMoney * goods.getDiscount()/100;
        }
        order.setTotal_money(totalMoney);
        order.setActual_amount(actualMoney);
        order.setOrder_status(OrderStatus.PLAYING);
        //记录当前的session保存Order信息到buyGoodsServlet
        HttpSession session1 = req.getSession();
        session1.setAttribute("order", order);
        session1.setAttribute("goodsList", goodsList);


        //如果是跳转到另一个网页的话，对应的数据不好拿到，所以在这里直接打印网页
        //通过响应体对前端传入数据
        resp.getWriter().println("<html>") ;
        resp.getWriter().println("<p>"+"【用户名称】:"+order.getAccount_name()+"</p>");
        resp.getWriter().println("<p>"+"【订单编号】:"+order.getId()+"</p>");
        resp.getWriter().println("<p>"+"【订单状态】:"+order.getOrder_statusDesc().getDesc()+"</p>");
        resp.getWriter().println("<p>"+"【创建时间】:"+order.getCreate_time()+"</p>");

        resp.getWriter().println("<p>"+"编号  "+"名称  "+"数量  "+"单位  "+"单价(元)  "+"</p>");
        resp.getWriter().println("<ol>");
        for (OrderItem orderItem:order.orderItemList){
            resp.getWriter().println("<li>"+orderItem.getGoodsName()+" "+orderItem.getGoodsNum()+" "+
              orderItem.getGoodsUnit()+" "+orderItem.getGoodsPrice()+"</li>");
        }
        resp.getWriter().println("</ol>");
        resp.getWriter().println("<p>"+"【总金额】:"+order.getTotal_money()+"</p>");
        resp.getWriter().println("<p>"+"【优惠金额】:"+order.getDiscount()+"</p>");
        resp.getWriter().println("<p>"+"【应支付金额】:"+order.getActual_amount()+"</p>");
        //这个标签<a href = > 只会以get方法请求，所以buyGoodsServlet的doGet方法
        resp.getWriter().println("<a href=\"buyGoodsServlet\">确认</a>");
        resp.getWriter().println("<a href= \"index.html\">取消</a>");
        resp.getWriter().println("</html>");
    }

    private Goods getGoods(int goodsId){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Goods goods = null;
        try{
            String sql = "select * from goods where id=?";
            connection = DBUtil.getConnection(true);
            preparedStatement = connection.prepareStatement(sql);//预编译
            preparedStatement.setInt(1,goodsId);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                goods = this.extractGoods(resultSet);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,preparedStatement,resultSet);
        }
        return goods;
    }

    private Goods extractGoods(ResultSet resultSet) throws SQLException {
        Goods goods = new Goods();
        goods.setId(resultSet.getInt("id"));
        goods.setName(resultSet.getString("name"));
        goods.setIntroduce(resultSet.getString("introduce"));
        goods.setStock(resultSet.getInt("stock"));
        goods.setUnit(resultSet.getString("unit"));
        goods.setPrice(resultSet.getInt("price"));
        goods.setDiscount(resultSet.getInt("discount"));
        return goods;
    }


}
