package ru.spbstu.sysk

import org.openjdk.jmh.runner.Runner
import org.openjdk.jmh.runner.options.OptionsBuilder

/**
 * Created by akhin on 3/11/16.
 */
fun main(args: Array<String>) {
    val opt = OptionsBuilder()
            .include(".*")
            .forks(1)
            .warmupIterations(5)
            .measurementIterations(5)
            .build();

    Runner(opt).run()
}
