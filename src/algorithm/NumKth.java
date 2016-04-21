package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/*
 * 위대한 정복자 나폴레옹은 '내 사전에 불가능이란 없다'고 말했다.그러자 한 프랑스 병사가 나폴레옹에게 질문했다: '사전에 숫자도 들어있나요?' 
그래서 나폴레옹은 사전에 숫자들도 집어넣기로 했다. 
다만 사전에 숫자들을 쓰려고 하다 보니 “1 다음은 10, 10 다음은 100, 100 다음은 1000, 1000 다음은 10000 …”
등으로 무한히 길어지기 때문에 나폴레옹은 자신이 좋아하는 두 음이 아닌 정수 a,b사이의 모든 정수들을 집어넣는 것으로 타협을 보았다. 
그럼에도 불구하고 그 사전의 크기는 매우 방대하였고 그 사전은 단 한 권밖에 인쇄돼지 못했다. 
(소문에 따르면 나폴레옹의 몰락은 이 사전을 편찬하기 위해 들어간 막대한 예산의 탓도 있다고 한다.)
당신은 세인트 헬레나 섬에서 우연히 이 사전을 발견했다. 그러자 나폴레옹의 망령이 나타나서 이렇게 말했다: '내 사전에 들어있는 k번째 숫자를 맞춰야만 이 사전을 들고 섬에서 벗어날 수 있을 것이다.’
그리하여 당신은 생존을 위해서 본 문제를 해결해야만 한다. 
단, 당신은 훌륭한 컴퓨터 공학도이고 나폴레옹도 그 사실을 알기 때문에 질문의 k는 0-indexed이다. 자세한 것은 아래 예제를 참조하라.
입력
첫 줄에는 테스트 케이스의 수 T가 주어진다.
각 테스트 케이스는 한 줄로 구성되어 있으며, 매 테스트 케이스마다 3개의 숫자 a,b,k(0 ≤ a ≤ b < 2^63, 0 ≤ k ≤ b – a)가 공백을 사이에 두고 입력으로 들어온다.
출력
각 입력마다 정답을 한 줄에 하나씩 출력한다.
예제 입력
3
1 10 0
1 10 1
1 10 2

7
0 0 0
0 1 1
0 262 262
0 262 0
0 262 1
262 262 0
50 51 1
예제 출력
1
10 
2

9.223372036854776E18

9423372036854778123

9223372036854775807
 */
public class NumKth {

