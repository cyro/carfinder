package com.autoscout24.carfinder.arch.core

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor

data class AppExecutors(
        val diskIO: Executor,
        val mainThread: Executor,
        val networkIO: Executor
) {

    class MainThreadExecutor : Executor {

        private val mainThreadHandler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable?) {
            mainThreadHandler.post(command)
        }

    }
}
