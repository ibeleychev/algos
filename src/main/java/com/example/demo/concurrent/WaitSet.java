package com.example.demo.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WaitSet {

  public static void main(String[] args) throws Exception {
    var object = new WaitSet();
    var executorService = Executors.newSingleThreadExecutor();
    executorService.submit(object.doWork());
    TimeUnit.SECONDS.sleep(2L);
    object.doNotify();
    executorService.shutdown();
  }

  private Runnable doWork() {
    return () -> {
      synchronized (this) {
        try {
          System.out.println("Wait");
          wait();
          System.out.println("After wait");
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    };
  }

  private synchronized void doNotify() {
    System.out.println("Notify");
    notify();
  }
}
