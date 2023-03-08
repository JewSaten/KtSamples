package com.jew.tech.ktsamples

interface Base1 {
    fun print()
}

class BaseImpl(private val x: Int) : Base1 {
    override fun print() {
        println(x)
    }
}

class Derived(b: Base1) : Base1 by b

val lazyValue: String by lazy {
    println("computed!")
    "Hello"
}

fun main() {
    val b = BaseImpl(10)
    Derived(b).print()
    println(lazyValue)
    println(lazyValue)
}
