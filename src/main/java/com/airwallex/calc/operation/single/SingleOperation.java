package com.airwallex.calc.operation.single;

import com.airwallex.calc.Calc;
import com.airwallex.calc.operation.BaseOperation;

import java.math.BigDecimal;

/**
 * 单参数操作符
 */
public abstract class SingleOperation extends BaseOperation {

    /**
     * 预检查
     */
    public void preCheck(Calc calc) {
        if(calc.stackSize() < 1){
            throw new RuntimeException("not enough params");
        }
    }

    public void doExec(Calc calc) {
        BigDecimal a = calc.pop();
        BigDecimal result = calc(a);
        calc.push(result);
    }


    public abstract BigDecimal calc(BigDecimal value);


}
