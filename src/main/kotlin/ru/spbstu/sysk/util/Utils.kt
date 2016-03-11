package ru.spbstu.sysk.util

import java.util.*

/**
 * Created by akhin on 3/11/16.
 */
infix fun Long.`&`(other: Long) = this.and(other)

fun <T> Array<T>.randomElement() =
        if (this.isNotEmpty())
            this[Random().nextInt(this.size)]
        else
            throw NoSuchElementException()
