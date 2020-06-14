/*
There are n cities connected by m flights. Each flight starts from city u and arrives at v with a price w.

Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.

Example 1:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 1
Output: 200



The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
Example 2:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 0
Output: 500
 


The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
 

Constraints:

The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
The size of flights will be in range [0, n * (n - 1) / 2].
The format of each flight will be (src, dst, price).
The price of each flight will be in the range [1, 10000].
k is in the range of [0, n - 1].
There will not be any duplicated flights or self cycles
*/

//Solution:

/*This is the First Time i used Dikshtra's Algorithm successfully. I love this.*/
/*Dikshtra's Algorithm*/
class Pair {
	int city, cost;

	Pair(int city, int cost) {
		this.city = city;
		this.cost = cost;
	}
}

class City {
	int city, distFromSrc, costFromSrc;

	City(int city, int distFromSrc, int cost) {
		this.city = city;
		this.distFromSrc = distFromSrc;
		this.costFromSrc = cost;
	}
}

class Solution {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
    	// DFS
        if(n <= 0 || flights == null || flights.length == 0 || K < 0)
        	return -1;

        List<List<Pair>> graph = new ArrayList<>();
        this.buildGraph(graph, n, flights);

        Queue<City> pQueue = new PriorityQueue<>((City c1, City c2) -> c1.costFromSrc - c2.costFromSrc);
        pQueue.offer(new City(src, 0, 0));

        int totalCost = 0;

        while (!pQueue.isEmpty()) {
        	City top = pQueue.poll();

        	if (top.city == dst) {
        		return top.costFromSrc;
        	}

        	if (top.distFromSrc > K) {
        		continue;
        	}

        	List<Pair> neighbors = graph.get(top.city);
        	for (Pair neighbor: neighbors) {
        		pQueue.offer(new City(neighbor.city, top.distFromSrc + 1, top.costFromSrc + neighbor.cost));
        	}
        }

        return -1;
    }

    private void buildGraph(List<List<Pair>> graph, int n, int[][] flights) {
    	for (int i = 0; i < n; i++) {
    		graph.add(new ArrayList<>());
    	}

    	for (int[] flight: flights) {
    		graph.get(flight[0]).add(new Pair(flight[1], flight[2]));
    	}
    }
}
