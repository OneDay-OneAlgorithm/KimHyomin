package boj.topology_sort

import kotlin.system.exitProcess

fun main(){
    P2623().solution()
}
class P2623 {
    fun solution()=with(System.`in`.bufferedReader()){
        val(n,m)= readLine().split(" ").map{it.toInt()}
        val arr = MutableList(n+1){ArrayList<Int>()}
        val inDegree = MutableList(n+1){0}
        val queue = ArrayDeque<Int>()
        repeat(m){
            val inputs = readLine().split(" ").map{it.toInt()}
            val tot = inputs[0]
            for( i in inputs.size-1 downTo 2){
                arr[inputs[i]].add(inputs[i-1])
                inDegree[inputs[i-1]]++
            }
        }

        for(i in 1..n){
            if(inDegree[i] ==0) queue.add(i)
        }
        val ans = ArrayList<Int>()

        while(queue.isNotEmpty()){
            val cur = queue.removeFirst()
            ans.add(cur)
            for(k in arr[cur]){
                if(inDegree[k]==0){
                    println(0)
                    exitProcess(0)
                }
                inDegree[k]--
                if(inDegree[k]==0){
                    queue.add(k)
                }
            }
        }
        if(ans.size < n) println(0)
        else {
            ans.reversed().forEach{println(it)}
        }
    }
}