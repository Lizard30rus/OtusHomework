package com.example.otushomework.utils

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.example.otushomework.R
import kotlin.random.Random

class Worker(
    private val context: Context,
    private val workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        startForegroundService()
        return Result.success()
    }


    private suspend fun startForegroundService() {
        setForeground(
            ForegroundInfo(
                Random.nextInt(),
                NotificationCompat.Builder(context, "show_detail_film")
                    .setSmallIcon(R.drawable.ic_settings)
                    .setContentText("Открывается")
                    .setContentTitle("Открыть")
                    .build()
            )
        )
    }
}