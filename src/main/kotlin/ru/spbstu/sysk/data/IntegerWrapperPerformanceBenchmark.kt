package ru.spbstu.sysk.data

import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import ru.spbstu.sysk.util.*
import java.util.*

@State(Scope.Benchmark)
open class IntegerWrapperPerformanceBenchmark {

    @Param("16", "32", "64")
    var width: Int = 0;

    @Param("1000")
    var length: Int = 0;

    var bigIntegers: Array<SysBigInteger> = emptyArray()

    var integers: Array<SysInteger> = emptyArray()

    @Setup
    fun setUp() {
        val mask = -1L ushr (65 - width)
        bigIntegers = Random()
                .longs()
                .limit(2L * length)
                .map { it `&` mask }
                .mapToObj { SysBigInteger(width, it) }
                .toArray { i -> Array(i, { ii -> SysBigInteger(width, ii) }) }
        integers = Random()
                .longs()
                .limit(2L * length)
                .map { it `&` mask }
                .mapToObj { SysInteger(width, it) }
                .toArray { i -> Array(i, { ii -> SysInteger(width, ii) }) }
    }

    var a: SysBigInteger = SysBigInteger(width, 0)
    var b: SysBigInteger = SysBigInteger(width, 0)

    var aa: SysInteger = SysInteger(width, 0)
    var bb: SysInteger = SysInteger(width, 0)

    @Setup(Level.Iteration)
    fun setUpIteration() {
        a = bigIntegers.randomElement()
        b = bigIntegers.randomElement()

        aa = integers.randomElement()
        bb = integers.randomElement()
    }

    @Benchmark
    fun testSysBigIntegerPlus(bh: Blackhole) {
        bh.consume(a + b)
    }

    @Benchmark
    fun testSysIntegerPlus(bh: Blackhole) {
        bh.consume(aa + bb)
    }

    @Benchmark
    fun testSysBigIntegerMinus(bh: Blackhole) {
        bh.consume(a - b)
    }

    @Benchmark
    fun testSysIntegerMinus(bh: Blackhole) {
        bh.consume(aa - bb)
    }

    @Benchmark
    fun testSysBigIntegerMultiply(bh: Blackhole) {
        bh.consume(a * b)
    }

    @Benchmark
    fun testSysIntegerMultiply(bh: Blackhole) {
        bh.consume(aa * bb)
    }

    @Benchmark
    fun testSysBigIntegerDiv(bh: Blackhole) {
        bh.consume(a / b)
    }

    @Benchmark
    fun testSysIntegerDiv(bh: Blackhole) {
        bh.consume(aa / bb)
    }

    @Benchmark
    fun testSysBigIntegerMod(bh: Blackhole) {
        bh.consume(a % b)
    }

    @Benchmark
    fun testSysIntegerMod(bh: Blackhole) {
        bh.consume(aa % bb)
    }

}
