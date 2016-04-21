package algorithm;

import java.util.Scanner;


public class Dynamic {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Insert coin count : ");
		int coinCnt = sc.nextInt();
		if(coinCnt==0){
			return;
		}
		System.out.println("insert coins : ");
		int [] coins = new int [coinCnt];
		for(int i=0; i<coinCnt; ++i){
			coins[i] = sc.nextInt();
		}
		
		System.out.println("insert price for change : ");
		int price = sc.nextInt();
		int [] changeCnt = new int[price+1];
		int [] changeCoins = new int[price+1];
		
		int minValue = changeCalc(changeCnt, changeCoins, coins, price);
		System.out.println(minValue);
		//print(changeCoins);
		for(int i=price; i>0; i=i-coins[changeCoins[i]]){
			System.out.print(coins[changeCoins[i]] + " ");
		}
		System.out.println();
	}
	
	public static int changeCalc(int [] changeCnt, int [] changeCoins, int [] coins, int price){
		for(int k=0; k<coins.length; ++k){
			for(int i=coins[k]; i<=price; ++i){
				if(k==0){
					if(i%coins[k]!=0){
						changeCnt[i] = 9999;
					}else{
						changeCnt[i] = i/coins[k];
						changeCoins[i] = k;
					}
				}else {
					if(i==coins[k]){
						changeCnt[i]=1;
						changeCoins[i] = k;
					}else{
						if(changeCnt[i]>changeCnt[i-coins[k]]+1){
							changeCnt[i] = changeCnt[i-coins[k]]+1;
							changeCoins[i] = k;
						}
					}
				}
			}
			//print(changeCoins);
			//print(changeCnt);
		}
		return changeCnt[changeCnt.length-1];
	}
	
	public static void print(int [] array){
		for(int i=0; i<array.length; ++i){
			System.out.print(array[i] + " ");	
		}
		System.out.println();
	}
	public static int min(int a, int b){
		return a<b?a:b;
	}
}
