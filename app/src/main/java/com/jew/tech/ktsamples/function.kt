package com.jew.tech.ktsamples

import kotlin.math.roundToInt


fun main(){

    val animals = listOf("dog","cat","fish")
    val mapAnimals = animals.map { animal->"$animal 123456" }

    println(animals)
    println(mapAnimals)

    val flatMapIntArr = listOf(listOf(1,2,3), listOf(4,5,6)).flatten()
    println(flatMapIntArr)

    val filter = listOf("Jak","ahah","DGj").filter { it.contains("J") }
    println(filter)

    val employ = listOf("tom","jack","amy")
    val shirtSize= listOf("large","medium","x-large")
    val employShirtSize = employ.zip(shirtSize).toMap()
//    employShirtSize.forEach {
//        println("key: ${it.key} , value: ${it.value}")
//    }
    val employFormatSize = employShirtSize.map { "${it.key} , shirt size is: ${it.value}" }
    println(employFormatSize)

    val arr = arrayOf("ad","fdsf","fdf")
    val list = asList("11","sfs",*arr,"dfd")

    println(list)

   val stringPlus : (String,String) -> String = String::plus
    println(stringPlus("he","llo"))
    println(stringPlus.invoke("he","llo"))
    val intPlus : Int.(Int) -> Int = Int::plus
    println(intPlus.invoke(1,2))
    println(intPlus(1,2))

    val test = mapOf("hello" to 1,"world" to 2)
    test.forEach { (_, values) -> println("$values") }//参数未使用用_下划线取代



    //字符串操作
    val index = NAME.indexOf("\'")

    val str  = NAME.substring(0 until index)
    println("str：$str")

    val (s1:String,s2:String) = NAME.split("\'")
    println("s1:$s1, s2:$s2")

    val str1 = "The people republic of china"
    val str2 = str1.replace("people","")
    println("str2:$str2")
    val str3 = "Jason"
    val str4 = "jason".capitalize()
    println("$str3,$str4")
    println(str3 == str4)
    println(str3 === str4)

    NAME.forEach {
        println("$it*")
    }
    var a:String? = "2.22"
    var b:String? = null
    val num:Double? = a?.toDoubleOrNull()
    println("num: $num")

    val num1 = "%.2f".format(5.3243432)
    println("num1: $num1")
    println(2.3232.toInt())
    println(2.5232.roundToInt())

    var text  = "The people's republic of china"
    val isTooLong = with(text){
        length>=10
    }
    println("isTooLong: $isTooLong")


//    val listOne = listOf(2, 3, 3, 4, 5, 6)
//    val listTwo = listOf(1,2, 2, 4, 5, 6, 7, 8)
//    println(listOne.intersect(listTwo)) // [2, 4, 5, 6]

    val listOne = mutableListOf(1,2, 2, 4, 5, 6, 7, 8)
    val listTwo  = listOf(2, 3, 3, 4, 5, 6)
    println(listOne.retainAll(listTwo))
    println(listOne)

    val foldedValue:Int = listOf(1,2,3,4).fold(0){acc, i ->
        println("foldValued: $acc")
        acc+ (i*3)
    }
    println("final value: $foldedValue")
}

fun isLong(name:String):Boolean = name.length>=10

fun showMessage(isLong:Boolean):String{
    return  if(isLong){
        "text is too long"
    }else {
        "text "
    }
}
const val NAME = "hello'word"

fun <T> asList(vararg ts:T): List<T>{
    val result = ArrayList<T>()
    for(t in ts){
        result.add(t)
    }
    return result
}