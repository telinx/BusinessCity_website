CREATE TABLE IF NOT EXISTS s_goods_detail
(
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
