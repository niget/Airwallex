package com.airwallex.calc.operation.command;

import com.airwallex.calc.Calc;
import com.airwallex.calc.operation.doub.DoubleOperation;

import java.math.BigDecimal;

public class UndoOperation extends CommandOperation {


    /**
     * 获取操作符自身标识key,用于注册
     *
     * @return
     */
    @Override
    public String getKey() {
        return "undo";
    }


    @Override
    public void handle(Calc calc) {
        calc.undo();
    }
}
