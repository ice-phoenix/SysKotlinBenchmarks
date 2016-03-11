package ru.spbstu.sysk.data

import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import ru.spbstu.sysk.util.*
import java.util.*

@State(Scope.Benchmark)
open class IntegerWrapperAllocationBenchmark {

    @Param("16", "32", "64")
    var width: Int = 0;

    var value: Long = 0;

    @Setup
    fun setUp() {
        val mask = -1L ushr (65 - width)
        value = Random().nextLong() `&` mask
    }

    @Benchmark
    fun testSysBigIntegerAllocation(bh: Blackhole) {
        bh.consume(SysBigInteger(width, value))
    }

    @Benchmark
    fun testSysIntegerAllocation(bh: Blackhole) {
        bh.consume(SysInteger(width, value))
    }

}
