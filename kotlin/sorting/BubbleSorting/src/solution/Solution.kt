package solution

/**
 * https://www.hackerrank.com/challenges/ctci-bubble-sort/problem?h_l=interview&playlist_slugs%5B%5D%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D%5B%5D=sorting
 * DONE
 * */

// Complete the countSwaps function below.
fun countSwaps(a: Array<Int>): Unit {
    var acc = 0
    for (i in 0..(a.size-1)){
        for (j in (i+1)..(a.size-1)){
            if (a[i] > a[j]) {
                val aux = a[i]
                a[i] = a[j]
                a[j] = aux
                acc++
            }
        }
    }
    println("Array is sorted in $acc swaps.\nFirst Element: ${a[0]}\nLast Element: ${a[(a.size-1)]}")

}


fun readInt(): Int = readLine()!!.toInt()

//fun readValues(del: String): ArrayList<Int> = readLine()!!.split(del).mapTo(arrayListOf()) { it -> it.toInt() }

// o argumento passado para funcao mapTo precisa ser do tipo MutableCollection
// https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/map-to.html
fun readValues(del: String): Array<Int> = readLine()!!.split(del).mapTo(arrayListOf()) { it -> it.toInt() }.toTypedArray()

fun main(args: Array<String>) {
    val n = readInt()
    countSwaps(readValues(" "))
}