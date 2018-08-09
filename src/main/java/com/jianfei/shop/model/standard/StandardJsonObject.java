package com.jianfei.shop.model.standard;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author pangjianfei
 * create time 2018/7/21
 * 定义返回给前端的标准的json对象的格式
 */
@Setter
@Getter
public class StandardJsonObject {

    public Object data;
    public boolean ret;
    public String errMsg;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isRet() {
        return ret;
    }

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
