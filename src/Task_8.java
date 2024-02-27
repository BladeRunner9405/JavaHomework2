import java.util.concurrent.Semaphore;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Random;

public class Task_8 {
    public static void main(String[] args) {
        int shipCount = 8;
        Tunnel tunnel = new Tunnel(5);
        ExecutorService executor = Executors.newFixedThreadPool(shipCount);

        int[] sizes = {10, 50, 100};
        for (int i = 1; i <= shipCount; i++) {

            Random random = new Random();
            executor.submit(new Ship("Ship" + i, tunnel, sizes[random.nextInt(sizes.length)]));
        }
        executor.shutdown();
    }
}

class Ship implements Runnable {
    private final String name;
    private Tunnel tunnel;
    public int capacity;

    public Ship(String name, Tunnel tunnel, int capacity) {
        this.name = name;
        this.tunnel = tunnel;
        this.capacity = capacity;
    }

    public void waitForUnload(long millis) throws InterruptedException {
        Thread.sleep(millis);
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {

        System.out.println(name + " ship is approaching the tunnel.");
        try {
            tunnel.enter(this);
            System.out.println(name + " ship is sailing through the tunnel.");
            Thread.sleep(2000); // Корабль плывет в туннеле 2 секунды
            System.out.println(name + " ship has left the tunnel.");
            tunnel.exit(this);
            Dock.unload(this);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class Tunnel {
    private Semaphore semaphore;

    public Tunnel(int capacity) {
        semaphore = new Semaphore(capacity);
    }

    public void enter(Ship ship) throws InterruptedException {
        semaphore.acquire();
        System.out.println(ship.getName() + " ship entered the tunnel.");
    }

    public void exit(Ship ship) {
        semaphore.release();
        System.out.println(ship.getName() + " ship exited the tunnel.");
    }
}

class Dock {
    public static void unload(Ship ship) throws InterruptedException {
        System.out.println("Unloading " + ship.getName());
        ship.waitForUnload(ship.capacity * 100L);
        System.out.println("Unload " + ship.getName());
    }
}