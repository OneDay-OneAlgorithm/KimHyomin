package boj.topology_sort


fun main(){
    P1516().solution()
}

class P1516{
    fun solution() = with(System.`in`.bufferedReader()){
        val n = readLine().toInt()
        val graph = MutableList(n+1){ArrayList<Int>()}
        val inDegree =  MutableList(n+1){0}
        val origin = MutableList(n+1){0}
        val result = MutableList(n+1){0}
        val queue = ArrayDeque<Int>()

        repeat(n){
            val inputs = readLine().split(" ").map{it.toInt()}
            origin[it+1] = inputs[0]
            for(i in 1 until inputs.size -1){
                inDegree[it+1]++
                graph[inputs[i]].add(it+1)
            }

        }


        for(i in 1..n){
            if(inDegree[i] == 0 ){
                queue.add(i)
                result[i] = origin[i]
            }
        }

        while(queue.isNotEmpty()){
            val cur= queue.removeFirst()
            for(k in  graph[cur]){
                inDegree[k]--
                result[k] =  Math.max( result[cur] + origin[k], result[k])
                if(inDegree[k] == 0){
                    queue.add(k)
                }
            }
        }

        for(i in 1 until result.size){
            println(result[i])
        }
    }
}