package com.jew.tech.ktsamples

class Singleton private constructor(){
    companion object {
        val instance: Singleton by lazy { Singleton() }
    }
    fun print() = println("single inst")
}

fun main(){
    val singleton = Singleton.instance
    singleton.print()
}