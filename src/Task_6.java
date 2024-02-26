import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Task_6 {

    public final List<String> notes = new ArrayList<>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void addNote(int index, String note) {
        System.out.println("Сейчас будет добавлена заметка [" + note + "] На позицию " + index);
        lock.writeLock().lock();
        try {
            notes.add(index, note);
        } finally {
            lock.writeLock().unlock();
        }
        System.out.println("Уже добавлена заметка [" + note + "]");
    }

    public void removeNote(int index) {
        System.out.println("Сейчас будет удалена заметка с позиции " + index);
        String note;
        lock.writeLock().lock();
        try {
            note = notes.remove(index);
        } finally {
            lock.writeLock().unlock();
        }
        System.out.println("Уже удалена заметка [" + note + "] с позиции " + index);
    }

    public void readNote(int index) {
        System.out.println("Сейчас будет прочтена заметка с позиции " + index);
        String note;
        lock.readLock().lock();
        try {
            note = notes.get(index);
        } finally {
            lock.readLock().unlock();
        }
        System.out.println("Прочтена заметка [" + note + "] с позиции " + index);
    }
}
