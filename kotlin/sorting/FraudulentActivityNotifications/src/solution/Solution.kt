package solution

/**
 * https://www.hackerrank.com/challenges/fraudulent-activity-notifications/problem?h_l=interview&playlist_slugs%5B%5D%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D%5B%5D=sorting&h_r=next-challenge&h_v=zen
 * */

fun readInt(del: String): Array<Int> = readLine()!!.split(del).mapTo(arrayListOf()) { it -> it.toInt() }.toTypedArray()

fun activityNotifications(expenditure: Array<Int>, d: Int): Int {
    val limit = expenditure.size-d
    var acc = 0
    for(i in 0 until limit) {
        val r = d + i - 1
        val copy = expenditure.copyOf()
        copy.sort(i, r+1)
        val m = (r - i) / 2 + i
        val v= if( (d and 1) == 0) {
            ((copy[m] + copy[m+1]) * 1.0f / 2.0f).toDouble()
        } else {
            copy[m].toDouble()
        }
        if (v * 2.0f <= copy[i+d])
            acc++
    }
    return acc
}

fun main(args: Array<String>) {
    val n = readInt(" ")
    val values = readInt(" ")
    println(activityNotifications(values, n[1]))
}