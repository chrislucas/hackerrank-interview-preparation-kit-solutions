/**
 * DONE
 * */

fun String.isAnagram(str: String) : Boolean {
    if (length != str.length)
        return false
    var i = 0

    val mapReceiver = mutableMapOf<Char, Int>()

    this.forEach {
        if (mapReceiver[it] == null)
            mapReceiver[it] = 1
        else
            mapReceiver[it] = mapReceiver[it]?.plus(1) ?: 1
    }

    val mapArg = mutableMapOf<Char, Int>()
    str.forEach {
        if (mapArg[it] == null)
            mapArg[it] = 1
        else
            mapArg[it] = mapArg[it]?.plus(1) ?: 1
    }

    for ((k, v) in mapReceiver) {
        if(mapArg[k] == null)
            return false
        else if (mapArg[k] != mapReceiver[k])
            return false
    }

    return true
}


fun allSubstrings(str: String) : MutableMap<Int, MutableList<String>> {
    val map = mutableMapOf<Int, MutableList<String>>()
    for (i in 0 until str.length) {
        for(j in 0 until i+1) {
            val p = str.substring(j..i)
            if (map[p.length] != null){
                map[p.length]?.add(p)
            }
            else
                map[p.length] = mutableListOf(p)
        }
    }
    return map
}


// Complete the sherlockAndAnagrams function below.
fun sherlockAndAnagrams(s: String): Int {
    val substrings = allSubstrings(s)
    var count = 0
    substrings.forEach {
        val substrs = it.value
        val lim = substrs.size
        if (lim > 1) {
            for(i in 0 until lim) {
                for (j in i+1 until lim) {
                    if (substrs[i].isAnagram(substrs[j]) || substrs[i] == substrs[j])
                        count++
                }
            }
        }
    }
    return count
}

fun main(args: Array<String>) {
    /*
5
ifailuhkqqhucpoltgtyovarjsnrbfpvmupwjjjfiwwhrlkpekxxnebfrwibylcvkfealgonjkzwlyfhhkefuvgndgdnbelgruel
gffryqktmwocejbxfidpjfgrrkpowoxwggxaknmltjcpazgtnakcfcogzatyskqjyorcftwxjrtgayvllutrjxpbzggjxbmxpnde
mqmtjwxaaaxklheghvqcyhaaegtlyntxmoluqlzvuzgkwhkkfpwarkckansgabfclzgnumdrojexnrdunivxqjzfbzsodycnsnmw
ofeqjnqnxwidhbuxxhfwargwkikjqwyghpsygjxyrarcoacwnhxyqlrviikfuiuotifznqmzpjrxycnqktkryutpqvbgbgthfges
zjekimenscyiamnwlpxytkndjsygifmqlqibxxqlauxamfviftquntvkwppxrzuncyenacfivtigvfsadtlytzymuwvpntngkyhw
    */
    var cases = readLine()?.toInt() ?: 0
    while (cases > 0) {
        println(sherlockAndAnagrams(readLine() ?: ""))
        cases--
    }
}