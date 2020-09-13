package com.airwallex.calc.operation;

import com.airwallex.calc.Calc;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * 操作接口类
 */
public abstract class BaseOperation implements Operation {

    /**
     * 预检查
     */
    public abstract void preCheck(Calc calc);

    /**
     * 具体计算操作
     */
    public abstract void doExec(Calc calc);

    /**
     * 具体计算操作
     * @param
     */
    public void exec(Calc calc){
        //先做预检查
        preCheck(calc);
        //再做具体操作
        doExec(calc);
    }

    /**
     * 是否可以处理该符号
     * @return
     */
    public boolean beHandled(String key){
        return key.equals(getKey());
    }
}
