-- 常见商品表
CREATE TABLE IF NOT EXISTS s_goods_detail (
  id int not null auto_increment,
  goods_name varchar(45) not null comment '商品名称',
  logo varchar(150) not null default '' comment '商品logo',
  sm_logo varchar(150) not null default '' comment '商品缩略图logo',
  price decimal(10,2) not null default '0.00' comment '商品价格',
  goods_desc text comment '商品描述',
  is_on_sale tinyint not null default '1' comment '是否上架：1：上架，0：下架',
  is_delete tinyint  not null default '0' comment '是否已经删除，1：已经删除 0：未删除',
  addtime TIMESTAMP  not null comment '添加时间',
  good_inventory int not NULL DEFAULT 0 COMMENT '商品库存',
  primary key (id)
)engine=InnoDB default charset=utf8;

CREATE INDEX idx_name on s_goods_detail(goods_name);


-- 创建订单表
CREATE TABLE IF NOT EXISTS order_detail (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  goods_id int not null DEFAULT 0 COMMENT '商品id',
  create_time TIMESTAMP DEFAULT now() COMMENT '订单创建时间',
  user_id BIGINT COMMENT '创建订单的用户',
  status TINYINT NOT NULL DEFAULT 0 COMMENT '订单状态 0有效 1待支付 2订单取消 3订单完成',
  mobile VARCHAR(12) COMMENT '联系方式',
  expressNo VARCHAR(20) COMMENT '快递号',
  PRIMARY KEY (id)
)ENGINE = InnoDB default charset=utf8;
create index idx_user_id on order_detail(user_id);