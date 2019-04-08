/**
 * https://www.hackerrank.com/challenges/frequency-queries/problem?h_l=interview&playlist_slugs%5B%5D%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D%5B%5D=dictionaries-hashmaps
 * */

val freqNumberInserted : MutableMap<Int, Int> = mutableMapOf()



// TLE
fun s1(queries: Array<Array<Int>>) : Array<Int> {
    val rs = arrayListOf<Int>()
    queries.forEach {
        pair ->
        when(pair[0]) {
            // iusert pair[1] in your data structure.
            1 -> {
                if (freqNumberInserted[pair[1]] == null) {
                    freqNumberInserted[pair[1]] = 1
                }
                else {
                    freqNumberInserted[pair[1]] = freqNumberInserted[pair[1]]?.plus(1) ?: 1
                }
            }
            // Delete one occurence of pair[1] from your data structure, if present.
            2 -> {
                if(freqNumberInserted[pair[1]] != null && freqNumberInserted[pair[1]]!! > 0) {
                    freqNumberInserted[pair[1]] = freqNumberInserted[pair[1]]?.minus(1) ?: 0
                }
            }
            // Check if any integer is present whose frequency is exactly pair[1]. If yes, print 1 else 0.
            3 -> {
                var exists = false
                for((k, v) in freqNumberInserted) {
                    if(v == pair[1]) {
                        exists = true
                    }
                }
                rs.add(if(exists) 1 else 0)
            }
        }
    }
    return rs.toTypedArray()
}

val freqQuantityNumbers : MutableMap<Int, Int> = mutableMapOf(1 to 0)


fun s2(queries: Array<Array<Int>>) : Array<Int> {
    val rs = arrayListOf<Int>()
    queries.forEach {
        pair ->
        when(pair[0]) {
            // iusert pair[1] in your data structure.
            1 -> {
                if (freqNumberInserted[pair[1]] == null) {
                    freqNumberInserted[pair[1]] = 1
                    // vetor que guarda a quantidade de frequencias que aparecem no mapa de frequencias
                    freqQuantityNumbers[1] = freqQuantityNumbers[1]!!.plus(1)
                }
                else {
                    freqQuantityNumbers[freqNumberInserted[pair[1]]!!] =
                            freqQuantityNumbers[freqNumberInserted[pair[1]]!!]!!.minus(1)
                    freqNumberInserted[pair[1]] = freqNumberInserted[pair[1]]!!.plus(1)
                    freqQuantityNumbers[freqNumberInserted[pair[1]]!!] =
                            freqQuantityNumbers[freqNumberInserted[pair[1]]!!]!!.plus(1)
                }
            }
            // Delete one occurence of pair[1] from your data structure, if present.
            2 -> {
                if(freqNumberInserted[pair[1]] != null && freqNumberInserted[pair[1]]!! > 0) {
                    freqNumberInserted[pair[1]] = freqNumberInserted[pair[1]]!!.minus(1)

                    freqQuantityNumbers[freqNumberInserted[pair[1]]!!] =
                            freqQuantityNumbers[freqNumberInserted[pair[1]]!!]!!.minus(1)

                }
            }
            // Check if any integer is present whose frequency is exactly pair[1]. If yes, print 1 else 0.
            3 -> {
                rs.add(if(freqQuantityNumbers[pair[1]] ?: 0 > 0) 1 else 0)
            }
        }
    }
    return rs.toTypedArray()
}


// Complete the freqQuery function below.
fun freqQuery(queries: Array<Array<Int>>): Array<Int> = s1(queries)

/**
 * DONE
 * */

fun run() {
    val queries = readLine()?.toInt() ?: 0
    val rs = StringBuilder()
    for (i in 0 until queries) {
        val pair = readLine()?.trimEnd()
                ?.split(" ")
                ?.map { it.toInt() }?.toTypedArray() ?: emptyArray()
        when(pair[0]) {
            // iusert pair[1] in your data structure.
            1 -> {
                if (freqNumberInserted[pair[1]] == null) {
                    freqNumberInserted[pair[1]] = 1
                    // vetor que guarda a quantidade de frequencias que aparecem no mapa de frequencias
                    freqQuantityNumbers[1] = freqQuantityNumbers[1]!!.plus(1)
                }
                else {
                    freqQuantityNumbers[freqNumberInserted[pair[1]]!!] =
                            freqQuantityNumbers[freqNumberInserted[pair[1]]!!]?.minus(1) ?: 0

                    if(freqQuantityNumbers[freqNumberInserted[pair[1]]!!] ?: 0 < 0)
                        freqQuantityNumbers[freqNumberInserted[pair[1]]!!] = 0

                    freqNumberInserted[pair[1]] = freqNumberInserted[pair[1]]!!.plus(1)

                    freqQuantityNumbers[freqNumberInserted[pair[1]]!!] =
                            freqQuantityNumbers[freqNumberInserted[pair[1]]!!]?.plus(1) ?: 1
                }
            }
            // Delete one occurence of pair[1] from your data structure, if present.
            2 -> {
                if(freqNumberInserted[pair[1]] != null && freqNumberInserted[pair[1]]!! > 0) {
                    freqQuantityNumbers[freqNumberInserted[pair[1]]!!] =
                            freqQuantityNumbers[freqNumberInserted[pair[1]]!!]?.minus(1) ?: 0

                    if(freqQuantityNumbers[freqNumberInserted[pair[1]]!!] ?: 0 < 0)
                        freqQuantityNumbers[freqNumberInserted[pair[1]]!!] = 0

                    freqNumberInserted[pair[1]] = freqNumberInserted[pair[1]]?.minus(1) ?: 0

                    freqQuantityNumbers[freqNumberInserted[pair[1]]!!] =
                            freqQuantityNumbers[freqNumberInserted[pair[1]]!!]?.plus(1) ?: 0
                }
            }
            // Check if any integer is present whose frequency is exactly pair[1]. If yes, print 1 else 0.
            3 -> {
                rs.append(if(rs.isNotEmpty()) "\n${if(freqQuantityNumbers[pair[1]] ?: 0 > 0) 1 else 0}"
                else "${if(freqQuantityNumbers[pair[1]] ?: 0 > 0) 1 else 0}")
            }
        }
    }
    println(rs)
}

fun main(args: Array<String>) {
    run()
}

fun run2() {
    val queries = readLine()?.toInt() ?: 0
    val data = Array(queries) { _ -> Array(2) {0} }
    for (i in 0 until queries) {
        val pairs = readLine()?.trimEnd()
                ?.split(" ")
                ?.map { it.toInt() }?.toTypedArray() ?: emptyArray()
        data[i] = pairs
    }
    s2(data).forEach { println(it) }
}