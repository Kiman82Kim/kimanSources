package algorithm;

import java.util.Scanner;

public class Calculator {

	/**
	 * @param args
	 */
	public static int numberCnt = 0;
	public static int operatorCnt = 0;
	public static int [] numbers = new int[100];
	public static char [] operators = new char[100];
	public static int a = 0;
	public static int b = 0;
	public static char operator = '@';
	static int bracketCnt = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		
		
		for(int k=0; k<operators.length; ++k){
			operators[k] = '@';
		}
		while(true){
			pushNum(numbers, sc.nextInt());
			pushOp(operators, sc.next().charAt(0));
//			if(operators[j]=='='){
//				break;
//			}
			checkOperator(operators);
		}
		//printNum(numbers);
		//printOp(operators);
	}
	
	public static void checkOperator(char [] operators){
		
		if(operators[operatorCnt-2] == '('){
			++bracketCnt;
		}else if(operators[operatorCnt-2] == ')'){
			--bracketCnt;
		}else{
			if(operatorCnt>2){
				if(getOperatorScore(operators[operatorCnt-1]) <= getOperatorScore(operators[operatorCnt-2])){
					b = popNum(numbers);
					a = popNum(numbers);
					operator = PopOp(operators);
					calc();
				}
			}
		}
		
	}
	
	public static void printNum(int [] numbers){
		for(int i=0; i<numbers.length; ++i){
			System.out.print(numbers[i]);
		}
		System.out.println();
	}
	public static void printOp(char [] operators){
		for(int i=0; i<operators.length; ++i){
			System.out.print(operators[i]);
		}
		System.out.println();
	}
	
	public static int popNum(int [] numbers){
		int value = numbers[numberCnt - 1];
		numbers[numberCnt - 1] = 0;
		--numberCnt;
		return value;
	}
	public static char PopOp(char [] operators){
		char value = operators[operatorCnt - 1];
		operators[operatorCnt - 1] = '@';
		--operatorCnt;
		return value;
	}
	public static void pushNum(int [] numbers, int number){
		numbers[numberCnt] = number;
		++numberCnt;
	}
	public static void pushOp(char [] operators, char operator){
		operators[operatorCnt] = operator;
		++operatorCnt;
	}
	public static int getOperatorScore(char newOperator){
		switch(newOperator){
		case '+':
			return 1;
		case '-':
			return 1;
		case '*':
			return 2;
		case '/':
			return 2;
		case '(':
			return 3;
		case ')':
			return 4;
		case '=':
			return 5;
		default :
			return 0;
		}
	}
	
	public static int calc(){
		switch(operator){
		case '+':
			return a+b;
		case '-':
			return a-b;
		case '*':
			return a*b;
		case '/':
			return a/b;
		case '(':
			return a*b;
		default :
			return 0;
		}
	}
}
