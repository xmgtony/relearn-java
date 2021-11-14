package com.xmgtony.jvm;

import sun.misc.Launcher;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created on 2021/11/14.
 *
 * @author tony
 * email xmgtony@gmail.com
 * description 打印jvm启动时，启动类加载器BootstrapClassLoader，拓展类加载器ExtClassLoader，
 * 应用类加载器AppClassLoader加载了哪些路径下的jar包。
 */
public class JvmClassLoaderPrinter {
    public static void main(String[] args) {
        System.out.println("启动类加载器加载路径");
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL urL : urLs) {
            System.out.println("---> " + urL.toExternalForm());
        }
        System.out.println("拓展类加载器加载路径");
        URL[] extUrls = ((URLClassLoader)ClassLoader.getSystemClassLoader().getParent()).getURLs();
        for (URL urL : extUrls) {
            System.out.println("---> " + urL.toExternalForm());
        }
        System.out.println("应用类家加载器加载路径");
        URL[] appUrls = ((URLClassLoader)ClassLoader.getSystemClassLoader()).getURLs();
        for (URL urL : appUrls) {
            System.out.println("---> " + urL.toExternalForm());
        }
    }
}
