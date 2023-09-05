package com.xmum.server.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class VisibleThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {

    private static final Logger logger = LoggerFactory.getLogger(VisibleThreadPoolTaskExecutor.class);

    public void info() {
        ThreadPoolExecutor executor = getThreadPoolExecutor();


        if (executor == null) return;

        String info = "线程池" + this.getThreadNamePrefix() +
                "中，总任务数为 " + executor.getTaskCount() +
                " ，已处理完的任务数为 " + executor.getCompletedTaskCount() +
                " ，目前正在处理的任务数为 " + executor.getActiveCount() +
                " ，缓冲队列中任务数为 " + executor.getQueue().size();

        logger.info(info);
    }

    @Override
    public void execute(Runnable task) {
        info();
        super.execute(task);
    }

    @Override
    public void execute(Runnable task, long startTimeout) {
        info();
        super.execute(task, startTimeout);
    }

    @Override
    public Future<?> submit(Runnable task) {
        info();
        return super.submit(task);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        info();
        return super.submit(task);
    }

    @Override
    public ListenableFuture<?> submitListenable(Runnable task) {
        info();
        return super.submitListenable(task);
    }

    @Override
    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        info();
        return super.submitListenable(task);
    }
}