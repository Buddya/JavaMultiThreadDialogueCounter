import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static void main(String[] args) throws Exception {
        List<Future<String>> futureList = getFutures(getCallables()); // list for futures

        Thread.sleep(8000); //для понятного вывода после сообщений от потоков

        System.out.println("Invoke all:");
        for (Future<String> future : futureList) {
            System.out.println(future.get());
        }

        Thread.sleep(8000);

        System.out.println("Invoke any:");
        String result = executorService.invokeAny(getCallables());
        System.out.println(result);

        executorService.shutdown();
    }

    private static List<Callable<String>> getCallables() {
        final Random random = new Random();
        final int tasksAmount = random.nextInt(5);// random amount of tasks 1-5
        List<Callable<String>> callableList = new ArrayList<>();
        for (int i = 0; i < tasksAmount; i++) {
            Callable<String> callable = new MyThread("Thread-" + i);
            callableList.add(callable);
        }
        return callableList;
    }

    private static List<Future<String>> getFutures(List<Callable<String>> callableList) {
        List<Future<String>> futureList = new ArrayList<>(); // list for futures
        for (Callable<String> callable : callableList) {
            Future<String> future = executorService.submit(callable);
            futureList.add(future);
        }
        return futureList;
    }
}
