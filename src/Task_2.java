public class Task_2 {

    public static void main(String[] args) {
        Thread thread1 = new MyThread("Thread 1", 10);
        Thread thread2 = new MyThread("Thread 2", 15);
        Thread thread3 = new MyThread("Thread 3", 15);

        thread1.start();
        thread2.start();
        thread3.start();
    }

    static class MyThread extends Thread {
        private final String name;
        private final int amount;

        public MyThread(String name, int amount) {
            this.name = name;
            this.amount = amount;
        }

        @Override
        public void run() {
            for (int i = 0; i < amount; i++) {
                System.out.println(name + " " + currentThread().threadId());
            }
        }
    }
}