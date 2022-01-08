import java.util.Random;
import java.util.concurrent.Callable;

public class MyThread implements Callable<String> {
    private final String name;
    private final Random random = new Random();
    private final int counter = random.nextInt(5);

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        for (int i = 0; i <= counter; i++) {
            System.out.printf("Привет! Я поток %s\n", name);
            Thread.sleep(1000);
        }
        return "Количество сообщений в потоке " + name + " = " + (counter + 1);
    }
}
