public class Task_4 {

    private static final Object lock = new Object();
    private static int currNum = 1;
    private static final int endNum = 10;

    public static void main(String[] args) throws InterruptedException {
        Thread oddThread = new Thread(new TaskOdd());
        Thread evenThread = new Thread(new TaskEven());

        oddThread.start();
        evenThread.start();

        oddThread.join();
        evenThread.join();
    }

    static class TaskOdd implements Runnable {
        @Override
        public void run() {
            while (currNum <= endNum) {
                synchronized (lock) {
                    if (currNum % 2 != 0) {
                        System.out.println("Нечет. " + currNum);
                        currNum++;
                        lock.notify();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }

    static class TaskEven implements Runnable {
        @Override
        public void run() {
            while (currNum <= endNum) {
                synchronized (lock) {
                    if (currNum % 2 == 0) {
                        System.out.println("Чет. " + currNum);
                        currNum++;
                        lock.notify();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }
}