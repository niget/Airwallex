package com.airwallex.calc.operation.command;

import com.airwallex.calc.Calc;

public class ClearOperation extends CommandOperation {


    /**
     * 获取操作符自身标识key,用于注册
     *
     * @return
     */
    @Override
    public String getKey() {
        return "clear";
    }


    @Override
    public void handle(Calc calc) {
        calc.clear();
    }
}
