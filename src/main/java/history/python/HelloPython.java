package history.python;

import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

/**
 * @Created by  qiao
 * @date 18-4-23 下午4:32
 */

public class HelloPython {
    public static void main(String[] args) {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("/home/qiao/IdeaProjects/tanklegendnetty/src/main/java/log/hello.py");

        PyFunction pyFunction = interpreter.get("hello", PyFunction.class); // 第一个参数为期望获得的函数（变量）的名字，第二个参数为期望返回的对象类型
        int a =1;
        int b =2;
        PyObject pyObject = pyFunction.__call__(new PyInteger(a), new PyInteger(b)); // 调用函数

        System.out.println(pyObject);
    }
}
