package com.example.demo.concurrent;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class DijkstraPhilosophers {
  private static final int NUMBER_OF_PHILOSOPHERS = 5;
  private static boolean stop = false;

  public static void main(String[] args) throws Exception {
    CountDownLatch latch = new CountDownLatch(1);
    Semaphore[] sticks = new Semaphore[NUMBER_OF_PHILOSOPHERS];

    for (int i = 0; i < NUMBER_OF_PHILOSOPHERS; i++) {
      sticks[i] = new Semaphore(1);
    }

    Thread[] philosophers = new Thread[NUMBER_OF_PHILOSOPHERS];
    for (int i = 0; i < NUMBER_OF_PHILOSOPHERS; i++) {
      if (i == NUMBER_OF_PHILOSOPHERS - 1) {
        philosophers[i] =
            new Thread(new Philosopher(latch, sticks[(i + 1) % NUMBER_OF_PHILOSOPHERS], sticks[i]));
      } else {
        philosophers[i] =
            new Thread(new Philosopher(latch, sticks[i], sticks[(i + 1) % NUMBER_OF_PHILOSOPHERS]));
      }
    }

    for (Thread philosopher : philosophers) {
      philosopher.start();
    }

    latch.countDown();
    TimeUnit.SECONDS.sleep(5);
    stop = true;
  }

  private static final class Philosopher implements Runnable {
    private final String id;
    private final CountDownLatch latch;
    private final Semaphore leftStick;
    private final Semaphore rightStick;

    public Philosopher(CountDownLatch latch, Semaphore leftStick, Semaphore rightStick) {
      this.id = UUID.randomUUID().toString().substring(0, 5);
      this.latch = latch;
      this.leftStick = leftStick;
      this.rightStick = rightStick;
    }

    @Override
    public void run() {
      try {
        latch.await();
        while (!stop) {
          eat();
          System.out.println(id + " is eating...");
          TimeUnit.MILLISECONDS.sleep(500);
          think();
          System.out.println(id + " is thinking...");
          TimeUnit.MILLISECONDS.sleep(300);
        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        throw new RuntimeException(e);
      }
    }

    private void eat() throws InterruptedException {
      leftStick.acquire();
      System.out.println(id + " pick up leftStick...");
      rightStick.acquire();
      System.out.println(id + " pick up rightStick...");
    }

    private void think() {
      leftStick.release();
      System.out.println(id + " put down leftStick...");
      rightStick.release();
      System.out.println(id + " put down rightStick...");
    }
  }
}
