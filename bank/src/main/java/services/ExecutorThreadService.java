package services;

public interface ExecutorThreadService {

    void executeThread(Runnable task);

    void shutdown();
}
