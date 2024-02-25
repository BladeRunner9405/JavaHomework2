public class Task_3 {
    private static final int SIZE = 100;
    private static final int[] arr = new int[SIZE];
    private static boolean isFilled = false;

    public static void main(String[] args) {
        Thread fillerThread = new FillThread();
        Thread printerThread = new PrintThread();

        fillerThread.start();
        printerThread.start();
    }

    static class FillThread extends Thread {
        @Override
        public void run() {
            synchronized (arr) {
                for (int i = 0; i < SIZE; i++) {
                    arr[i] = i * 2;
                }
                isFilled = true;
                arr.notify();
            }
        }
    }

    static class PrintThread extends Thread {
        @Override
        public void run() {
            synchronized (arr) {
                while (!isFilled) {
                    try {
                        arr.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                for (int num : arr) {
                    System.out.print(num + " ");
                }
            }
        }
    }
}