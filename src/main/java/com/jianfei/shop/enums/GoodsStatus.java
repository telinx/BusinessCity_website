package com.jianfei.shop.enums;

/**
 * @author pangjianfei
 * create time 2018/8/13
 * 商品状态枚举类型,枚举是绝对的单例，他的所有变量必须是private，构造方法必须是私有的
 */
public enum GoodsStatus {

    ON_SALE(0,"在销售"),
    OFF_SALE(1,"下架");

    private Integer status;
    private String desc;

    private GoodsStatus(Integer status,String desc) {
        this.status = status;
        this.desc = desc;
    }
}
