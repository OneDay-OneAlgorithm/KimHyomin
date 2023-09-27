package boj.graph

fun main(){
    P1167().solution()
}

class P1167 {
    val tree = MutableList(100000+1){ArrayList<Node>()}
    var visited = MutableList(100000+1){false}
    var N = 0
    var width = 0L

    data class Node(val idx : Int, val w : Int)
    fun solution() = with(System.`in`.bufferedReader()){

        val n = readLine().toInt()
        N=n

        repeat(n){
            val input = readLine().split(" ").map{it.toInt()}
            val v = input[0]

            for(i in 1 until input.size -1 step 2){
                tree[v].add(Node(input[i],input[i+1]))
            }
        }

        visited[1] = true
        val res = dfs(Node(1,0),0)
        visited = MutableList(100000+1){false}
        visited[res.first] = true
        val ans =  dfs(Node(res.first,0),0)
        println(width)

    }

    val dx = listOf(1,0,-1,0)
    val dy = listOf(0,-1,0,1)

    fun dfs( node : Node, dist : Long) : Pair<Int,Long>{
        var pos = Pair(-1,-1L)

        if(dist > width){
            width = dist
            pos = Pair(node.idx, dist)
        }

        var max = 0L
        var vertex = -1

        for(v in tree[node.idx]){
            if(visited[v.idx]) continue

            visited[v.idx] = true
            val res = dfs(v,dist+v.w)

            if(res.second > max){
                max = res.second
                vertex = res.first
            }
        }

        if(vertex == -1) return pos
        else return Pair(vertex,max)
    }
}