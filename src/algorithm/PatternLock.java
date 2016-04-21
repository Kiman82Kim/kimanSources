package algorithm;

import java.util.ArrayList;
import java.util.Scanner;

/*
 *
2
4
1 2 6 9
5
1 2 5 8 9


2
4
1 2 3 4
5
9 8 7 6 5


 */
public class PatternLock {

	static int caseCnt;
	static int dotCnt;
	static int dotMaxNum;
	static int patternCnt;
	static ArrayList<Integer[]> dotArrList;
	static int[] dotArrOrigin;
	static int N = 3;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		caseCnt = sc.nextInt();

		for (int cases = 0; cases < caseCnt; ++cases) {
			dotCnt = sc.nextInt();
			dotMaxNum = 0;
			patternCnt = 0;
			dotArrList = new ArrayList<Integer[]>();
			dotArrOrigin = new int[dotCnt];
			for (int dotC = 0; dotC < dotCnt; ++dotC) {
				dotArrOrigin[dotC] = sc.nextInt();
				if (dotArrOrigin[dotC] > dotMaxNum) {
					dotMaxNum = dotArrOrigin[dotC];
				}
			}
			calcPattern(dotArrOrigin);
		}
	}

	public static void calcPattern(int[] dotArrOrigin) {
		permutation(dotArrOrigin, 0);
		System.out.println(patternCnt);
	}

	public static void permutation(int[] dotArrOrigin, int pos) {
		if (pos == dotCnt) {
			for (int i = 0; i < dotCnt - 1; ++i) {
				if (!isAvailablePos(i, i + 1)) {
					return;
				}
			}
			// print(dotArrOrigin, "");
			++patternCnt;
			return;
		}
		for (int i = pos; i < dotCnt; ++i) {
			swap(dotArrOrigin, i, pos);
			permutation(dotArrOrigin, pos + 1);
			swap(dotArrOrigin, pos, i);
		}
	}

	public static void swap(int[] arr, int left, int right) {
		int temp = arr[left];
		arr[left] = arr[right];
		arr[right] = temp;
	}

	public static void print(int[] arr, String str) {
		System.out.println(str);
		for (int i = 0; i < arr.length; ++i) {
			System.out.print(arr[i] + " ");
		}
		// System.out.println();
	}

	private static int gcd(int a, int b) {
		while (b != 0) {
			int temp = a % b;
			a = b;
			b = temp;
		}
		return a;
	}

	private static boolean isAvailablePos(int positionA, int positionB) {
		int x1 = dotArrOrigin[positionA] % N;
		if (x1 == 0) {
			x1 = N;
		}
		int y1 = ((dotArrOrigin[positionA] - 1) / N) + 1;
		int x2 = dotArrOrigin[positionB] % N;
		if (x2 == 0) {
			x2 = N;
		}
		int y2 = ((dotArrOrigin[positionB] - 1) / N) + 1;

		if (((Math.abs(x2 - x1) >= 2) && (Math.abs(x2 - x1) == 0) || (Math.abs(y2 - y1) >= 2) && (Math.abs(y2 - y1) == 0))) {
			return false;
		} else if (x2 - x1 == 0) {
			return true;
		} else if ((Math.abs((y2 - y1) / (x2 - x1)) == 1)) {
			if (Math.abs(x2 - x1) > 1) {
				return false;
			}
		}
		return true;
	}
}
