package com.example.demo;

import java.util.concurrent.CompletableFuture;

public class SimpleCompletableFuture {
    public static void main(String[] args) throws Exception {
        final CompletableFuture<Integer> future = CompletableFuture
                .supplyAsync(() -> {
                    try {
                        Thread.sleep(1500L);
                        System.out.println("First");
                        return 48;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                })
                .thenCombine(CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(1000L);
                        System.out.println("Second");
                        return 76;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }), (r1, r2) -> r2 - r1);

        future.whenComplete((result, exception) -> {
           if (exception != null) {
               throw new RuntimeException(exception);
           } else {
               System.out.println(result);
           }
        });

        Thread.sleep(2000L);

        final CompletableFuture<Integer> composed = CompletableFuture.supplyAsync(() -> 42)
                .thenCompose(number -> CompletableFuture.supplyAsync(() -> number - 42));

        System.out.println(composed.get());
    }
}
