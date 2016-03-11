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

    var bigInteger_a: Array<SysBigInteger> = emptyArray()
    var bigInteger_b: Array<SysBigInteger> = emptyArray()

    var integer_a: Array<SysInteger> = emptyArray()
    var integer_b: Array<SysInteger> = emptyArray()

    @Setup
    fun setUp() {
        val mask = -1L ushr (65 - width)
        val bigIntegers = Random()
                .longs()
                .limit(2L * length)
                .map { it `&` mask }
                .mapToObj { SysBigInteger(width, it) }
                .toArray { i -> Array(i, { ii -> SysBigInteger(width, ii) }) }
        val integers = Random()
                .longs()
                .limit(2L * length)
                .map { it `&` mask }
                .mapToObj { SysInteger(width, it) }
                .toArray { i -> Array(i, { ii -> SysInteger(width, ii) }) }

        bigInteger_a = bigIntegers.copyOfRange(0, length)
        bigInteger_b = bigIntegers.copyOfRange(length, 2 * length)

        integer_a = integers.copyOfRange(0, length)
        integer_b = integers.copyOfRange(length, 2 * length)
    }

    @Benchmark
    fun testSysBigIntegerPlus(bh: Blackhole) {
        for (i in bigInteger_a.indices) {
            val a = bigInteger_a[i]
            val b = bigInteger_b[i]
            bh.consume(a + b)
        }
    }

    @Benchmark
    fun testSysIntegerPlus(bh: Blackhole) {
        for (i in integer_a.indices) {
            val a = integer_a[i]
            val b = integer_b[i]
            bh.consume(a + b)
        }
    }

    @Benchmark
    fun testSysBigIntegerMinus(bh: Blackhole) {
        for (i in bigInteger_a.indices) {
            val a = bigInteger_a[i]
            val b = bigInteger_b[i]
            bh.consume(a - b)
        }
    }

    @Benchmark
    fun testSysIntegerMinus(bh: Blackhole) {
        for (i in integer_a.indices) {
            val a = integer_a[i]
            val b = integer_b[i]
            bh.consume(a - b)
        }
    }

    @Benchmark
    fun testSysBigIntegerMultiply(bh: Blackhole) {
        for (i in bigInteger_a.indices) {
            val a = bigInteger_a[i]
            val b = bigInteger_b[i]
            bh.consume(a * b)
        }
    }

    @Benchmark
    fun testSysIntegerMultiply(bh: Blackhole) {
        for (i in integer_a.indices) {
            val a = integer_a[i]
            val b = integer_b[i]
            bh.consume(a * b)
        }
    }

    @Benchmark
    fun testSysBigIntegerDiv(bh: Blackhole) {
        for (i in bigInteger_a.indices) {
            val a = bigInteger_a[i]
            val b = bigInteger_b[i]
            bh.consume(a / b)
        }
    }

    @Benchmark
    fun testSysIntegerDiv(bh: Blackhole) {
        for (i in integer_a.indices) {
            val a = integer_a[i]
            val b = integer_b[i]
            bh.consume(a / b)
        }
    }

    @Benchmark
    fun testSysBigIntegerMod(bh: Blackhole) {
        for (i in bigInteger_a.indices) {
            val a = bigInteger_a[i]
            val b = bigInteger_b[i]
            bh.consume(a % b)
        }
    }

    @Benchmark
    fun testSysIntegerMod(bh: Blackhole) {
        for (i in integer_a.indices) {
            val a = integer_a[i]
            val b = integer_b[i]
            bh.consume(a % b)
        }
    }

}
