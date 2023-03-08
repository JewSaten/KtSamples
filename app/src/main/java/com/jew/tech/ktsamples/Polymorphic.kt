package com.jew.tech.ktsamples


class Polymorphic {



    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var base = Base()
            var child = Child()

            executeFoo(base)
            executeFoo(child)
        }

        fun Base.foo() = println("this is from base") // 父类的扩展函数 foo

        fun Child.foo() = println("this is from child") // 子类的扩展函数 foo

        fun executeFoo(base: Base) = base.foo()
    }
    open class Base

    class Child : Base()
}