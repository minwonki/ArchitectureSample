package com.wk.min.architecturesample.scheduler

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun ui() : Scheduler
    fun io() : Scheduler
}