package com.airwallex.calc.operation.doub;

import com.airwallex.calc.Calc;

import java.math.BigDecimal;

public class DivideOperation extends DoubleOperation {


    /**
     * 获取操作符自身标识key,用于注册
     *
     * @return
     */
    @Override
    public String getKey() {
        return "/";
    }

    /**
     * @param cur
     * @param augend
     * @return
     */
    @Override
    public BigDecimal calc(BigDecimal cur, BigDecimal augend) {
        return cur.divide(augend).setScale(Calc.SCALE);
    }
}
