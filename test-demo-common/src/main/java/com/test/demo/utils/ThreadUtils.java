package com.test.demo.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadUtils {

    private ThreadUtils(){}

    private static class SingletonHolder{
        private static  ExecutorService INSTANCE = Executors.newCachedThreadPool();
    }

    public static final ExecutorService getInstance(){
        return SingletonHolder.INSTANCE;
    }
}
