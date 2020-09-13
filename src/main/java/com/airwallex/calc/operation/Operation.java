package com.airwallex.calc.operation;

import com.airwallex.calc.Calc;

/**
 * 操作接口类
 */
public interface Operation {


    /**
     * 具体计算操作
     * @param
     */
    void exec(Calc calc);

    /**
     * 获取操作符自身标识key,用于注册
     * @return
     */
    String getKey();

    /**
     * 是否可以处理该符号
     * @return
     */
    boolean beHandled(String key);
}
