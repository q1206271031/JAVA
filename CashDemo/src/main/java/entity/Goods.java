package entity;

import lombok.Data;

@Data
public class Goods {
    private Integer id;
    private String name;
    private String introduce;
    private Integer stock;
    private String unit;
    private Integer price;
    private Integer discount;//存入数据库为整数  88 0.88要除以100

    private Integer buyGoodsNum;//记录需要购买当前商品的数量

    public double getPrice() {
        return price *1.0 / 100;
    }
    public int getPriceInt(){
        return price;
    }


}
