import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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

    public static final String SQRT = "sqrt";
    public static final String CLEAR = "clear";
    public static final String UNDO = "undo";
    public static final String QUIT = "quit";

    final List<String> operationList = Arrays.asList(new String[]{"+","-","*","/",});
    final List<String> singleOperationList = Arrays.asList(new String[]{SQRT});
    final List<String> commandList = Arrays.asList(new String[]{CLEAR,UNDO});

    int maxUndo = 2;
    int scale = 15;
    int displayScale = 10;
    DecimalFormat decimalFormat = new DecimalFormat("0.##########");
    Stack[] undoStackArr = new Stack[maxUndo];

    Stack<BigDecimal> operationStack = new Stack<BigDecimal>();

    public static void main(String[] args) {
        ReversePolishNotationCalculator calc = new ReversePolishNotationCalculator();
        String prefixIdentification = "input:  ";
        Scanner scan = new Scanner(System.in);
        System.out.print(prefixIdentification);
        while(scan.hasNextLine()) {
            String s = scan.nextLine();
            calc.input(s);
            System.out.print(prefixIdentification);
        }
    }

    /**
     * 输入计算式,累计叠加
     * @param s 空格分割
     */
    public void input(String s) {
        if(s.equals(QUIT)) {
            System.exit(0);
        }
        calculate(s);
    }

    /**
     * 返回计算结果
     *
     * @return 当堆栈状态未达终态时, 返回null
     */
    @Override
    public BigDecimal result() {
        if(operationStack.size() == 1){

        }
        return operationStack.peek();
    }

    /**
     * 打印当前堆栈状态
     */
    public void printCurStatus() {
        StringBuffer buffer = new StringBuffer();
        for(BigDecimal i:operationStack){
            buffer.append(decimalFormat.format(i)).append(" ");
        }

        System.out.println("stack: "+ buffer);
    }

    @Override
    public void clear() {
        operationStack.clear();
    }

    public void calculate(String s) {
        ArrayList<String> input = new ArrayList<String>();
        Collections.addAll(input, s.trim().split(" "));
        input.removeAll(Arrays.asList(null, ""));


        String var = "";
        int startIndex = 0;
        for(int i = startIndex; i < input.size(); i++) {
            String n = input.get(i);
            if(isOperator(n)) {
                if(operationStack.size() > 1) {
                    operationStack.push(doOperation(n));
                } else {
                    //错误提示
                    err(i, n);
                    printCurStatus();
                    //未处理数据提示
                    promptLast(input, i);
                    return;
                }
            }else if (isSingleOperator(n)) {
                if(operationStack.size() > 0) {
                    operationStack.push(doSingleOperation(n));
                } else {
                    //错误提示
                    System.out.println("not enough params");
                    printCurStatus();
                    //未处理数据提示
                    promptLast(input, i);
                    return;
                }
            } else if (isCommand(n)) {
                doCommand(n);
            } else {
                BigDecimal d = getValue(n);
                push(d);
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
        System.out.println("(the "+input.subList(i+1,input.size())+" were not pushed on to the stack due to the previous error)");
    }

    /**
     * 错误位置提示
     * @param i
     * @param n
     */
    private void err(int i, String n) {
        System.out.println("operator "+n+" (position: "+(i*2+1)+"): insucient parameters");
    }


    public BigDecimal doOperation(String s) {
        saveUndo(operationStack);
        BigDecimal a = pop();
        BigDecimal b = pop();
        switch (s) {
            case "+":
                return b.add(a).setScale(scale);
            case "-":
                return b.subtract(a).setScale(scale);
            case "*":
                return b.multiply(a).setScale(scale);
            case "/":
                return b.divide(a).setScale(scale);
            default:
                throw new RuntimeException("not support double operation");
        }
    }
    public void doCommand(String s) {
        switch (s) {
            case UNDO:
                operationStack = undo();
                return;
            case CLEAR:
                operationStack.clear();
                return;
            default:
                return;
        }
    }
    public BigDecimal doSingleOperation(String s) {
        BigDecimal a = pop();
        switch (s) {
            case SQRT:
                return sqrt(a);
            default:
                throw new RuntimeException("not support single operation");
        }
    }

    public BigDecimal getValue(String s) {
        try {
            return new BigDecimal(s).setScale(scale);
        } catch (NumberFormatException ex) {
            System.out.printf("\t%s not found.\n", s);
            throw ex;
        }
    }


    public boolean isOperator(String s) {
        return operationList.contains(s);
    }
    public boolean isSingleOperator(String s) {
        return singleOperationList.contains(s);
    }
    public boolean isCommand(String s) {
        return commandList.contains(s);
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
    public Stack<BigDecimal> undo(){
        Stack<BigDecimal> last = undoStackArr[undoStackArr.length-1];
        //一起向后移动
        for (int i = undoStackArr.length-1; i > 0 ; i--) {
            undoStackArr[i] = undoStackArr[i-1];
        }
        return last;
    }

    /**
     * 开方
     * @param value
     * @return
     */
    public BigDecimal sqrt(BigDecimal value){
        return sqrt(value,scale);
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
}