package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Goods;
import util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;

/*
 * 查询goods表中的所有商品
 * */
@WebServlet("/browseGoods")
public class GoodsBrowseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Goods> list = new ArrayList<>();
        try {
            String sql = "select id,name,introduce,stock,unit,price,discount from goods";
            connection = DBUtil.getConnection(true);
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();//表中内容放在了resultSet中
            //解析结果集中的内容
            while (resultSet.next()) {//有数据
                Goods goods = this.extractGoods(resultSet);
                if (goods != null) {//解析成功
                    list.add(goods);//list添加解析过的goods
                }
            }
            System.out.println("浏览货物"+list);
            //把list转为json
            //方便将模型转换为JSON
            ObjectMapper mapper=new ObjectMapper();
            //将响应包推送给浏览器
            //PrintWriter 使用io流
            PrintWriter printWriter=resp.getWriter();
            //将list转化为字符串，并将当前json字符串填充到printWriter流中
            mapper.writeValue(printWriter,list);
            //给前端发数据的方法
            Writer writer=resp.getWriter();
            //把流响应发送给前端页面
            writer.write(printWriter.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }
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