package com.jew.tech.ktsamples


import kotlinx.coroutines.*
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.transformWhile
import kotlinx.coroutines.flow.zip
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
        launch (Dispatchers.Default){
//            doSomethingOne()
            println("dEFAULT --- current thread is ${Thread.currentThread().name}")
        }
    }

    launch(newSingleThreadContext("MyOwnThread")) { // 将使它获得一个新的线程
        println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
    }

//    val job = GlobalScope.launch {
//        println("sdfsdfsfds")
//    }
//    job.join()
    flow{
        for(i in 1..5){
            emit(i)
        }
    }.map {
        it*2
    }.transform{
        emit(it+1)
    }.collect {
        println("i:$it")
    }

    (1..5).asFlow().collect {
        println("asFlow:$it")
    }

    flow {
        for (i in 0 until 3) {
            println("Emit Flow in ${Thread.currentThread().name}")
            emit(i)
        }
    }.map {
        println("Map Flow in ${Thread.currentThread().name}")
        it * it
    }.flowOn(Dispatchers.IO).collect {
        println("Collect Flow in ${Thread.currentThread().name}")
        println("Result---$it")
    }


    val cost = measureTimeMillis {
        flow {
            for (i in 0..3) {
                println("Emit Flow in ${Thread.currentThread().name}")
                delay(100)
                emit(i)
            }
        }.collectLatest {
            println("collectLatest：$it")
        }
//            f.collectIndexed { index, value ->
//            println(" index---$index ,values: $value")
//        }
//        f.collectLatest {
//            println("collectLatest：$it")
//        }
    }
    println("cost time: $cost")


    flowOf(1, 2, 3).collectLatest {
        delay(1)
        println("Result---$it")
    }

    launch {
        flow {
            for (i in 0..3) {
                println("Emit Flow in ${Thread.currentThread().name}")
                emit(i)
            }
        }.transformWhile { t ->
            emit(t)
            t == 3
        }.collect {
            println("transformWhile $it")
        }

    }
    //同步非阻塞

    launch {
        flow {
            for (i in 0..3) {
                emit(i)
            }
        }.onStart {
            println("Start Flow in ${Thread.currentThread().name}")
        }.onEach {
            println("emit value---$it")
        }.collect {
            println("Result---$it")
        }
    }

    launch {
        flow {
            for (i in 0..3) {
                emit(i)
            }
        }.onStart {
            println("Start Flow in ${Thread.currentThread().name}")
        }.onEach {
            println("emit value---$it")
        }.flowOn(Dispatchers.IO).collect {
            println("Result---$it")
        }

    }
    //同步非阻塞
    val sync = measureTimeMillis {
        flow {
            for (i in 0..5) {
                delay(200)
                println("product : ${Thread.currentThread().name}")
                emit(i)
            }
        }.collect {
            delay(500)
            println("consume : ${Thread.currentThread().name}")
            println("i: $it")
        }
    }

    //异步非阻塞
    val buff = measureTimeMillis {
        flow {
            for (i in 0..5) {
                delay(200)
                println("product : ${Thread.currentThread().name}")
                emit(i)
            }
        }.buffer().collect {
            delay(500)
            println("consume : ${Thread.currentThread().name}")
            println("i: $it")
        }
    }

    val flowOn = measureTimeMillis {
        flow {
            for (i in 0..5) {
                delay(200)
                println("product : ${Thread.currentThread().name}")
                emit(i)
            }
        }.flowOn(Dispatchers.Default).collect {
            delay(500)
            println("consume : ${Thread.currentThread().name}")
            println("i: $it")
        }
    }
    println("sync time: $sync")
    println("buff time: $buff")
    println("flowOn time: $flowOn")

    val conflate = measureTimeMillis {
        flow {
            for (i in 0..5) {
                delay(200)
                println("product : ${Thread.currentThread().name}")
                emit(i)
            }
        }.conflate().collect {
            delay(500)
            println("consume : ${Thread.currentThread().name}")
            println("i: $it")
        }
    }
    println("conflate time: $conflate")

    launch {
//        withTimeoutOrNull(1000) {
        val latest = measureTimeMillis {
            flow {
                for (i in 0..5) {
                    delay(200)
                    println("product111 : ${Thread.currentThread().name}")
                    emit(i)
                }
            }.collectLatest {
                delay(500)
                println("consume111 : ${Thread.currentThread().name}")
                println("i: $it")
            }
        }
        println("latest time: $latest")
//        }
    }


    val flow1 = flowOf(1,2).onEach { delay(10) }
    val flow2 = flowOf("a","b","c").onEach { delay(15) }
    //combine 组合
    combine(flow1,flow2){f1,f2-> f1.toString()+f2}.collect {
        println("combine i: $it ")
    }

    //merge 合并
    listOf(flow1,flow2).merge().collect {
        println("merge i: $it ")
    }

    //zip

    flow1.zip(flow2){f1,f2-> f1.toString()+f2}.collect {
        println("zip i: $it ")
    }

}



suspend fun main()  {
    doWork()
}