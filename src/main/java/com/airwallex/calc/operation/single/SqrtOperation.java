package com.airwallex.calc.operation.single;

import com.airwallex.calc.Calc;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class SqrtOperation extends SingleOperation{

    @Override
    public BigDecimal calc(BigDecimal value) {
        return sqrt(value, Calc.SCALE);
    }


    public BigDecimal sqrt(BigDecimal value, int scale){
        BigDecimal num2 = BigDecimal.valueOf(2);
        int precision = 100;
        MathContext mc = new MathContext(precision, RoundingMode.HALF_UP);
        BigDecimal deviation = value;
        int cnt = 0;
        while (cnt < precision) {
            deviation = (deviation.add(value.divide(deviation, mc))).divide(num2, mc);
            cnt++;
        }
        deviation = deviation.setScale(scale, BigDecimal.ROUND_HALF_UP);
        return deviation;
    }

    /**
     * 获取操作符自身标识key,用于注册
     *
     * @return
     */
    @Override
    public String getKey() {
        return "sqrt";
    }
}
