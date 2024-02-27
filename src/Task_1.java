import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Task_1 {
    public static void main(String[] args) {
        try {
            File file = new File("src/resources/data.txt");
            fillFileRandomNums(file, 1000);

            Scanner scanner = new Scanner(file);
            List<Integer> numbers = new ArrayList<>();
            while (scanner.hasNextInt()) {
                numbers.add(scanner.nextInt());
            }

            findMinMaxParallel(numbers);
            findMinMax(numbers);

        } catch (FileNotFoundException e) {
            System.out.print("File not found");
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void fillFileRandomNums(File file, int amount) throws IOException {
        FileWriter writer = new FileWriter(file);
        Random random = new Random();
        for (int i = 0; i < amount; i++) {
            writer.write(random.nextInt(1000) + " ");
        }
        writer.close();
    }

    public static void findMinMaxParallel(List<Integer> numbers) throws InterruptedException {
        // Поиск минимума и максимума в двух потоках
        Thread minThread = new Thread(() -> {
            int min = numbers.stream().min(Integer::compareTo).orElse(0);
            System.out.println("Минимум: " + min);
        });

        Thread maxThread = new Thread(() -> {
            int max = numbers.stream().max(Integer::compareTo).orElse(0);
            System.out.println("Максимум: " + max);
        });

        minThread.start();
        maxThread.start();

        minThread.join();
        maxThread.join();
    }

    public static void findMinMax(List<Integer> numbers) {
        // Поиск минимума и максимума без использования параллельности
        int min = numbers.stream().min(Integer::compareTo).orElse(0);
        int max = numbers.stream().max(Integer::compareTo).orElse(0);
        System.out.println("Минимум: " + min);
        System.out.println("Максимум: " + max);
    }

}