	static long a;
	static long b;
	static long k;
	static int decimalPosA;
	static int decimalPosB;
	static int decimalPosK;
	static int biggestDecimalPosNumA;
	static int biggestDecimalPosNumB;
	static int biggestDecimalPosNumK;
	static ArrayList<String> numStrList;
	static ArrayList<Long> decimalPowList;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// a = 9223372036854775806L;
		// b = 9223372036854775807L;
		// if (a < b) {
		// System.out.println("b");
		// System.out.println(b);
		// System.out.println(a);
		// } else if (a > b) {
		// System.out.println("a");
		// System.out.println(a);
		// System.out.println(b);
		// } else {
		// System.out.println("..");
		// }
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		for (int c = 0; c < TC; ++c) {
			a = sc.nextLong();
			b = sc.nextLong();
			k = sc.nextLong();
			decimalPowList = new ArrayList<Long>();
			b = (long) (Math.pow(10, 63) - 1);
			initABK();
			setDecimalPowNum();
			// initNumList();
			long num = 0;
			// num = findKthNumBySorting();
			// num = findKthNum();
			// System.out.println(num);
			num = findKthNum2();
			System.out.println(num);
		}
	}

	private static void setDecimalPowNum() {
		long minA = (long) (biggestDecimalPosNumA * Math.pow(10, decimalPosA - 1));
		long minB = (long) (biggestDecimalPosNumB * Math.pow(10, decimalPosB - 1));
		if (a == 0) {
			decimalPowList.add(a);
		}
		for (int n = 1; n <= 9; ++n) {
			for (int z = 0; z <= 63; ++z) {
				long powNum = (long) (n * Math.pow(10, z));
				if (minA <= powNum && powNum <= b) {
					if (minA == powNum) {
						decimalPowList.add(a);
					} else if (minB == powNum) {
						decimalPowList.add(b);
					} else {
						decimalPowList.add(powNum);
					}
				}
			}
		}
	}

	private static long findKthNum2() {
		long num = 0;
		long index = 0;
		long leftIndex = k;
		long addIndex = 0;
		long beforeLeftIndex = 0;
		for (long decimalPowNum : decimalPowList) {
			long tempDecimalPow = getDecimalPow(decimalPowNum);
			long tempDecimalPowNum = decimalPowNum / tempDecimalPow;
			if (decimalPowNum == 0) {
				addIndex = 1;
			} else if (decimalPowNum == a) {
				addIndex = ((tempDecimalPowNum + 1) * tempDecimalPow - decimalPowNum);
			} else if (decimalPowNum == b) {
				addIndex = (decimalPowNum - (tempDecimalPowNum) * tempDecimalPow) + 1;
			} else {
				addIndex = ((tempDecimalPowNum + 1) * tempDecimalPow - decimalPowNum);
			}
			index += addIndex;
			beforeLeftIndex = leftIndex;
			leftIndex = k - index;

			if (leftIndex < 0) {
				if (decimalPowNum == a) {
					num = decimalPowNum + (k - (index - addIndex));
				} else if (decimalPowNum == b) {
					num = decimalPowNum - ((index - 1) - k);
				} else {
					num = decimalPowNum + (k - (index - addIndex));
				}
				break;
			}
		}
		return num;
	}

	private static long getDecimalPow(long num) {
		String numStr = String.valueOf(num);
		return (long) (Math.pow(10, numStr.length() - 1));
	}

	private static long findKthNum() {
		// a와 b 자릿수가 변하지 않는다면 k-a+1
		// a와 b 자릿수가 변한다면
		long num = 0;
		if (decimalPosA == decimalPosB && biggestDecimalPosNumA == biggestDecimalPosNumB) {
			num = a + k;
			return num;
		}
		for (int i = 1; i <= 9; ++i) {
			if (i < biggestDecimalPosNumA) {
				num += decimalPosB - decimalPosA;
			} else if (i >= biggestDecimalPosNumA) {
				num += decimalPosB - decimalPosA + 1;
			}
			if (i > biggestDecimalPosNumB) {
				num += -1;
				num += Math.pow(10, decimalPosB - 1) - 1;
			} else if (i < biggestDecimalPosNumB) {
				num += Math.pow(10, decimalPosB) - 1;
			} else {
				num += b - Math.pow(10, decimalPosB);
			}
		}
		return num;
	}

	private static long findKthNumBySorting() {
		long num = 0;
		num = Long.parseLong(numStrList.get((int) k));
		return num;
	}

	private static void initABK() {
		boolean isFinishA = false;
		boolean isFinishB = false;
		boolean isFinishK = false;
		for (int i = 1; i <= 63; ++i) {
			if (a / (long) Math.pow(10, i) == 0 && !isFinishA) {
				decimalPosA = i;
				biggestDecimalPosNumA = (int) (a / (long) Math.pow(10, i - 1));
				isFinishA = true;
			}
			if (b / (long) Math.pow(10, i) == 0 && !isFinishB) {
				decimalPosB = i;
				biggestDecimalPosNumB = (int) (b / (long) Math.pow(10, i - 1));
				isFinishB = true;
			}
			if (k / (long) Math.pow(10, i) == 0 && !isFinishK) {
				decimalPosK = i;
				biggestDecimalPosNumK = (int) (k / (long) Math.pow(10, i - 1));
				isFinishK = true;
			}
		}
	}

	private static void initNumList() {
		numStrList = new ArrayList<String>();
		for (long i = a; i <= b; ++i) {
			numStrList.add(String.valueOf(i));
		}
		Collections.sort(numStrList);
	}

	public static class Compare implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {
			// TODO Auto-generated method stub
			int o1Lenghth = o1.length();
			int o2Lenghth = o2.length();
			int maxLength = 0;
			int minLength = 0;
			int minNum = 0;
			if (o1Lenghth > o2Lenghth) {
				maxLength = o1Lenghth;
				minNum = 2;
				minLength = o2Lenghth;
			} else {
				maxLength = o2Lenghth;
				minNum = 1;
				minLength = o1Lenghth;
			}
			for (int i = 0; i < maxLength; ++i) {
				if (i == minLength) {
					if (minNum == 1) {
						return 1;
					} else {
						return -1;
					}
				}
				if (o1.charAt(i) > o2.charAt(i)) {
					return -1;
				} else if (o1.charAt(i) < o2.charAt(i)) {
					return 1;
				}
			}

			return 0;
		}

	}
}
