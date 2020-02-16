package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xpath.internal.operations.Or;
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
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/browseOrder")
public class OrderBrowseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("browseOrder");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        //1、根据当前的用户ID，进行订单的查找。//写成函数
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("user");

        System.out.println("account.getId()"+account.getId());

        //2、查询结果可能是多个订单
        List<Order> orderList = this.queryOrder(account.getId());
        System.out.println("orderList:"+orderList);//没有打印，说明查询数据库失败
        if (orderList == null) {
            System.out.println("订单列表为空");
        } else {
            ObjectMapper mapper = new ObjectMapper();
            PrintWriter pw = resp.getWriter();
            mapper.writeValue(pw, orderList);
            Writer writer = resp.getWriter();
            writer.write(pw.toString());
        }
    }

    public List<Order> queryOrder (Integer accountId) {
        List<Order> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sql = this.getSql("@query_order_by_account");
            connection = DBUtil.getConnection(true);
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, accountId);
            resultSet = preparedStatement.executeQuery();

            Order order = null;
            while (resultSet.next()) {
                //1.订单解析
                if (order == null) {
                    order = new Order();
                    this.extractOrder(order, resultSet);
                    list.add(order);
                }
                String orderId = resultSet.getString("order_id");
                if(!orderId.equals(order.getId())){
                    //不同的订单
                    order = new Order();
                    this.extractOrder(order,resultSet);
                    list.add(order);
                }

                //2.订单项解析
                  //错误的
//                OrderItem orderItem = new OrderItem();
//                this.extractOrderItem(resultSet);
                OrderItem orderItem = this.extractOrderItem(resultSet);
                order.orderItemList.add(orderItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }


        System.out.println("浏览订单:"+list);
        return list;
    }

    private void extractOrder(Order order, ResultSet resultSet) throws SQLException {
        order.setId(resultSet.getString("order_id"));
        order.setAccount_id(resultSet.getInt("account_id"));
        order.setAccount_name(resultSet.getString("account_name"));
        order.setCreate_time(resultSet.getString("create_time"));
        order.setFinish_time(resultSet.getString("finish_time"));
        order.setActual_amount(resultSet.getInt("actual_amount"));
        order.setTotal_money(resultSet.getInt("total_money"));
        order.setOrder_status(OrderStatus.valueOf(resultSet.getInt("order_status")));
    }

    private OrderItem extractOrderItem(ResultSet resultSet) throws SQLException {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(resultSet.getInt("item_id"));
        orderItem.setGoodsId(resultSet.getInt("goods_id"));
        orderItem.setGoodsName(resultSet.getString("goods_name"));
        orderItem.setGoodsIntroduce(resultSet.getString("goods_introduce"));
        orderItem.setGoodsNum(resultSet.getInt("goods_num"));
        orderItem.setGoodsUnit(resultSet.getString("goods_unit"));
        orderItem.setGoodsPrice(resultSet.getInt("goods_price"));
        orderItem.setGoodsDiscount(resultSet.getInt("goods_discount"));
        return orderItem;
    }

    //3.判断查询的结果，如果是空的，说明没有订单


    //4.如果不是空的，那么将ist转为json，发送给前端



    //生成一条sql语句
    public String getSql(String sqlName) {
        System.out.println("sqlName:"+sqlName);
        //InputStream 是字节流  this.getClass获取当前的Class对象
        try (InputStream in = this.getClass()
                //获取类加载器
                .getClassLoader()
                //这个方法是用来获取配置文件的，方法传入的参数是一个路径
                .getResourceAsStream("script/" + sqlName.substring(1) + ".sql");
             // 从1 开始提取的原因是：sqlName: @query_order_by_account 去掉@符号
        ) {
            if (in == null) {
                throw new RuntimeException("load sql " + sqlName + " failed");
            } else {
                //把字节流转为字符流
                //InputStreamReader :字节流 通向字符流的桥梁
                try (InputStreamReader isr = new InputStreamReader(in);
                     //BufferedReader -> 从字符输入流中读取文本并缓冲字符
                     BufferedReader reader = new BufferedReader(isr)) {//isr字符流

                    StringBuilder stringBuilder = new StringBuilder();

                    stringBuilder.append(reader.readLine());

                    String line;
                    while (( line = reader.readLine()) != null) {
                        stringBuilder.append(" ").append(line);
                    }

                    System.out.println("value:" + stringBuilder.toString());
                    return stringBuilder.toString();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("load sql " + sqlName + " failed");
        }
    }
}
