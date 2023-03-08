package com.jew.tech.ktsamples

import java.util.regex.Pattern

open class Base

class Child :Base()

fun Base.foo() = println("base foo")

fun Child.foo() = println("Child foo")

fun execute(base :Base) = base.foo()

//扩展函数
fun <T> T.generatePrint() : T {
    println("[generatePrint]: $this")
    return this
}

fun String.checkEmail():Boolean{
    val emailPattern = "[a-zA-Z0-9][a-zA-Z0-9._-]{2,16}[a-zA-Z0-9]@[a-zA-Z0-9]+.[a-zA-Z0-9]+"
    return Pattern.matches(emailPattern,this)
}

//扩展属性

val String.numOfVowel get() = count { "aeiou".contains(it) }

fun main(){
    var base = Base()
    var child = Child()
    execute(base)
    execute(child)
    println("aaa.qwe@qq.com".checkEmail())
    "123".generatePrint()

    "aaa.qwe@qq.com".numOfVowel.generatePrint()

}