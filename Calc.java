import java.math.BigDecimal;

/**
 * 计算抽象类
 */
public interface Calc {
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
     * 打印当前堆栈状态
     */
    void printCurStatus();

    void clear();
}
