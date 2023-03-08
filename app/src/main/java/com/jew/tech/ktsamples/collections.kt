package com.jew.tech.ktsamples


data class People(var name:String,var age:Int)

fun printAll(strings:Collection<String>){
    for (s in strings) print("$s")
    println()
}
fun main() {
    var list = mutableListOf("11","22","33")
    list.add("44")
    for ( i in list){
        println(i)
    }

    val stringList = listOf("das","fdg","fdgg")
    val stringSet = setOf("one","two","three")

    printAll(stringList)
    printAll(stringSet)

    val p = People("bob",20)
    val list1 = listOf(People("tom",30), People("bob",20))
    val list2 = listOf(People("tom",30),p)
    println(list1 == list2)
    p.name = "haha"
    println(list1 == list2)

    val numbers = mutableListOf(1,2,3,4)
    numbers.add(5)
    numbers.remove(1)
    numbers[0] = 0
    numbers.shuffle() //随机顺序
    println(numbers)

    val map = mapOf("key1" to 1,"key2" to 2,"key3" to 3)
    println("all keys : ${map.keys}")
    println("all valuess : ${map.values}")
    if("key1" in map.keys) println("key1 in map")
    if(map.containsKey("key2")) println("key2 contains")


    val mulMap = mutableMapOf("key1" to 1,"key2" to 2,"key3" to 3)

    mulMap["key5"] = 12

    mulMap["key1"] = 11

    println(mulMap)

    val test = listOf("11","22","33")
    test.forEach {
        println("foreach:$it")
    }

    for (item in test){
        println("forin:$item")
    }

    for(i in test.indices){
        var item : String = test[i]
        println("rangeto:$item")
    }

    test.forEachIndexed{index,item->
        println("foreachindex,index: $index,$item")
    }

    val str = """
        哈哈哈
        123
    """.trimIndent()
    println(str)

    val getList = listOf("asd","qwe","fgh")
    println(getList.getOrElse(2){"unknown"})
    println(getList.getOrNull(2))
    println(getList.getOrNull(2)?:"he")

    getList.forEach {
        println(it)
    }

    val getlist2= getList.toMutableList()
    getlist2 += "erew"
    getlist2.add("qqq")
    getlist2.add("www")
    getlist2.remove("asd")
    getlist2.remove("fgh")
    println(getlist2)

    val (a:String,_,c:String) = getList
    println("$a,$c")

    val set = setOf("java","kotlin","scala")
    println(set.elementAt(0))
    val mutableSet = set.toMutableSet().toMutableList()
    mutableSet.add("js")
    println(mutableSet)
    mutableSet -= "js"
    println(mutableSet)
    mutableSet += "java"
    mutableSet += "kotlin"
    println(mutableSet.distinct())
}