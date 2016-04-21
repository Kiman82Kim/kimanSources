package algorithm;

import java.util.Scanner;

/*
입력의 첫 줄에는 테스트 케이스의 수 C (<= 50) 이 주어진다. 
각 테스트 케이스의 첫 줄에는 도시의 수 N (3 <= N <= 8) 이 주어진다. 
그 후 N 줄에, 각 N 개씩의 실수로 도시간의 거리가 주어진다. 
도시들은 1 부터 N 까지의 숫자로 표현되며, i 번째 줄의 j 번째 실수는 i번째 도시와 j번째 도시 사이의 거리이다. 
각 거리는 0 이상 1415 이하이고, 소수점 밑 열 자리까지 주어진다.

출력
테스트 케이스마다 한 줄에 최소 경로의 길이를 소수점 밑 열 자리까지 출력한다. 1e-7 이하의 절대/상대 오차가 있어도 맞는 답으로 인정한다.
예제 입력
2
3
0.0000000000  611.6157225201  648.7500617289
611.6157225201  0.0000000000  743.8557967501
648.7500617289  743.8557967501  0.0000000000
4
0.0000000000  326.0008994586  503.1066076077  290.0250922998
326.0008994586  0.0000000000  225.1785728436  395.4019367384
503.1066076077  225.1785728436  0.0000000000  620.3945520632
290.0250922998  395.4019367384  620.3945520632  0.0000000000

1
3
0 6 7
6 0 8
7 8 0


1
4
0 6 7 5
6 0 8 9
7 8 0 3
5 9 3 0

예제 출력
1260.3657842490
841.2045646020
*/
public class Tcp2 {
	static int N;
	static double[][] city;
	static double[][] dCity;
	static double shortestLen;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		for (int i = 0; i < TC; ++i) {
			N = sc.nextInt();
			shortestLen = Double.MAX_VALUE;
			city = new double[N][N];
			dCity = new double[N][1 << N];
			int allPath = (1 << N) - 1;
			for (int j = 0; j < N; ++j) {
				for (int k = 0; k < N; ++k) {
					city[j][k] = sc.nextDouble();
				}
			}
			for (int first = 0; first < N; ++first) {
				double min = calcShortestPath(first, allPath & (~(1 << first)));
				shortestLen = Double.min(min, shortestLen);
			}
			System.out.println(shortestLen);
		}
	}

	static private double calcShortestPath(int from, int path) {
		double min = 0;
		if (dCity[from][path] != 0) {
			return dCity[from][path];
		}
		if (Integer.bitCount(path) == 1) {
			dCity[from][path] = city[from][bitPosition(path)];
			return city[from][bitPosition(path)];
		}

		min = Double.MAX_VALUE;
		for (int nextCity = 0; nextCity < N; ++nextCity) {
			if ((path & (1 << nextCity)) == 0) {
				continue;
			}
			min = Double.min(city[from][nextCity] + calcShortestPath(nextCity, path & (~(1 << nextCity))), min);
			dCity[from][path] = min;
		}
		return min;
	}

	private static int bitPosition(int bit) {
		for (int i = 0;; ++i) {
			int temp = 1 << i;
			if (temp > bit) {
				break;
			}
			if ((bit & temp) != 0) {
				return i;
			}
		}
		return -1;
	}

}
