import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Main{

    private int N, M;
    private Route[][] routes = new Route[51][51];
    private int[] routeSize = new int[51];

    public static void main(String... args){
        (new Main()).solve();
    }

    private void solve(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            N = Integer.parseInt(br.readLine());
            List<Integer> routeSizeInput = Arrays.stream(br.readLine().split(" "))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
            for(int i = 0; i < routeSizeInput.size(); i++){
                routeSize[i+1] = routeSizeInput.get(i);
            }
            M = Integer.parseInt(br.readLine());
            initRoutes();
            for(int i = 0; i < M; i++){
                List<Integer> line = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
                routes[line.get(0)][line.get(1)] = new Route(line.get(2), line.get(3));
                routes[line.get(2)][line.get(3)] = new Route(line.get(0), line.get(1));
            }
            int K = Integer.parseInt(br.readLine());
            for(int i = 0; i < K; i++){
                List<Integer> line = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
                dijkstra(line.get(0), new Route(line.get(1), line.get(2)), new Route(line.get(3), line.get(4)));
            }
        }catch(IOException ignored){}
    }

    private void initRoutes(){
        for(int i = 0; i < 51; i++){
            for(int j = 0; j < 51; j++){
                routes[i][j] = new Route(0, 0);
            }
        }
    }

    private void dijkstra(int time, Route start, Route destination){
        int[][] check = new int[51][51];
        for(int i = 0; i < 51; i++){
            for(int j = 0; j < 51; j++){
                check[i][j] = -1;
            }
        }
        PriorityQueue<QueueElement> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new QueueElement(start.nextRoute, start.nextIdx, 0));
        while(!priorityQueue.isEmpty()){
            QueueElement current = priorityQueue.poll();
            if(check[current.currentRoute][current.currentIdx] > -1
                && check[current.currentRoute][current.currentIdx] <= current.currentTime){
                continue;
            }
            check[current.currentRoute][current.currentIdx] = current.currentTime;


            int nextRoute = current.currentRoute;
            int nextPlusIdx = current.currentIdx + 1;
            int nextMinusIdx = current.currentIdx - 1;
            int nextTime = current.currentTime + 1;
            if(nextMinusIdx >= 1
                && (check[nextRoute][nextMinusIdx] == -1 || check[nextRoute][nextMinusIdx] > nextTime)){
                priorityQueue.add(new QueueElement(nextRoute, nextMinusIdx, nextTime));
            }
            if(nextPlusIdx <= routeSize[current.currentRoute]
                && (check[nextRoute][nextPlusIdx] == -1 || check[nextRoute][nextPlusIdx] > nextTime)){
                priorityQueue.add(new QueueElement(nextRoute, nextPlusIdx, nextTime));
            }
            if(routes[current.currentRoute][current.currentIdx].nextIdx != 0) {
                nextRoute = routes[current.currentRoute][current.currentIdx].nextRoute;
                int nextIdx = routes[current.currentRoute][current.currentIdx].nextIdx;
                nextTime = current.currentTime + time;
                if(check[nextRoute][nextIdx] > -1
                    && check[nextRoute][nextIdx] <= nextTime) {
                    continue;
                }
                priorityQueue.add(new QueueElement(nextRoute, nextIdx, nextTime));
            }
        }
        System.out.println(check[destination.nextRoute][destination.nextIdx]);
    }

    private static class Route{

        private int nextRoute;
        private int nextIdx;

        public Route(int nextRoute, int nextIdx){
            this.nextRoute = nextRoute;
            this.nextIdx = nextIdx;
        }

    }

    private static class QueueElement implements Comparable<QueueElement>{

        int currentRoute;
        int currentIdx;
        int currentTime;

        private QueueElement(int currentRoute, int currentIdx, int currentTime) {
            this.currentRoute = currentRoute;
            this.currentIdx = currentIdx;
            this.currentTime = currentTime;
        }

        @Override
        public int compareTo(QueueElement queueElement){
            if(currentTime < queueElement.currentTime){
                return -1;
            }
            if(currentTime == queueElement.currentTime){
                return 0;
            }
            return 1;
        }

    }

}
