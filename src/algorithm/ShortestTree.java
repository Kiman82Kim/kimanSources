package algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/*
 *
최소 신장 트리.
프림 알고리즘.
입력
T
node 개수
edge 개수
node a, node b, ab 길이

1
9
15
0 1 45
0 2 220
0 3 79
1 4 98
1 3 168
2 8 19
3 4 15
3 5 33
3 6 60
4 6 133
4 7 53
5 2 10
5 6 93
5 8 65
6 7 100
6 8 111

출력
0 1 45
0 3 79
3 5 33
3 4 15
5 2 10
2 8 19
3 6 60
4 7 53

 */

public class ShortestTree {

	static int W[][];
	static int nodeCnt;
	static int edgeCnt;
	static int[] nearNodeList;
	static ArrayList<Edge> edgeList;
	static ArrayList<Edge> linkedEdgeList;
	static ArrayList<Integer> usedNodeList;

	static class Destance {
		int from;
		int length;
	}

	static class Edge {
		int from;
		int to;
		int path;
		int flag;

		public Edge(int from, int to, int path) {
			this.from = from;
			this.to = to;
			this.path = path;
			flag = 0;
		}

		public Edge() {

		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		for (int c = 0; c < TC; ++c) {
			nodeCnt = sc.nextInt();
			edgeCnt = sc.nextInt();
			W = new int[nodeCnt][nodeCnt];
			edgeList = new ArrayList<Edge>();
			linkedEdgeList = new ArrayList<Edge>();
			usedNodeList = new ArrayList<Integer>();
			nearNodeList = new int[nodeCnt];
			initPath();
			for (int e = 0; e < edgeCnt; ++e) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				int path = sc.nextInt();
				Edge edge = new Edge(from, to, path);
				edgeList.add(edge);
				addPath(from, to, path);
			}
			prim();
			print(linkedEdgeList);
		}
	}

	private static void print(ArrayList<Edge> edgeList) {
		for (Edge edge : edgeList) {
			System.out.println(edge.from + " " + edge.to + " " + edge.path);
		}
	}

	private static void prim() {
		int nextNodeNum = edgeList.get(0).from;
		usedNodeList.add(0);
		ArrayList<Edge> findedEdges = new ArrayList<Edge>();
		for (int i = 0; i < nodeCnt - 1; ++i) {
			findLinkedEdges(nextNodeNum, findedEdges);
			Edge shortestEdge = findShortestEdge(findedEdges);
			nextNodeNum = findNextNode();
			linkedEdgeList.add(shortestEdge);
		}

	}

	private static void findLinkedEdges(int node, ArrayList<Edge> findedEdges) {
		nearNodeList[node] = -1; // -1 = 한번 지나온 길. 자기 자신, 0>n = 가까운 node 까지 길이.
		for (Edge tEdge : edgeList) {
			int tNode;
			if ((tEdge.from == node) && tEdge.flag != 1) {
				tNode = tEdge.to;
				if (!isInUsedNodeList(tNode)) {
					if (tEdge.path < nearNodeList[tNode]) {
						nearNodeList[tNode] = tEdge.path;
						findedEdges.add(tEdge);
					}
				}
			} else {
				if ((tEdge.to == node) && tEdge.flag != 1) {
					tNode = tEdge.from;
					if (!isInUsedNodeList(tNode)) {
						if (tEdge.path < nearNodeList[tNode]) {
							nearNodeList[tNode] = tEdge.path;
							findedEdges.add(tEdge);
						}
					}
				}
			}
		}
	}

	static class Compare implements Comparator<Edge> {
		@Override
		public int compare(Edge a, Edge b) {
			if (a.path > b.path) {
				return 1;
			} else {
				return -1;
			}
		}
	}

	private static Edge findShortestEdge(ArrayList<Edge> findedEdges) {
		// Collections.sort(edges, new Compare());
		int min = Integer.MAX_VALUE;
		Edge shortestEdge = new Edge();
		int index = 0;
		int i = -1;
		for (Edge tEdge : findedEdges) {
			++i;
			if (tEdge.path < min) {
				min = tEdge.path;
				shortestEdge = tEdge;
				index = i;
			}
		}
		findedEdges.remove(index);
		return shortestEdge;
	}

	private static int findNextNode() {
		int min = Integer.MAX_VALUE;
		int index = 0;
		for (int i = 0; i < nearNodeList.length; ++i) {
			if (nearNodeList[i] == -1) {
				continue;
			}
			if (nearNodeList[i] < min) {
				min = nearNodeList[i];
				index = i;
			}
		}
		usedNodeList.add(index);
		nearNodeList[index] = -1;
		return index;
	}

	private static boolean isInUsedNodeList(int node) {
		for (int tNode : usedNodeList) {
			if (node == tNode) {
				return true;
			}
		}
		return false;
	}

	private static void initPath() {
		for (int i = 0; i < nodeCnt; ++i) {
			for (int j = 0; j < nodeCnt; ++j) {
				W[i][j] = Integer.MAX_VALUE;
			}
			nearNodeList[i] = Integer.MAX_VALUE;
		}
	}

	private static void addPath(int x, int y, int path) {
		W[x][y] = path;
		W[y][x] = path;
	}
}
