package com.airwallex.calc.operation.command;

import com.airwallex.calc.Calc;
import com.airwallex.calc.operation.BaseOperation;

import java.math.BigDecimal;
import java.util.Stack;

/**
 */
public abstract class CommandOperation extends BaseOperation {
    /**
     * 暂时没有需要校验的
     * @param calc
     */
    public void preCheck(Calc calc) {
    }


    public void doExec(Calc calc) {
        handle(calc);
    }

    public abstract void handle(Calc calc);

}
