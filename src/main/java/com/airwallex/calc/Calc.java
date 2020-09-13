package com.airwallex.calc;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 计算抽象类
 *
 * 具体实现类,需要维护一个计算机的状态,并需要提供方法,对状态做操作,具体的操作,可以通过 Operation 子类做扩展
 *
 * 需要定义一个 数值存储泛型 <T> ,用于可能的数据类型替换?  暂先不做处理
 */
public interface Calc {

    //基本常量
    int MAX_UNDO = 2;
    int SCALE = 15;
    DecimalFormat DECIMALFORMAT = new DecimalFormat("0.##########");


    /**
     * 输入计算式,累计叠加
     * @param s 空格分割
     */
    void input(String s);

    /**
     * 返回计算结果
     * @return 当堆栈状态未达终态时,返回null
     */
    BigDecimal result();


    /**
     * 当前堆栈状态
     */
    String curStatus();

    /**
     * 清除操作,具体实现可以不支持,如不支持,可以直接抛出异常,或空处理
     */
    void clear();
    /**
     * 回退操作,具体实现可以不支持,如不支持,可以直接抛出异常,或空处理
     */
    void undo();
    /**
     * 当前数据堆栈大小
     * @return
     */
    int stackSize();

    /**
     * 从当前堆栈中出栈一个数据
     * @return
     */
    BigDecimal pop();

    /**
     * 入栈
     * @param item
     * @return
     */
    BigDecimal push(BigDecimal item);

}
