package algorithm;

import java.util.Scanner;

/*
 *너비가 같은 N개의 나무 판자를 붙여 세운 울타리가 있습니다. 시간이 지남에 따라 판자들이 부러지거나 망가져 높이가 다 달라진 관계로 울타리를 통째로 교체하기로 했습니다.
 *이 때 버리는 울타리의 일부를 직사각형으로 잘라내 재활용하고 싶습니다. 그림 (b)는 (a)의 울타리에서 잘라낼 수 있는 많은 직사각형 중 가장 넓은 직사각형을 보여줍니다.
 *울타리를 구성하는 각 판자의 높이가 주어질 때, 잘라낼 수 있는 직사각형의 최대 크기를 계산하는 프로그램을 작성하세요. 단 (c)처럼 직사각형을 비스듬히 잘라낼 수는 없습니다.
판자의 너비는 모두 1이라고 가정합니다.
입력
첫 줄에 테스트 케이스의 개수 C (C≤50)가 주어집니다.
각 테스트 케이스의 첫 줄에는 판자의 수 N (1≤N≤20000)이 주어집니다.
그 다음 줄에는 N개의 정수로 왼쪽부터 각 판자의 높이가 순서대로 주어집니다.
높이는 모두 10,000 이하의 음이 아닌 정수입니다.
출력
각 테스트 케이스당 정수 하나를 한 줄에 출력합니다. 이 정수는 주어진 울타리에서 잘라낼 수 있는 최대 직사각형의 크기를 나타내야 합니다.
예제 입력
3
7
7 1 5 9 6 7 3
7
1 4 4 4 4 1 1
4
1 8 2 2

1
7
7 1 5 9 6 7 3

예제 출력
20
16
8
 */
public class FenceD_C {

	static int[] fences;
	static int N;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		for (int i = 0; i < TC; ++i) {
			N = sc.nextInt();
			fences = new int[N];
			for (int j = 0; j < N; ++j) {
				fences[j] = sc.nextInt();
			}
			int max = getArea2(0, N - 1);
			System.out.println(max);
		}
	}

	private static int findBigestRectD_C(int start, int end) {
		if (start == end) {
			return fences[start];
		}
		int mid = (start + end) / 2;
		int max = 0;
		int left = mid;
		int right = mid;
		int height = fences[mid];
		int centerBigRect = fences[mid];
		while (left > start || right < end) {
			if (left > start && (right >= end || fences[left - 1] > fences[right + 1])) {
				--left;
				if (fences[left] < height) {
					height = fences[left];
				}
			} else {
				++right;
				if (fences[right] < height) {
					height = fences[right];
				}
			}
			centerBigRect = Integer.max(centerBigRect, height * (right - left + 1));
		}
		int leftBigRect = findBigestRectD_C(start, mid);
		max = Integer.max(centerBigRect, leftBigRect);
		int rightBigRect = findBigestRectD_C(mid + 1, end);
		max = Integer.max(max, rightBigRect);

		return max;
	}

	static int getArea2(int start, int end) {
		if (start == end) {
			return fences[start];
		}
		int mid = (start + end) / 2;
		int height = Math.min(fences[mid], fences[mid + 1]);
		int area = height * 2;
		int left = mid;
		int right = mid + 1;
		while (start < left || right < end) {
			if (start < left && (right == end || fences[left - 1] > fences[right + 1])) {
				left--;
				height = Math.min(height, fences[left]);
			} else {
				right++;
				height = Math.min(height, fences[right]);
			}
			area = Math.max(area, height * (right - left + 1));
		}
		return Math.max(Math.max(getArea2(start, mid), area), getArea2(mid + 1, end));
	}
}
