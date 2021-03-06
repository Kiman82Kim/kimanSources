package algorithm;

import java.util.Scanner;
import java.util.Stack;

/*
3
()()
({[}])
({}[(){}])
[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]
 */
public class Brackets2 {

	static String str;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int gameCnt = sc.nextInt();
		sc.nextLine();
		for (int i = 0; i < gameCnt; ++i) {
			str = sc.nextLine();
			calcBrackets();
		}
	}

	public static void calcBrackets() {
		Stack<Character> stack = new Stack<Character>();

		for (int i = 0; i < str.length(); ++i) {
			char bracket = str.charAt(i);
			char bracketTemp;
			if (bracket == '(' || bracket == '{' || bracket == '[') {
				stack.push(bracket);
			} else {
				if (stack.isEmpty()) {
					System.out.println("NO");
					return;
				}
				if (bracket == ')') {
					bracketTemp = stack.pop();
					if (bracketTemp != '(') {
						System.out.println("NO");
						return;
					}
				} else if (bracket == '}') {
					bracketTemp = stack.pop();
					if (bracketTemp != '{') {
						System.out.println("NO");
						return;
					}
				} else if (bracket == ']') {
					bracketTemp = stack.pop();
					if (bracketTemp != '[') {
						System.out.println("NO");
						return;
					}
				}
			}

		}
		if (!stack.isEmpty()) {
			System.out.println("NO");
			return;
		}
		System.out.println("YES");
	}
}
