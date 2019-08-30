package com.hakivin.cptestjava;

import java.util.Scanner;

public class ConquerTheWorld {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] graph = new int[n][n];
        int[][] army = new int[n][2];
        for (int i = 0; i < n-1; i++){
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int c = scanner.nextInt();
            graph[u-1][v-1] = c;
            graph[v-1][u-1] = c;
        }

        for (int j = 0; j < n; j++){
            int xi = scanner.nextInt();
            int ji = scanner.nextInt();
            army[j][0] = xi;
            army[j][1] = ji;
        }

        int[][] map = new int[n][n];
        for (int i = 0; i < n;i++){
            map[i] = dijkstra(graph, i);
        }
        int cost = 0;
        for (int i = 0; i < army.length;){
            int[] nation = army[i];
            int[] shortestPath = map[i];
            if (!isNeeded(nation)){
                i++;
            } else {
                int smallest = smallestIndex(shortestPath,army);
                    army[smallest][0] -= 1;
                    army[i][0] += 1;
                    cost += shortestPath[smallest];
            }
        }
        System.out.println(cost);
    }

    private static boolean isAvailable(int[] arr){
        return arr[0] > arr[1];
    }

    private static boolean isNeeded(int[] arr){
        return arr[0] < arr[1];
    }

    private static int[] dijkstra(int[][] graph, int src) {
        int V = graph.length;
        int[] dist = new int[V];
        Boolean[] sptSet = new Boolean[V];
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }
        dist[src] = 0;
        for (int count = 0; count < V-1; count++) {
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;
            for (int v = 0; v < V; v++)

                if (!sptSet[v] && graph[u][v]!=0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u]+graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }
    return dist;
    }

    private static int smallestIndex(int[] array, int[][] xy) {
        int currentValue = Integer.MAX_VALUE;
        int smallestIndex = -1;
        for (int j=0; j < array.length; j++) {
            if (array[j] < currentValue && array[j] != 0 && isAvailable(xy[j]))
            {
                currentValue = array[j];
                smallestIndex = j;
            }
        }
        return smallestIndex;
    }

    private static int minDistance(int[] dist, Boolean[] sptSet) {
        int min = Integer.MAX_VALUE, min_index=-1;
        int V = dist.length;
        for (int v = 0; v < V; v++)
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        return min_index;
    }
}
