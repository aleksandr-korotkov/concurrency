package services;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorThreadServiceImpl implements ExecutorThreadService{
    private ExecutorService executorService;

    public ExecutorThreadServiceImpl(int quantityThreads) {
        executorService = Executors.newFixedThreadPool(quantityThreads);
    }

    @Override
    public void executeThread(Runnable task){
        executorService.submit(task);
    }

    @Override
    public void shutdown(){
        executorService.shutdown();
    }
}
