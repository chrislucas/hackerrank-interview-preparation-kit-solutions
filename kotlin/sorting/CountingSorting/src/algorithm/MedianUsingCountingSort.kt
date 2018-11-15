package algorithm

/**
 * https://www.geeksforgeeks.org/median-and-mode-using-counting-sort/
 * */


fun median(data: Array<Int>) : Double {
    //
    val maxValue = data.max()!!
    // tabela de frequencia
    val count = Array<Int>(maxValue + 1) {0}
    // contar frequencia.
    // se quisessemos saber a MODA desse array bastava verificar o maior valor de count[]
    for (i in data)
        count[i] += 1

    // soma parcial para armazenar na tabela de frequencia quantos elementos <= a data[i]
    // existem em data
    for (i in 1 .. maxValue)
        count[i] += count[i-1]

    val sz = data.size

    val copy = Array(sz) {0}
    for(i in 0 until sz) {
        copy[count[data[i]]-1] = data[i]
        count[data[i]]--
    }

    for(i in 0 until sz)
        data[i] = copy[i]

    if (sz and 1 == 0) {
        return (data[(sz-1)/2].toDouble() + data[sz/2].toDouble()) / 2.0f
    }

    return data[sz/2].toDouble()
}



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
    for(i in p..q)
        data[i] = copy[i]

    val mid =  (q-p)/2+p
    // se a limite superior - inferior for um numero par temos um intervalo com uma quantidade
    // impar de elementos: p = 7 q = 9 [7,8,9] (q-p)/2+p -> 2/2+7=8
    // do contrario a quantidade de elementos é par: p:0 q:3 [0,1,2,3] (q-p)/2+p -> 3/2+0= 1
    // no caso de quantidade par de elementos a mediana e a media entre mid e mid+1
    if ((q-p) and 1 == 0) {
        return data[mid].toDouble()
    }
    return (data[mid].toDouble() + data[mid+1].toDouble()) / 2.0f
}



fun testFnMedian() {
    println(median(arrayOf(1,5,2,3,4,5,12,7,2,3)))
    println(median(arrayOf(1,4,1,2,7,1,2,5,3,6)))
}


fun testFnMedianInRange() {
    println(medianInRange(arrayOf(1,4,1,2,7,1,2,5,3,6), 0, 5))
    println(medianInRange(arrayOf(1,4,1,2,7,1,2,5,3,6), 0, 4))
    println(medianInRange(arrayOf(1,5,2,3,4,5,12,7,2,3), 0, 4))
}

fun main(args: Array<String>) {
    //testFnMedian()
    testFnMedianInRange()
}