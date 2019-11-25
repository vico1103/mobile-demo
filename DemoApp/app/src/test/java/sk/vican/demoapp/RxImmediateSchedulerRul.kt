package sk.vican.demoapp

import io.reactivex.android.plugins.*
import io.reactivex.plugins.*
import io.reactivex.schedulers.*
import org.junit.rules.*
import org.junit.runner.*
import org.junit.runners.model.*

class RxImmediateSchedulerRule: TestRule {

  override fun apply(base: Statement, d: Description): Statement {
    return object: Statement() {
      @Throws(Throwable::class)
      override fun evaluate() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        try {
          base.evaluate()
        } finally {
          RxJavaPlugins.reset()
          RxAndroidPlugins.reset()
        }
      }
    }
  }
}
