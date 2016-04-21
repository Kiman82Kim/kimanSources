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

문제 풀이
이 문제는 다음과 같이 나누어 풀 수 있다.
도시 {1, 2, 3, 4, 5} 가 있다고 했을 때, 최소 경로를 min {1, 2, 3, 4, 5} 이라고 정의하자.
그렇다면 최소 값은,
min {1, 2, 3, 4, 5} = min (
1 > min {2, 3, 4, 5}, // 1 에서 출발하여 {2, 3, 4, 5} 를 순회하는 최소값
2 > min {1, 3, 4, 5}, // 마찬가지로 2 에서 출발하여 나머지를 순회하는 최소값
3 > min {1, 2, 4, 5},
4 > min {1, 2, 3, 5},
5 > min {1, 2, 3, 4}
)
 
1 > min {2, 3, 4, 5} 은 다음과 같은 의미를 가진다.
1 > min {2, 3, 4, 5} = min (
distance(1, 2) + min (2 > {3, 4, 5}), // "1 에서 2까지 거리" + 2 에서 출발하여 나머지 도시를 순회하는 최소값
distance(1, 3) + min (2 > {2, 4, 5}),
distance(1, 4) + min (2 > {2, 3, 5}),
distance(1, 5) + min (2 > {2, 3, 4})
)
 
이렇게 반복해서 전개하면 마지막에는 4 > {5} 와 같이 가장 단순한 형태로 귀결된다. 이 값은 distance(4 ,5) 이다.
 */
public class Tsp1 {

	static int C;
	static int N;
	static double shortestPath;
	static double[][] citiesArr;
	static double[][] citiesArrWeight;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		C = sc.nextInt();
		for (int c = 0; c < C; ++c) {
			N = sc.nextInt();
			int leftPath = (1 << N) - 1;
			citiesArrWeight = new double[N][leftPath + 1];
			citiesArr = new double[N][N];
			shortestPath = Double.MAX_VALUE;
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					citiesArr[i][j] = sc.nextDouble();
				}
			}
			for (int first = 0; first < N; ++first) {
				double len = calcShortestPath(first, leftPath & (~(1 << first)));
				shortestPath = Math.min(len, shortestPath);
			}
			System.out.println(shortestPath);
		}
	}

	public static double calcShortestPath(int from, int leftPath) {
		double min = citiesArrWeight[from][leftPath];
		if (min != 0) {
			return min;
		}
		if (countBit(leftPath) == 1) {
			int pos = bitPosition(leftPath);
			if (citiesArrWeight[from][leftPath] == 0 || citiesArr[from][pos] < min) {
				citiesArrWeight[from][leftPath] = citiesArr[from][pos];
				return citiesArrWeight[from][leftPath];
			}
		}
		min = Double.MAX_VALUE;
		for (int i = 0; i < N; ++i) {
			if (((1 << i) & (~leftPath)) > 0) {
				continue;
			}
			double v = calcShortestPath(i, leftPath & (~(1 << i))) + citiesArr[from][i];
			min = Double.min(min, v);
			citiesArrWeight[from][leftPath] = min;
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

	private static int countBit(int bit) {
		int count = 0;
		for (int i = 0;; ++i) {
			int temp = 1 << i;
			if (temp > bit) {
				break;
			}
			if ((bit & temp) != 0) {
				++count;
			}
		}
		return count;
	}

}
