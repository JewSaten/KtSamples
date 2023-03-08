package com.jew.tech.ktsamples

class Sample1 {


    companion object{
        const val TEST= "123" //编译时常量
        @JvmStatic
        fun main(vararg args:String) {

            //apply also
            //
            val morningNotification = 51
            val eveningNotification = 135
            printNotificationSummary(morningNotification)
            printNotificationSummary(eveningNotification)

            //
            val child = 5
            val adult = 28
            val senior = 87

            val isMonday = true

            println("The movie ticket price for a person aged $child is \$${ticketPrice(child, isMonday)}.")
            println("The movie ticket price for a person aged $adult is \$${ticketPrice(adult, isMonday)}.")
            println("The movie ticket price for a person aged $senior is \$${ticketPrice(senior, isMonday)}.")


            //
            printFinalTemperature(27.0,"Celsius","Fahrenheit") {
                9.0/5.0 * it+32
            }
            printFinalTemperature(350.0,"Kelvin","Celsius") {
                it-273.15
            }
            printFinalTemperature(10.0,"Fahrenheit","Kelvin") {
                5.0/9.0 *(it - 32) + 273.15
            }

            //
            var song = Song("哈哈哈","nima","2010",30_000_000)
            song.introduce()
            println(song.isPopular)

            //
            val amanda = Person("Amanda", 33, "play tennis", null)
            val atiqah = Person("Atiqah", 28, "climb", amanda)

            amanda.showProfile()
            atiqah.showProfile()

            //

            var foldPhone = FoldablePhone()
            foldPhone.switchOn()
            foldPhone.checkPhoneScreenLight()
            foldPhone.unFold()
            foldPhone.switchOn()
            foldPhone.checkPhoneScreenLight()

            //
            val winningBid = Bid(5000, "Private Collector")
            println("winningBid $winningBid")
            println("Item A is sold at ${auctionPrice(winningBid, 2000)}.")
            println("Item B is sold at ${auctionPrice(null, 3000)}.")


            //
            testFunc("哈哈")
            testFunc("泥马",30)
            testFunc(age = 50,name = "O(∩_∩)O哈哈~")//具名函数


            //参数是函数的函数
            val getDiscountWords : (String,Int)->String = { goodsName,hour->
                val currentYear = "2022"
                "$currentYear 双11$goodsName 促销打折倒计时 $hour 小时."
            }
            showOnBoard("哈哈",getDiscountWords)
            //函数引用
            showOnBoard("泥马",::discountWords)//::

            var input:String? = readLine()
            println("input:$input")

            var str:String? = readLine()?.capitalize() // ?.安全调用操作符
            println("str:$str")

            //let操作符
            var test : String? = readLine()?.let {

                if(it.isNotBlank()){
                    it.capitalize()
                }else{
                    "哈哈哈"
                }
            }
            println("test:$test")

            var test1:String = readLine()!!.capitalize()
            println("test1:$test1")

        }

        //匿名函数 lambda


        private fun showOnBoard(goodsName: String, showDiscount:(String, Int)->String){
            val  hour:Int = (1..24).shuffled().last()
            println(showDiscount(goodsName,hour))
        }

        fun discountWords(goodsName:String,hour:Int):String{
            val currentYear = "2022"
            return "$currentYear 双11$goodsName 促销打折倒计时 $hour 小时."
        }


        private fun testFunc(name:String,age:Int = 10){ //默认值
            println("my name is $name ,i am $age years old")
        }

        private fun printNotificationSummary(numberOfMessages: Int) {
            val msg = if (numberOfMessages<100) "You have $numberOfMessages notifications."
            else "Your phone is blowing up! You have 99+ notifications."
            println(msg)

        }

        private fun ticketPrice(age: Int, isMonday: Boolean): Int {
          return when(age){
              in 0..12 -> 15
              in 13..60 -> if (isMonday) 25 else 30
              in 61..100 -> 20
              else -> -1
          }
        }

        private fun printFinalTemperature(
            initialMeasurement: Double,
            initialUnit: String,
            finalUnit: String,
            conversionFormula: (Double) -> Double
        ) {
            val finalMeasurement = String.format("%.2f", conversionFormula(initialMeasurement))
            println("$initialMeasurement degrees $initialUnit is $finalMeasurement degrees $finalUnit.")
        }

        private fun auctionPrice(bid: Bid?, minimumPrice: Int): Int {
            return bid?.amount ?: minimumPrice
        }
    }

    class Song(var name:String, var musician:String, var year:String, var playTimes:Int){

        val isPopular :Boolean get() = playTimes>=1000

        fun introduce(){
            println("$name, performed by $musician, was released in $year.")
        }
    }

    class Person(val name: String, val age: Int, val hobby: String?, val referrer: Person?) {
        fun showProfile() {

            println("Name：$name")
            println("Age：$age")
            val intro = if(referrer!=null) "Likes to $hobby. Has a referrer named ${referrer.name}, who likes to ${referrer.hobby}."
            else "Likes to $hobby. Doesn't have a referrer."
            println(intro)
        }
    }

     open class Phone(var isScreenLightOn: Boolean = false){
        open fun switchOn() {
            isScreenLightOn = true
        }

         fun switchOff() {
            isScreenLightOn = false
        }

         fun checkPhoneScreenLight() {
            val phoneScreenLight = if(isScreenLightOn) "on" else "off"
            println("The phone screen's light is $phoneScreenLight.")
        }
    }

    class  FoldablePhone(var isFold:Boolean = true) : Phone() {

        fun isFold(){
            isFold = true
        }

        fun unFold(){
            isFold = false
        }

        override fun switchOn() {
            if(!isFold) {
                isScreenLightOn = true
            }
        }

    }

    class Bid(val amount: Int, val bidder: String)


}