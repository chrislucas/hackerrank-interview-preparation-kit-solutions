import java.lang.Math.abs

/**
 * https://www.hackerrank.com/challenges/count-triplets-1/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dictionaries-hashmaps
 * */

// Metodo ingenuo
fun s1(arr: Array<Long>, r: Long) : Long {
    val map : MutableMap<Long, Pair<Long, Long>> = mutableMapOf()
    for (i in 0 until arr.size) {
        if (map[arr[i]]==null) {
            map[arr[i]] = arr[i] * r to arr[i] * r * r
        }
    }
    var acc = 0L
    for (i in 0 until arr.size - 2) {
        val pair = map[arr[i]]
        if (pair != null) {
            val f = pair.first
            val s = pair.second
            for(j in i+1 until arr.size - 1) {
                for(k in j+1 until arr.size) {
                    if(arr[j] == f && arr[k] == s)
                        acc++
                }
            }
        }
    }
    return acc
}

fun s2(arr: Array<Long>, r: Long) : Long {
    val map : MutableMap<Long, Pair<Long, Long>> = mutableMapOf()
    val pos : MutableMap<Long, MutableList<Int>> = mutableMapOf()
    for (i in 0 until arr.size) {
        if (map[arr[i]]==null) {
            map[arr[i]] = arr[i] * r to arr[i] * r * r
        }
        if (pos[arr[i]] == null) {
            pos[arr[i]] = arrayListOf(i)
        }
        else {
            pos[arr[i]]?.add(i)
        }
    }
    var acc = 0L

    for (i in 0 until arr.size - 2) {
        val pair = map[arr[i]]
        if(pair != null) {
            val f = pair.first
            val s = pair.second
            val qf = pos[f]?.count { it > i && it < arr.size-1 } ?: 0
            val qs = pos[s]?.count { it > i + 1 } ?: 0
            if (qf == 0 || qs == 0)
                continue
            acc +=  qf * qs
        }
    }
    return acc
}



fun s3(arr: Array<Long>, r: Long) : Long {
    val map : MutableMap<Long, Pair<Long, Long>> = mutableMapOf()
    val freq : MutableMap<Long, Int> = mutableMapOf()
    val table : MutableMap<Long, Int> = mutableMapOf()

    for (i in 0 until arr.size) {
        val v = arr[i]
        val p = arr[i] * r
        val q = p * r
        if (map[arr[i]]==null) {
            map[v] = p to q
        }


        val m = v / r
        val n = v / (r * r)
        // v faz parte de uma triplet
        if (map[m] != null && map[n] != null) {
            table[v] = table[v]?.plus(1) ?: 1
            table[m] = table[m]?.plus(freq[m] ?: 0) ?: 1
            table[n] = table[n]?.plus(freq[n] ?: 0) ?: 1
        }

        freq[v] = freq[v]?.plus(1) ?: 1

    }
    var acc = 0L
    for (i in 0 until arr.size) {
        val pair = map[arr[i]]
        if (pair != null) {
            val f = pair.first
            val s = pair.second
        }
    }

    return acc
}




// Complete the countTriplets function below.
fun countTriplets(arr: Array<Long>, r: Long): Long {
    return s3(arr, r)
}


fun readLongs() : Array<Long> = readLine()?.split(" ")?.map { it.toLong() }?.toTypedArray() ?: emptyArray<Long>()


fun main(args: Array<String>) {
    val pairInt = readLongs()
    val values = readLongs()
    println(countTriplets(values, pairInt[1]))
}