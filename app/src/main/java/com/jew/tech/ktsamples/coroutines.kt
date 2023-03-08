package com.jew.tech.ktsamples

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis


/*fun main() {

    GlobalScope.launch {
        delay(1000L) //非阻塞的等待 1 秒钟（默认时间单位是毫秒）,挂起函数 ，它不会造成线程阻塞，但是会 挂起 协程
        println("world")
    }
    println("hello,")
    Thread.sleep(2000L)
}*/

//fun main() = runBlocking {
//    launch {
//        delay(1000L)
//        println("world")
//    }
//
//    println("hello")
//}

//fun main() = runBlocking {
//    launch { doWorld() }
//    println("hello,")
//}
//
//suspend fun doWorld(){
//    delay(1000L)
//    println("world")
//}

//fun main() = runBlocking{
//
//    val job = GlobalScope.launch {
//        delay(1000L)
//        println("world")
//    }
//    println("hello,")
//    job.join()
//    println("done")
//
//}

//fun main() = runBlocking { // this: CoroutineScope
//    launch {
//        delay(200L)
//        println("Task from runBlocking")
//    }
//
//    coroutineScope { // 创建一个协程作用域
//        launch {
//            delay(500L)
//            println("Task from nested launch")
//        }
//
//        delay(100L)
//        println("Task from coroutine scope") // 这一行会在内嵌 launch 之前输出
//    }
//
//    println("Coroutine scope is over") // 这一行在内嵌 launch 执行完毕后才输出
//}
//
//fun main() = runBlocking {
//    repeat(100_000) { // 启动大量的协程
//        launch {
//            delay(5000L)
//            print(".")
//        }
//    }
//}

suspend fun doSomethingOne():Int{
    delay(1000)
    return 10
}

suspend fun doSomethingTwo():Int{
    delay(1000)
    return 20
}

//fun main() = runBlocking{
//    val time = measureTimeMillis {
//        val one = doSomethingOne()
//        val two = doSomethingTwo()
//        println("result is : ${one+two}")
//    }
//    println("time cost : $time")
//}

suspend fun concorrentSum(): Int = coroutineScope {
    val one = async { doSomethingOne() }
    val two = async { doSomethingTwo() }
    one.await() +two.await()
}

//fun main() = runBlocking{
//    val time = measureTimeMillis {
//        val one = async {  doSomethingOne()}
//        val two = async {  doSomethingTwo()}
//        println("result is : ${one.await()+two.await()}")
//    }
//    println("time cost : $time")
//
//    val time1 = measureTimeMillis {
//        println("result1 is : ${concorrentSum()}")
//    }
//
//    println("time1 cost : $time1")
//    println("1-- current thread is ${Thread.currentThread().name}")
//    launch(Dispatchers.Default){
//        println("Default--- current thread is ${Thread.currentThread().name}")
//    }
//    launch(Dispatchers.Unconfined){
//        println("Unconfined --- current thread is ${Thread.currentThread().name}")
//    }
//    launch(Dispatchers.IO){
//        println("IO --- current thread is ${Thread.currentThread().name}")
//    }
//
//    launch(newSingleThreadContext("MyOwnThread")) { // 将使它获得一个新的线程
//        println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
//    }
//
//    println("2-- current thread is ${Thread.currentThread().name}")
//
//}

suspend fun doWork() = coroutineScope {
    println("1-- current thread is ${Thread.currentThread().name}")
    launch(Dispatchers.Default){
        println("Default--- current thread is ${Thread.currentThread().name}")
    }
    launch(Dispatchers.Unconfined){
        println("Unconfined --- current thread is ${Thread.currentThread().name}")
    }
    launch(Dispatchers.IO){
        println("IO --- current thread is ${Thread.currentThread().name}")
        launch(Dispatchers.Main){
//            doSomethingOne()
            println("Main --- current thread is ${Thread.currentThread().name}")
        }
    }

    launch(newSingleThreadContext("MyOwnThread")) { // 将使它获得一个新的线程
        println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
    }

}

suspend fun main()  {
    doWork()
}