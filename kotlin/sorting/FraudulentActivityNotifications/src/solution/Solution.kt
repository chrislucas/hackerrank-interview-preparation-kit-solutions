package solution

/**
 * https://www.hackerrank.com/challenges/fraudulent-activity-notifications/problem?h_l=interview&playlist_slugs%5B%5D%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D%5B%5D=sorting&h_r=next-challenge&h_v=zen
 * */

fun readInt(del: String): Array<Int> = readLine()!!.split(del).mapTo(arrayListOf()) { it -> it.toInt() }.toTypedArray()

fun medianInRange(data: Array<Int>, p: Int, q: Int) : Double{
    var maxValue = data[p]
    for (i in (p+1..q)) {
        if (maxValue < data[i])
            maxValue = data[i]
    }

    val frequency = Array(maxValue + 1) {0}
    for (i in p..q) {
        frequency[data[i]] += 1
    }

    for (i in (p+1)..maxValue) {
        frequency[i] += frequency[i-1]
    }

    val copy = Array(data.size) {0}
    for (i in p..q) {
        copy[frequency[data[i]] - 1] = data[i]
        frequency[data[i]]--
    }

    val mid =  q/2 //(q-p)/2+p
    // se a limite superior - inferior for um numero par temos um intervalo com uma quantidade
    // impar de elementos: p = 7 q = 9 [7,8,9] (q-p)/2+p -> 2/2+7=8
    // do contrario a quantidade de elementos é par: p:0 q:3 [0,1,2,3] (q-p)/2+p -> 3/2+0= 1
    // no caso de quantidade par de elementos a mediana e a media entre mid e mid+1
    if ((q-p) and 1 == 0) {
        return copy[mid].toDouble()
    }
    return (copy[mid].toDouble() + copy[mid+1].toDouble()) / 2.0f
}

fun activityNotifications(expenditure: Array<Int>, d: Int): Int {
    val limit = expenditure.size-d
    var acc = 0
    for(i in 0 until limit) {
        /*
         Primeira solucao que da TLE
         val r = d + i - 1

         val copy = expenditure.copyOf()
         copy.sort(i, r+1)
         val m = (r - i) / 2 + i
         val v= if( (d and 1) == 0) {
             ((copy[m] + copy[m+1]) * 1.0f / 2.0f).toDouble()
         } else {
             copy[m].toDouble()
         }
     */
        val v = medianInRange(expenditure, i, d+i-1)
        if (v * 2.0f <= expenditure[i+d])
            acc++
    }
    return acc
}

fun main(args: Array<String>) {
    val n = readInt(" ")
    val values = readInt(" ")
    println(activityNotifications(values, n[1]))
}