package boj.string

fun main(){
    P1032().solution()
}

class P1032 {
    fun solution() = with(System.`in`.bufferedReader()){
        val n = readLine().toInt()
        val ans = readLine().toCharArray().toMutableList()
        repeat(n-1){
            val input =  readLine().toCharArray()
            input.forEachIndexed{idx,v->
                if(v!=ans[idx]){
                    ans[idx] = '?'
                }
            }
        }
        ans.forEach{print(it)}
    }
}