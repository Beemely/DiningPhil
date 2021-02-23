package com.company;

public class Philosopher implements Runnable {
    private Object leftFork;
    private Object rightFork;

    private void doAction(String action) throws InterruptedException {
        System.out.println(
                Thread.currentThread().getName() + " " + action);
        Thread.sleep(300);
    }

    public Philosopher(Object leftFork, Object rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        try {
            while (true) {
                doAction(System.nanoTime() + ": think");
                synchronized (leftFork) {
                    doAction(System.nanoTime() + ": L fork - picked up");

                    synchronized (rightFork) {
                        doAction(System.nanoTime() + ": R fork - eating");
                        doAction(System.nanoTime() + ": R fork - put down");
                    }

                    doAction(System.nanoTime() + ": L fork - put down");
                }
            }
        }
         catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

    }
}
