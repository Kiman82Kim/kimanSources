package algorithm;

import java.util.Scanner;

/*
 *
 *모든 문자에 색을 넣을 수 있는 단어수의 최소값.
1
5
catch apple lion letter length

1
8
speaking nuclear summit washington obama said statements republican 
모음 제외
aeiou
단어 개수 <=200
 */

public class WordColor {

	static int N;
	static String[] wordArr;
	static boolean[][] alphabetArr;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		// System.out.println(Character.compare('b', 'a'));
		int TC = sc.nextInt();
		for (int i = 0; i < TC; ++i) {
			N = sc.nextInt();
			wordArr = new String[N];
			alphabetArr = new boolean[N][27];
			for (int j = 0; j < N; ++j) {
				wordArr[j] = sc.next();
				insertAlphabetArr(wordArr[j], j);
			}
			// printStr(wordArr);
			int min = calcCountColorWord(0);
			System.out.println(min);
		}
	}

	static private int calcCountColorWord(int recurCnt) {
		int nextWord = isAllColored();
		if (nextWord == -1) {
			return recurCnt;
		}
		int min = 28;
		for (int i = 1; i < 27; ++i) {
			if (alphabetArr[nextWord][i]) {
				int[] checkWordArr = new int[27];
				for (int k = 0; k < 27; ++k) {
					checkWordArr[k] = -1;
				}
				System.out.println(i);
				insertWordCheck(checkWordArr, i);
				int coloredAlphabetCnt = calcCountColorWord(recurCnt + 1);
				System.out.println("coloredAlphabetCnt : " + coloredAlphabetCnt);
				System.out.println("-" + i);
				insertWordUnCheck(checkWordArr);
				min = Integer.min(min, coloredAlphabetCnt);
			}
		}
		return min;
	}

	static private void insertWordCheck(int[] checkWordArr, int alphabetIndex) {
		int index = 0;
		for (int i = 0; i < N; ++i) {
			if (alphabetArr[i][alphabetIndex] && !alphabetArr[i][0]) {
				checkWordArr[index++] = i;
				alphabetArr[i][0] = true;
			}
		}
	}

	static private void insertWordUnCheck(int[] checkWordArr) {
		for (int i = 0; i < N; ++i) {
			if (checkWordArr[i] != -1) {
				alphabetArr[checkWordArr[i]][0] = false;
			}
		}
	}

	static private void insertAlphabetArr(String word, int wordNum) {
		for (int i = word.length() - 1; i >= 0; --i) {
			if (word.charAt(i) == 'a' || word.charAt(i) == 'e' || word.charAt(i) == 'i' || word.charAt(i) == 'o' || word.charAt(i) == 'u') {
				continue;
			}
			alphabetArr[wordNum][Character.compare(word.charAt(i), 'a') + 1] = true;
		}
	}

	static private int isAllColored() {
		for (int i = 0; i < N; ++i) {
			if (!alphabetArr[i][0]) {
				// 색칠 안된 word 리턴.
				return i;
			}
		}
		// all colored
		return -1;
	}

	static private void printStr(String[] wordArr) {
		for (int i = 0; i < wordArr.length; ++i) {
			System.out.println(wordArr[i]);
		}
	}
}
