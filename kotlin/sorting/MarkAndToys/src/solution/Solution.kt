package solution

/**
 * https://www.hackerrank.com/challenges/mark-and-toys/problem?h_l=interview&playlist_slugs%5B%5D%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D%5B%5D=sorting
 * DONE
 * */

fun readInt(del: String): Array<Int> = readLine()!!.split(del).mapTo(arrayListOf()) { it -> it.toInt() }.toTypedArray()

fun readInt(): Int = readLine()!!.toInt()

// Complete the maximumToys function below.
fun maximumToys(prices: Array<Int>, k: Int): Int {
    prices.sort()
    var acc = 0
    var q = 0
    var i = 0
    while ((acc + prices[i]) <= k && i < prices.size){
        acc += prices[i]
        i++
        q++
    }
    return q
}


fun main(args: Array<String>) {

    val p = readInt(" ")
    val prices = readInt(" ")
    println("${maximumToys(prices, p[1])}")
}