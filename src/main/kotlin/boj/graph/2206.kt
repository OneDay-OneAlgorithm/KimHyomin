package boj.graph


fun main(){
    P2206().solution()
}


class P2206 {
    val visited = MutableList(1001) { MutableList(1001) {MutableList(2){ 0 }}}
    val map = MutableList(1001) { MutableList<Int>(1001) { Int.MAX_VALUE } }

    var N = 0
    var M = 0
    var isArrived = false
    var min = Int.MAX_VALUE


    val dr = listOf(0, -1, 0, 1)
    val dc = listOf(1, 0, -1, 0)

    data class Pos(val row: Int, val col: Int, var len: Int)

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        N = n
        M = m

        repeat(n) {
            val input = readLine().toCharArray()
            for (i in 1..m) {
                map[it + 1][i] = input[i - 1] - '0'
            }
        }

        val chance = if( map[1][1] == 1) 0 else 1
        bfs(chance,Pos(1,1,1))
        
        if (isArrived) println(min)
        else println(-1)
    }


    fun bfs(chance : Int, pos: Pos) {
        val queue = ArrayDeque<Pair<Pos, Int>>()
        queue.add(Pair(pos, chance))
        visited[1][1][chance] = 1

        while(queue.isNotEmpty()){
            val cur = queue.removeFirst()
            val p = cur.first
            val ch = cur.second

            if(p.row == N && p.col == M){
                isArrived = true
                min = Math.min(min,p.len) // 거리 최소 업데이트
            }

            for(i in 0..3){
                val nr = p.row + dr[i]

                val nc = p.col + dc[i]
                if(nr in 1..N && nc in 1..M){

                    if( map[nr][nc] == 1 && ch == 1){ // 다음이 벽이고 찬스가 하나 남았다면
                        visited[nr][nc][0] =  1 // 찬스 사용 가능이 0 일때 방문처리를 한다
                        queue.add(Pair(Pos(nr,nc,p.len+1),0))
                    }
                    if(map[nr][nc] == 0 && visited[nr][nc][ch] == 0){ // 다음이 이동가능하고 만약 방문하지 않았다면
                        visited[nr][nc][ch] = visited[p.row][p.col][ch] + 1 // 해당 찬스를 이용했을 때 방문한 이력을 전가한다
                        queue.add(Pair(Pos(nr,nc,p.len+1), ch))
                    }
                }
            }
        }
    }
}
