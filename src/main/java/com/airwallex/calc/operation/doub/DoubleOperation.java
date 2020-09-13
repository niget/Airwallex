package com.airwallex.calc.operation.doub;

import com.airwallex.calc.Calc;
import com.airwallex.calc.operation.BaseOperation;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * 操作接口类
 */
public abstract  class DoubleOperation  extends BaseOperation {

    /**
     * 预检查
     * @param calc
     */
    @Override
    public void preCheck(Calc calc) {
        if(calc.stackSize() < 2){
            throw new RuntimeException("not enough params");
        }
    }
    /**
     * 具体计算操作
     * @param calc
     */
    public void doExec(Calc calc) {
        BigDecimal augend = calc.pop();
        BigDecimal cur = calc.pop();
        BigDecimal result = calc(cur,augend);
        calc.push(result);
    }

    /**
     * @param cur
     * @param augend
     * @return
     */
    public abstract BigDecimal calc(BigDecimal cur,BigDecimal augend);


}
