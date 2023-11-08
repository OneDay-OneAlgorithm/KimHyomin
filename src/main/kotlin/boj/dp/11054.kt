package boj.dp


fun main(){
    P11054().solution()
}

class P11054 {
    fun solution ()  = with(System.`in`.bufferedReader()){

        val size = readLine().toInt()
        val input = readLine().split(" ").map{it.toInt()}

        val result = Array(2){IntArray(size){1}}
        result[0][0] = 1
        result[1][0] = 1


        for(i in 1 until size){
            //본인보다 작으면서 가장 큰수 탐색
            var leftMin = -1
            var rightMin = -1


            var flag = false
            for(j in 0 until i){
                if(input[i] > input[j] ){ // 값이 더 작은거 발견
                    leftMin = Math.max(result[0][j],leftMin)
                    flag = true
                }
            }

            if(flag)result[0][i] = leftMin+1

            flag = false
            val rightCursor = size  -i -1

            for(j in size -1  downTo rightCursor +1 ){
                if(input[rightCursor] > input[j] ){ // 값이 더 작은거 발견
                    rightMin = Math.max(rightMin, result[1][j])
                    flag = true
                }
            }

            if(flag)result[1][rightCursor] = rightMin+1
        }

        var ans = 0
        for(i in 0 until size){
            ans = Math.max(ans, result[0][i] + result[1][i] -1)
        }
        println(ans)
    }
}

