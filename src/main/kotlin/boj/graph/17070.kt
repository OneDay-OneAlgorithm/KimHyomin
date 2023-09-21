package boj.graph


fun main(){
    P17070().solution()
}


class P17070{

    data class Pipe(val r  :Int, val c : Int, val direct  : Int)
    val map = Array(17){IntArray(17){0}}
    val visit = MutableList(17){MutableList(17){MutableList(3){0} }}
    var n = 0
    var cnt = 0;

    fun solution() = with(System.`in`.bufferedReader()){
        n = readLine().toInt()
        repeat(n){
            map[it] =  readLine().split(" ").map{it.toInt()}.toIntArray()
        }
        dfs(Pipe(0,1,0))
        println(cnt)
    }


    fun dfs(cur:Pipe){
        if(cur.r == n-1 && cur.c == n-1){
            cnt ++
            return
        }

        when(cur.direct){
            0 ->{
                if(cur.c +1 < n && map[cur.r][cur.c+1]!=1 ){
                    dfs(Pipe(cur.r, cur.c+1,0))
                }
            }
            1->{
                if( cur.r + 1< n  && map[cur.r+1][cur.c]!=1 ){
                    dfs(Pipe(cur.r+1,cur.c,1))
                }

            }
            else ->{
                if(cur.c +1 < n && map[cur.r][cur.c+1]!=1 ){
                    dfs(Pipe(cur.r, cur.c+1,0))
                }

                if( cur.r + 1< n  && map[cur.r+1][cur.c]!=1 ){
                    dfs(Pipe(cur.r+1,cur.c,1))
                }

            }
        }

        if(cur.c + 1 < n && cur.r + 1< n  && map[cur.r+1][cur.c+1]!=1 ){
            if(map[cur.r+1][cur.c] == 0 && map[cur.r][cur.c+1]==0)
                dfs(Pipe(cur.r+1,cur.c+1,2))
        }
    }

    fun bfs(){

        val queue = ArrayDeque<Pipe>()
        queue.add(Pipe(0,1,0))
        visit[0][0][0] = 0
        visit[0][1][0] = 1

        /*
        *  0 : 가로 . 1 : 세로 , 2 : 대각선
        */
        var count = 0
        while(queue.isNotEmpty()){
            val cur = queue.removeFirst()

            if(cur.r == n-1 && cur.c == n-1){
                count++
                continue
            }

            when(cur.direct){
                0 ->{
                    if(cur.c +1 < n && map[cur.r][cur.c+1]!=1 ){
                        // visit[cur.r][cur.c+1][0]++
                        queue.add(Pipe(cur.r,cur.c+1,0))
                    }

                    if(cur.c + 1 < n && cur.r + 1< n  && map[cur.r+1][cur.c+1]==0 ){
                        if(map[cur.r][cur.c+1]==0 &&map[cur.r+1][cur.c]==0 ){
                            queue.add(Pipe(cur.r+1,cur.c+1,2))
                        }

                    }
                }
                1->{

                    if( cur.r + 1< n  && map[cur.r+1][cur.c]!=1 ){
                        //visit[cur.r+1][cur.c][1] ++
                        queue.add(Pipe(cur.r+1,cur.c,1))
                    }

                }
                else->{
                    if(cur.c +1 < n && map[cur.r][cur.c+1]!=1 ){
                        //visit[cur.r][cur.c+1][0] ++
                        queue.add(Pipe(cur.r,cur.c+1,0))
                    }

                    if( cur.r + 1< n  && map[cur.r+1][cur.c]!=1 ){
                        // visit[cur.r+1][cur.c][1] ++
                        queue.add(Pipe(cur.r+1,cur.c,1))
                    }

                }
            }


            if(cur.c + 1 < n && cur.r + 1< n  && map[cur.r+1][cur.c+1]!=1 ){
                queue.add(Pipe(cur.r+1,cur.c+1,2))

            }
        }

        println(count)

    }
}