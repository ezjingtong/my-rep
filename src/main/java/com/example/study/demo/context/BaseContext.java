//package com.example.study.demo.context;
//
//public class BaseContext {
//
//    public static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
//
//    public static void setCurrentId(int id) {
//        threadLocal.set(id);
//    }
//
//    public static int getCurrentId() {
//        return (int) threadLocal.get();
//    }
//
//    public static void removeCurrentId() {
//        threadLocal.remove();
//    }
//
//}
