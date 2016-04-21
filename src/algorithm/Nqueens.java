package algorithm;

import java.util.Scanner;

/*
입력
3
1
2
8
출력
1
0
92

0 1 0 0
0 0 0 1
1 0 0 0
0 0 1 0

0 0 1 0
1 0 0 0
0 0 0 1
0 1 0 0

1 0 0 0 0   1 0 0 0 0     0 1 0 0 0     0 1 0 0 0      0 0 1 0 0      0 0 1 0 0
0 0 1 0 0   0 0 0 1 0     0 0 0 1 0     0 0 0 0 1      1 0 0 0 0      0 0 0 0 1
0 0 0 0 1   0 1 0 0 0     1 0 0 0 0     0 0 1 0 0      0 0 0 1 0      0 1 0 0 0
0 1 0 0 0   0 0 0 0 1     0 0 1 0 0     1 0 0 0 0      0 1 0 0 0      0 0 0 1 0
0 0 0 1 0   0 0 1 0 0     0 0 0 0 1     0 0 0 1 0      0 0 0 0 1      1 0 0 0 0

 */
public class Nqueens {

	static int n;
	static int calcN;
	static int caseCnt;
	static boolean[][] board;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int gameCnt = sc.nextInt();
		for (int i = 0; i < gameCnt; ++i) {
			n = sc.nextInt();
			calcN = 0;
			if (n >= 4) {
				calcN = (n + 1) / 2;
			}
			board = new boolean[n][n];
			initBoard();
			caseCnt = 0;
			calcNqueen(0);
			System.out.println(caseCnt);
		}
	}

	private static void initBoard() {
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				board[i][j] = false;
			}
		}
	}

	public static void calcNqueen(int y) {
		if (y == n) {
			++caseCnt;
			return;
		}
		for (int x = 0; x < n; ++x) {
			if (n % 2 == 0) {
				if (y == 0 && x == calcN && calcN != 0) {
					caseCnt *= 2;
					return;
				}
			} else {
				if (y == 0 && x == calcN - 1 && calcN != 0) {
					caseCnt *= 2;
				}
				if (y == 0 && x > calcN - 1 && calcN != 0) {
					return;
				}
			}

			if (possiblePosition(x, y)) {
				board[y][x] = true;
				calcNqueen(y + 1);
				board[y][x] = false;
			}
		}
	}

	public static boolean possiblePosition(int x, int y) {
		// System.out.println("y : " + y + " x : " + x);
		// 가로.
		for (int row = 0; row < n; ++row) {
			if (row != x) {
				if (board[y][row] == true) {
					return false;
				}
			}
		}
		// 세로.
		for (int column = 0; column < n; ++column) {
			if (column != y) {
				if (board[column][x] == true) {
					return false;
				}
			}
		}
		// 대각선.
		for (int dig = 1; dig < n; ++dig) {
			if (y + dig < n && x + dig < n && board[y + dig][x + dig] == true) {
				return false;
			}
			if (y - dig >= 0 && x - dig >= 0 && board[y - dig][x - dig] == true) {
				return false;
			}
			if (y + dig < n && x - dig >= 0 && board[y + dig][x - dig] == true) {
				return false;
			}
			if (y - dig >= 0 && x + dig < n && board[y - dig][x + dig] == true) {
				return false;
			}
		}
		return true;
	}
}
