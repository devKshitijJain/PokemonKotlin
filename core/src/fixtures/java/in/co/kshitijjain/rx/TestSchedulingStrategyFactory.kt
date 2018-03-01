package `in`.co.kshitijjain.pokemonkotlin.rx

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestSchedulingStrategyFactory internal constructor(subscribingScheduler: Scheduler,
                                                         observingScheduler: Scheduler)
    : SchedulingStrategy.Factory(subscribingScheduler, observingScheduler) {
    companion object {

        fun immediate(): TestSchedulingStrategyFactory {
            return TestSchedulingStrategyFactory(Schedulers.trampoline(), Schedulers.trampoline())
        }

        fun subscribing(scheduler: Scheduler): TestSchedulingStrategyFactory {
            return TestSchedulingStrategyFactory(scheduler, Schedulers.trampoline())
        }
    }
}