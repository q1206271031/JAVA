package servlet;

import entity.Goods;
import util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 首先通过更新的id,进行查找该ID是否存在对应的商品
 * 如果不存在，更新失败
 * 如果存在，那么根据ID找到对应的货物，对该货物的属性进行修改
 * 随后将修改后的货物进行数据库的更新
 */

@WebServlet("/updateGoods")
public class UpdateGoodsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String goodsIdString = req.getParameter("goodsID");//绿色字体为前端的name
        int goosId = Integer.parseInt(goodsIdString);
        String name = req.getParameter("name");
        String introduce = req.getParameter("introduce");
        String stock = req.getParameter("stock");
        String unit = req.getParameter("unit");

        String price = req.getParameter("price");//字符串12.34
        double doublePrice = Double.parseDouble(price);//12.34
        int realPrice = new Double(doublePrice * 100).intValue();//整型1234
        String discount = req.getParameter("discount");

        //1.查看当前的goodsId是否存在
        Goods goods = this.getGoods(goosId);
        if (goods == null) {
            System.out.println("没有改商品");
            resp.sendRedirect("index.html");
        }else{
            //2.检查完之后如果存在对应的ID,那么进行删除
            goods.setName(name);
            goods.setIntroduce(introduce);
            goods.setStock(Integer.parseInt(stock));
            goods.setUnit(unit);
            goods.setPrice(Integer.parseInt(price));
            goods.setDiscount(Integer.parseInt(discount));

            //把查询的商品进行更新，随后对数据库进行操作
            // 把当前的goods进行更新
            boolean effect = this.modifyGoods(goods);
            if (effect) {
                System.out.println("更新成功");
                resp.sendRedirect("goodsbrowse.html");
            }else{
                System.out.println("更新失败");
                resp.sendRedirect("indedx.html");
            }
        }

    }

    private boolean modifyGoods(Goods goods) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean effect = false;
        try{
            String sql = "update goods set name=?,introduce=?,stock=?,unit=?,price=?,discount=? where id=?";
            connection = DBUtil.getConnection(true);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,goods.getName());
            preparedStatement.setString(2, goods.getIntroduce());
            preparedStatement.setInt(3, goods.getStock());
            preparedStatement.setString(4, goods.getUnit());
            preparedStatement.setInt(5, goods.getPriceInt());
            preparedStatement.setInt(6, goods.getDiscount());
            preparedStatement.setInt(7, goods.getId());
            effect = (preparedStatement.executeUpdate() == 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,preparedStatement,null);
        }
        return effect;
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
