package com.xmgtony.jvm;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created on 2021/11/14.
 *
 * @author tony
 * email xmgtony@gmail.com
 * description 通过classloader的addURL方法添加类路径，使其可以被JVM加载。
 */
public class JvmClassLoaderAddURL {
    public static void main(String[] args) {
        String appPath = "file:/relearn-java/src";
        URLClassLoader urlClassLoader = (URLClassLoader) JvmClassLoaderAddURL.class.getClassLoader();
        try {
            Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            addURL.setAccessible(true);
            URL url = new URL(appPath);
            addURL.invoke(urlClassLoader, url);

            // 然后可以加载类进来，可以通过Class.forName()方法。那么类加载器就会搜索我们添加的路径
            Class.forName("com.xmgtony.jvm.HelloWorld");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
