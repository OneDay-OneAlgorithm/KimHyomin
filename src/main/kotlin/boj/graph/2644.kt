package boj.graph

import java.util.*
import kotlin.collections.ArrayList

fun main(){
    P2644().solution()
}

class P2644 {
    fun solution() = with(System.`in`.bufferedReader()){
        val n = readLine().toInt()
        val (x,y) = readLine().split(" ").map{it.toInt()}
        val k = readLine().toInt()
        val node = Array(n+1){ArrayList<Int>()}
        val visited = BooleanArray(n+1){false}
        var start = 0
        //양방향 그래프
        repeat(k){
            val (x,y) = readLine().split(" ").map{it.toInt()}
            node[x].add(y)
            node[y].add(x)
        }

        val queue = LinkedList<Pair<Int,Int>>()
        queue.add(Pair(x,0)) // 시작점 ,촌수

        while(queue.isNotEmpty()){
            val head = queue.poll()
            visited[head.first] = true

            if(head.first == y){
                println(head.second)
                return
            }
            for(k in node[head.first]){
                if(k!=head.first && !visited[k])  queue.add(Pair(k,head.second+1))
            }
        }
        println(-1)
    }
}