fun dfs(start: Int, v: Int, used: MutableList<Boolean>, result: MutableList<MutableList<Int>>, grf: MutableList<MutableList<Int>>): Unit {
    used[v] = true
    for (u in grf[v]) {
        if (used[u]) {
            continue
        }
        result[start].add(u)
        dfs(start, u, used, result, grf)
    }
}

fun propagateHandshakes(friends: Map<String, Set<String>>): Map<String, Set<String>> {
    var cnt: Int = 0
    val nameToId: MutableMap<String, Int> = mutableMapOf()
    val idToName: MutableMap<Int, String> = mutableMapOf()
    var used: MutableList<Boolean> = mutableListOf()
    val grf: MutableList<MutableList<Int>> = mutableListOf()
    val result: MutableList<MutableList<Int>> = mutableListOf()
    for ((key, value) in friends) {
        var currentName: Int? = nameToId[key]
        if (currentName == null) {
            nameToId[key] = cnt
            idToName[cnt] = key
            cnt++
        }
        for (el in value) {
            currentName = nameToId[el]
            if (currentName == null) {
                nameToId[el] = cnt
                idToName[cnt] = el
                cnt++
            }
        }
    }
    for (i in 0 until cnt) {
        grf.add(mutableListOf())
        used.add(false)
        result.add(mutableListOf())
    }
    for ((v, list) in friends) {
        for (u in list) {
            val vIndex: Int = nameToId[v]!!
            val uIndex: Int = nameToId[u]!!
            grf[vIndex].add(uIndex)
        }
    }
    for (i in 0 until cnt) {
        dfs(i, i, used, result, grf)
        used.fill(false)
    }
    val answer: MutableMap<String, Set<String>> = mutableMapOf()
    for (i in 0 until cnt) {
        val currentAnswer: MutableSet<String> = mutableSetOf()
        for (j in result[i]) {
            currentAnswer.add(idToName[j]!!)
        }
        answer[idToName[i]!!] = currentAnswer
    }
    return answer.toMap()
}
