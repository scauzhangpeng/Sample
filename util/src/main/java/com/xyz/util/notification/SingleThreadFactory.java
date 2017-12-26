package com.xyz.util.notification;

import java.util.concurrent.ThreadFactory;

/**
 * This is intended to only be used with a single thread executor.
 */
final class SingleThreadFactory implements ThreadFactory {

    private final String threadName;

    SingleThreadFactory(String threadName) {
        this.threadName = "Notification-" + threadName;
    }

    @Override
    public Thread newThread(Runnable runnable) {
        return new Thread(runnable, threadName);
    }
}
