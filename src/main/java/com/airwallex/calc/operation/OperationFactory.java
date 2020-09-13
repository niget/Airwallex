package com.airwallex.calc.operation;

import com.airwallex.calc.operation.command.ClearOperation;
import com.airwallex.calc.operation.command.UndoOperation;
import com.airwallex.calc.operation.doub.AddOperation;
import com.airwallex.calc.operation.doub.DivideOperation;
import com.airwallex.calc.operation.doub.MultiplyOperation;
import com.airwallex.calc.operation.doub.SubOperation;
import com.airwallex.calc.operation.single.SqrtOperation;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 */
public class OperationFactory {

    /**
     * map直接映射?  还是 list, 遍历执行test方法,再决定由谁处理, 哪个更好?
     */
    private static Map<String,Operation> operationMap = new ConcurrentHashMap<>();

    static{

        //统一注册操作符
        register(new SqrtOperation());
        register(new AddOperation());
        register(new DivideOperation());
        register(new SubOperation());
        register(new MultiplyOperation());
        register(new ClearOperation());
        register(new UndoOperation());

    }


    private static void register(Operation operation){
        if(operation == null){
            throw new RuntimeException("operation is null  ");
        }
        if(operationMap.containsKey(operation.getKey())){
            throw new RuntimeException("key : " + operation.getKey() + " already exists ");
        }
        // TODO 需要处理并发注册问题,但目前注册顺序由jvm static代码块自行保证
        operationMap.put(operation.getKey(),operation);
    }


    /**
     * 根据操作符,获取计算方法
     * @param value
     * @return
     */
    public static Operation getOperation(String value){
        Operation operation = operationMap.get(value);
        return operation;
    }
}
