package algorithm;

import java.util.Scanner;

/*
예제 입력
4
110 4
10 50 100 500
850 4
10 50 100 500
3600 5
100 300 500 900 2000
1278 8
1 2 4 8 16 32 64 128

예제 출력
4
110
130
873794561

% 1000000007 

11
4
1 5 10 50


 */
public class Coins {

	static int exchange;
	static int coinCnt;
	static int noc;
	static int[] coinValueArr;
	static int[] coinArr;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int gameCnt = sc.nextInt();
		for (int i = 0; i < gameCnt; ++i) {
			exchange = sc.nextInt();
			coinCnt = sc.nextInt();
			noc = 0;
			coinValueArr = new int[coinCnt];
			coinArr = new int[exchange + 1];

			for (int j = 0; j < coinCnt; ++j) {
				coinValueArr[j] = sc.nextInt();
			}
			calcNumberOfCase2(exchange);
			System.out.println(coinArr[exchange]);
		}
	}

	public static void calcNumberOfCase(int coinIndex, int leftMoney) {
		if (leftMoney == 0) {
			++noc;
			return;
		}
		for (int i = coinIndex; i >= 0; --i) {
			if (leftMoney >= coinValueArr[i]) {
				leftMoney -= coinValueArr[i];
			} else {
				continue;
			}

			calcNumberOfCase(i, leftMoney);
			leftMoney += coinValueArr[i];
		}
	}

	public static void calcNumberOfCase2(int leftMoney) {
		coinArr[0] = 1;
		for (int i = 0; i < coinCnt; ++i) {
			for (int coinVal = 1; coinVal <= exchange; ++coinVal) {
				if (coinVal - coinValueArr[i] >= 0) {
					coinArr[coinVal] = coinArr[coinVal] + coinArr[coinVal - coinValueArr[i]];
					if (coinArr[coinVal] > 1000000007) {
						coinArr[coinVal] = coinArr[coinVal] % 1000000007;
					}
				}
			}
		}
	}
}
