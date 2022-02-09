package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class BarrierCycleTest {
    public static void main(String[] args) {
        int parties = 3;
        final long startTime = System.currentTimeMillis();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(parties, () -> {
            System.out.println(System.currentTimeMillis() - startTime);
        });
        try {

            List<Thread> threads = new ArrayList<>(parties);
            for (int i = 0; i < parties; i++) {
                Thread thread = new Thread(Runner.getRunner(cyclicBarrier));
                thread.setName("Thread-" + i);
                threads.add(thread);
                thread.start();
            }

            for (Thread thread : threads) {
                thread.join();
            }
        } catch (Exception e) {
            cyclicBarrier.reset();
        }
    }

    static class Runner implements Runnable {
        private final CyclicBarrier cyclicBarrier;

        private Runner(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        public static Runner getRunner(CyclicBarrier cyclicBarrier) {
            return new Runner(cyclicBarrier);
        }

        @Override
        public void run() {
            try {
                while (!new Random().nextBoolean()) {
                    System.out.println(Thread.currentThread().getName() + " failed.");
                }
                System.out.println(Thread.currentThread().getName() + " success.");
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
