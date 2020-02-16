package commom;

import lombok.Getter;
import lombok.ToString;

//枚举类中构造方法默认私有的，而且反射无法拿到
@Getter
@ToString
public enum OrderStatus {
    PLAYING(1,"待支付"),OK(2,"支付完成");
    private int flg;
    private String desc;

    OrderStatus(int flg,String desc){
        this.flg = flg;
        this.desc = desc;
    }
    //浏览订单时
    public static OrderStatus valueOf(int flg){
        for (OrderStatus orderStatus :OrderStatus.values()){
            if (orderStatus.flg == flg) {
                return orderStatus;
            }
        }
        throw new RuntimeException("OrderStatus is not found");
    }
}

