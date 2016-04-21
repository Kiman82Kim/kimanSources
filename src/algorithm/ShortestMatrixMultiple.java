package algorithm;

import java.util.Scanner;

/*

1
6
5 2
2 3 
3 4
4 6 
6 7
7 8

348
 * 
 */
public class ShortestMatrixMultiple {

	static int N;
	/*
	 * M[i][j] 는 i 부터 j 메트릭스까지 곱했을 때 최소 횟수.
	 */
	static int[][] M;

	static class InMatrix {
		public int row;
		public int col;

		public InMatrix(int row, int col) {
			this.row = row;
			this.col = col;
		}

		public InMatrix() {

		}
	}

	static InMatrix[] matrix;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		while (TC-- > 0) {
			N = sc.nextInt();
			M = new int[N + 1][N + 1];
			matrix = new InMatrix[N + 1];
			int index = 0;
			for (InMatrix m : matrix) {
				m = new InMatrix();
				matrix[index] = m;
				++index;
			}
			for (int i = 1; i <= N; ++i) {
				matrix[i].row = sc.nextInt();
				matrix[i].col = sc.nextInt();
			}
			findShortestMultiple();
			System.out.println(M[1][N]);
		}
	}

	private static void findShortestMultiple() {
		for (int diag = 1; diag <= N - 1; ++diag) {
			for (int from = 1; from <= N - diag; ++from) {
				int to = from + diag;
				int min = Integer.MAX_VALUE;
				for (int k = from; k < to; ++k) {
					int temp = M[from][k] + M[k + 1][to] + matrix[from].row * matrix[k].col * matrix[to].col;
					min = Integer.min(temp, min);
				}
				M[from][to] = min;
			}
		}
	}

}
