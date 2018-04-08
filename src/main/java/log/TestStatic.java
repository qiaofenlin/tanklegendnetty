package log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
//import log.aaa;
/**
 * @Created by  qiao
 * @date 18-3-8 下午9:33
 */

public class TestStatic {
    public static void main(String[] args) throws Exception {
        String value ="aaa";
        Class clazz = Class.forName("log."+value);
        Constructor constructor = clazz.getConstructor();
        System.out.println(clazz.getSimpleName());

        aaa a =new aaa();
//        if () {
//            System.out.println("==========");
//        }
//        a = (aaa) constructor.newInstance();
//
//        Method channelRead = clazz.getDeclaredMethod("dis", String.class);
//        channelRead.invoke(a, "aaaaa");
        Class cl = Class.forName("log.aaa");
        Method method = cl.getDeclaredMethod("dis", String.class);
        method.invoke(cl.newInstance(), "---111111---");

    }
}
