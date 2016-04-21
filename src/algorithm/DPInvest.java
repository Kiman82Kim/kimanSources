package algorithm;

import java.util.Scanner;

/*

4 2
1 5 3
2 7 4
3 7 9
4 15 9

4 2
1 5 3
2 9 4
3 7 13
4 20 9

4 2
1 5 3
2 7 4
3 7 9
4 11 9


*/

public class DPInvest {

	static int[][] returnMoney = new int[20][301];
	static int[][] returnMoneySave = new int[20][301];
	static int[][] investMoney = new int[20][301];
	static int compCnt;
	static int moneyTotal;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		moneyTotal = sc.nextInt();
		compCnt = sc.nextInt();
		for (int money = 1; money <= moneyTotal; ++money) {
			int inputMoney = sc.nextInt();
			for (int comp = 1; comp <= compCnt; ++comp) {
				returnMoney[comp][inputMoney] = sc.nextInt();
			}
		}

		calcMaxInvest();
		output();
	}

	public static void calcMaxInvest() {
		for (int comp = 1; comp <= compCnt; ++comp) {
			for (int money = 0; money <= moneyTotal; ++money) {
				for (int moneyMod = 0; moneyMod <= money; ++moneyMod) {
					if (returnMoneySave[comp][money] < returnMoneySave[comp - 1][money - moneyMod] + returnMoney[comp][moneyMod]) {
						returnMoneySave[comp][money] = returnMoneySave[comp - 1][money - moneyMod] + returnMoney[comp][moneyMod];
						investMoney[comp][money] = moneyMod;
					}
				}

			}
		}
		print(investMoney, "InvestMoney");
		print(returnMoneySave, "returnMoneySave");
	}

	public static void print(int[][] arr, String str) {
		System.out.println(str + " : ");
		for (int i = 1; i <= compCnt; ++i) {
			System.out.println("company " + i + " : ");
			for (int j = 1; j <= moneyTotal; ++j) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void output() {
		int[] g = new int[21];
		int i, j, k;

		j = moneyTotal;
		for (i = compCnt; i >= 1; i--) {
			g[i] = investMoney[i][j];
			j -= g[i];
		}

		System.out.println("Output : ");
		for (i = 1; i <= compCnt; i++) {
			System.out.println(g[i] + " ");
		}
		System.out.println(returnMoneySave[compCnt][moneyTotal]);
	}
}
