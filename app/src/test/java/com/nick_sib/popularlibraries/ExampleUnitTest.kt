package com.nick_sib.popularlibraries

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.Test

import org.junit.Assert.*
import java.util.concurrent.TimeUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    /**информация для основы примеров взята с ресурса
     * https://medium.com/appunite-edu-collection/rxjava-flatmap-switchmap-and-concatmap-differences-examples-6d1f3ff88ee0
     * */
    @Test
    fun testFlatMap() {
        println("\n\nflatMap test")
        val scheduler = TestScheduler()
        Observable.just("A", "B", "C")
            .flatMap { a: String ->
                println(a)
                Observable
                    .intervalRange(1, 3, 0, 1, TimeUnit.SECONDS)
                    .map { b ->
                        "($a, $b)"
                    }
            }
            .blockingSubscribe {
                println(it)
            }
        scheduler.advanceTimeBy(1, TimeUnit.MINUTES)
    }


    @Test
    fun testSwitchMap() {
        println("\n\nswitchMap test")
        val scheduler = TestScheduler()
        Observable.just("A", "B", "C")
            .switchMap { a ->
                println(a)
                Observable
                    .intervalRange(1, 3, 0, 1, TimeUnit.SECONDS)
                    .map { b ->
                        "($a, $b)"
                    }
            }
            .blockingSubscribe {
                println(it)
            }
        scheduler.advanceTimeBy(1, TimeUnit.MINUTES)
    }
}