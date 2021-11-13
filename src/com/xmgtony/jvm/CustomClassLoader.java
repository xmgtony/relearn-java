package com.xmgtony.jvm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Base64;

/**
 * Created on 2021/11/13.
 *
 * @author tony
 * email xmgtony@gmail.com
 * description 自定义类加载器, 加载HelloWorld类并实例化，然后调用其中sayHelloWorld方法
 * @see HelloWorld
 */
public class CustomClassLoader extends ClassLoader {
    public static void main(String[] args) {
        try {
            Object instance = new CustomClassLoader()
                    .findClass("com.xmgtony.jvm.HelloWorld")
                    .newInstance();
            Class<?> aClass = instance.getClass();
            Method method = aClass.getMethod("sayHelloWorld");
            method.invoke(instance);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 将HelloWorld.java编译成HelloWorld.class,然后将该文件内容编码为base64，
        // 变量source即存储了base64编码后的字节码内容。
        // 可以使用https://www.hitoy.org/tool/file_base64.php 在线对.class文件编码
        String source = "yv66vgAAADQAHAoABgAOCQAPABAIABEKABIAEwcAFAcAFQEABjxpbml0PgEAAygpVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBAA1zYXlIZWxsb1dvcmxkAQAKU291cmNlRmlsZQEAD0hlbGxvV29ybGQuamF2YQwABwAIBwAWDAAXABgBAAxIZWxsbyB3b3JsZCEHABkMABoAGwEAGmNvbS94bWd0b255L2p2bS9IZWxsb1dvcmxkAQAQamF2YS9sYW5nL09iamVjdAEAEGphdmEvbGFuZy9TeXN0ZW0BAANvdXQBABVMamF2YS9pby9QcmludFN0cmVhbTsBABNqYXZhL2lvL1ByaW50U3RyZWFtAQAHcHJpbnRsbgEAFShMamF2YS9sYW5nL1N0cmluZzspVgAhAAUABgAAAAAAAgABAAcACAABAAkAAAAdAAEAAQAAAAUqtwABsQAAAAEACgAAAAYAAQAAAAoAAQALAAgAAQAJAAAAJQACAAEAAAAJsgACEgO2AASxAAAAAQAKAAAACgACAAAADAAIAA0AAQAMAAAAAgAN";
        byte[] bytes = Base64.getDecoder().decode(source);
        return defineClass(name, bytes, 0, bytes.length);
    }
}
