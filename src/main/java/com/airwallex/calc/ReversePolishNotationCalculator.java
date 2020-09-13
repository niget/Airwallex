package com.airwallex.calc;

import com.airwallex.calc.operation.Operation;
import com.airwallex.calc.operation.OperationFactory;

import java.math.BigDecimal;
import java.util.*;

/**
 * rpn计算引擎实现
 *
 *   1.用户输入
 *   2.操作枚举 e +, -, *, /, sqrt, undo, clear
 *   3.计算精度 保存15个小数位,显示10个
 *
 *   4.错误定位
 *
 */
public class ReversePolishNotationCalculator implements Calc{


    Stack[] undoStackArr = new Stack[MAX_UNDO];

    Stack<BigDecimal> operationStack = new Stack<>();


    /**
     * 输入计算式,累计叠加
     * @param s 空格分割
     */
    public void input(String s) {
        calculate(s);
    }


    public void calculate(String s) {
        ArrayList<String> input = new ArrayList<String>();
        Collections.addAll(input, s.trim().split(" "));
        input.removeAll(Arrays.asList(null, ""));

        int startIndex = 0;
        for(int i = startIndex; i < input.size(); i++) {
            String n = input.get(i);

            //获取操作符
            Operation operation = OperationFactory.getOperation(n);

            //暂定不是操作符,就是数据,其他异常则直接跑出
            if(operation==null){
                BigDecimal d = getValue(n);
                push(d);
                continue;
            }

            try{
                operation.exec(this);
            }catch (Exception e){
                err(i, n);
                printCurStatus();
                //未处理数据提示
                promptLast(input, i);
                break;
            }
        }

        printCurStatus();
    }

    /**
     * 未处理数据提示
     * @param input
     * @param i
     */
    private void promptLast(ArrayList<String> input, int i) {
        System.out.printf("(the %s were not pushed on to the stack due to the previous error) \n",input.subList(i+1,input.size()));
    }

    /**
     * 错误位置提示
     * @param i
     * @param n
     */
    private void err(int i, String n) {
        System.out.printf("operator %s (position: %s): insucient parameters \n",n,i*2+1);
    }

    /**
     * 返回计算结果
     * @return 当堆栈状态未达终态时, 返回null
     */
    public BigDecimal result() {
        if(operationStack.size() == 1){
            return operationStack.peek();
        }
        return null;
    }


    public String curStatus() {
        StringBuffer buffer = new StringBuffer();
        for(BigDecimal i:operationStack){
            buffer.append(DECIMALFORMAT.format(i)).append(" ");
        }
        return buffer.toString();
    }

    /**
     * 打印当前堆栈状态
     */
    public void printCurStatus(){
        System.out.printf("stack: %s \n", curStatus());
    }

    public void clear() {
        operationStack.clear();
    }


    public BigDecimal getValue(String s) {
        try {
            return new BigDecimal(s).setScale(SCALE);
        } catch (NumberFormatException ex) {
            System.out.printf("%s not found.", s);
            throw ex;
        }
    }


    public BigDecimal push(BigDecimal item){
        //做状态保存
        saveUndo(operationStack);
        return operationStack.push(item);
    }
    public BigDecimal pop(){
        return operationStack.pop();
    }
    public void saveUndo(Stack<BigDecimal> operationStack){
        //向前腾挪位置,如果满了,就把最旧的记录顶掉
        for (int i = 0; i <undoStackArr.length-1 ; i++) {
            undoStackArr[i] = undoStackArr[i+1];
        }
        //最新数据放到最后
        undoStackArr[undoStackArr.length-1] = (Stack) operationStack.clone();
    }
    public void undo(){
        Stack<BigDecimal> last = undoStackArr[undoStackArr.length-1];
        //一起向后移动
        for (int i = undoStackArr.length-1; i > 0 ; i--) {
            undoStackArr[i] = undoStackArr[i-1];
        }
        operationStack = last;
    }

    /**
     * 当前数据堆栈大小
     *
     * @return
     */
    @Override
    public int stackSize() {
        return operationStack.size();
    }
}