package algorithm

// extension function
fun <T> Array<T>.forEachInRange(range: IntRange,  compare: ( cur: T, cmp: T) -> T) : T? {
    if (this.isEmpty())
        return null
    else if(this.size < range.last)
        return null
    var rs = this[range.first]
    for (i in (range.first+1..range.last)) {
        rs = compare(this[i], rs)
    }
    return rs
}

fun sort(data: Array<Int>)  {
    val maxValue = data.max()!! + 1
    val count =  Array(maxValue) { 0 }
    // criando um mapa de contagem de repeticoes de valores no array
    for (i in data) {
        count[i] += 1
    }
    // o i-esimo indice ira armazenar quantos numeros <= a data[i]
    // existem em data. Sabendo quantos numeros menores ou iguais a data[i] temos
    // podemos posiciona-lo no array no lugar certo
    for(i in 1 until maxValue) {
        count[i] += count[i-1]
    }
    val copy = Array(data.size) {0}
    for (i in (data.size-1 downTo 0) ) {
        copy[count[data[i]]-1] = data[i]
        count[data[i]] -= 1
    }
    data.forEachIndexed { i, j -> data[i] = copy[i] }
}

fun sortRange(data: Array<Int>, p: Int, q: Int) {
    val comp : (p: Int, q: Int) -> Int = { pp, qq -> if (pp > qq) pp else qq}
    val maxValue = data.forEachInRange ((p..q), comp)
    if (maxValue != null) {
        val count = Array(maxValue+1) {0}
        // tabela de frequencia
        for (i in (p..q)) {
            count[data[i]] += 1
        }
        // soma parcial da tabela de frequencia
        for (i in ((p+1)..maxValue)) {
            count[i] += count[i-1]
        }
        // reposicionar os elementos
        val copy = Array(data.size) {0}
        for ( i in q downTo p) {
            copy[count[data[i]]-1] = data[i]
            count[data[i]]--
        }
        for (i in (p..q))
            data[i] = copy[i]
    }
}


fun testCountingSort() {
    testCountingSort(arrayOf(1,5,2,3,4,5,12,7,2,3))
    testCountingSort(arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9))
    testCountingSort(arrayOf(1,5,2,100,3,4,5,12,7,8,9,2,3,5))
    testCountingSort(arrayOf(1,5,2,100,3,4,5,12,7))
}

fun testCountingSort(data: Array<Int>) {
    sort(data)
    showin(data)
}

fun testCountingSortRange() {
    testCountingSortRange(arrayOf(9, 8, 7, 6, 5, 0, 1, 2, 3, 4), 0, 5)
    testCountingSortRange(arrayOf(5, 6, 7, 8, 9, 0, 1, 2, 3, 4), 0, 5)
    testCountingSortRange(arrayOf(5, 6, 7, 8, 9, 0, 1, 2, 3, 4), 0, 4)
    testCountingSortRange(arrayOf(1, 5, 2, 3, 4, 5, 12, 7, 2, 3), 0, 5)
    testCountingSortRange(arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), 0, 5)
}

fun testCountingSortRange(data: Array<Int>, p: Int, q: Int) {
    sortRange(data, p, q)
    showin(data)
}

fun <T> showin(p: Array<T>) {
    p.forEach { print(" $it") }
    println("")
}

fun main(args: Array<String>) {
    testCountingSortRange()
}