package com.chucan.javaBase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * @Author: Yeman
 * @CreatedDate: 2022-07-16-18:51
 * @Description:
 */
public class CallableTest {

    @Test
    public void futureTaskTest() throws ExecutionException, InterruptedException {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "this is callable return result!";
            }
        };

        FutureTask<String> futureTask = new FutureTask<>(callable);

        Thread thread = new Thread(futureTask);
        thread.start();

        System.out.println(futureTask.get());
    }

    @Test
    public void executorAndCallable() throws ExecutionException, InterruptedException {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "this is callable return result 2!";
            }
        };

        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<String> future = es.submit(callable);

        System.out.println(future.get());
    }

    //当执行多个Callable任务，有多个返回值时，我们可以创建一个Future的集合
    class MyCallableTask implements Callable<String> {
        private int id;
        public MyCallableTask(int id){
            this.id = id;
        }
        @Override
        public String call() throws Exception {
            for(int i = 0;i<5;i++){
                System.out.println("Thread"+ id);
                Thread.sleep(1000);
            }
            return "Result of callable: "+id;
        }
    }

    @Test
    public void callables(){
         ExecutorService exec = Executors.newCachedThreadPool();
         ArrayList<Future<String>> results = new ArrayList<Future<String>>();

         for (int i = 0; i < 5; i++) {
             results.add(exec.submit(new MyCallableTask(i)));
         }

         for (Future<String> fs : results) {
             if (fs.isDone()) {
                 try {
                     System.out.println(fs.get());
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
             } else {
                 System.out.println("MyCallableTask任务未完成！");
             }
         }
         exec.shutdown();
    }
}
