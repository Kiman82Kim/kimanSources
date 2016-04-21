package algorithm;

import java.util.ArrayList;
import java.util.Scanner;


public class EssentialNode {

	static int PAN_SIZE = 10;
	static int [][] pan = new int [PAN_SIZE][PAN_SIZE];
	static ArrayList<Integer> nodeList = new ArrayList<Integer>();
	static ArrayList<Integer> tempNodeList = new ArrayList<Integer>();
	static int essentialNodeCnt = 0;
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int nodeCnt = sc.nextInt();
		for(int i=0; i<nodeCnt; ++i){
			int from = sc.nextInt();
			int to = sc.nextInt();
			insertNodeList(from);
			insertNodeList(to);
			pan[from][to] = 1;
		}
		for(int i=0; i<nodeList.size(); ++i){
			if(isEssentialNode(nodeList.get(i))){
				++essentialNodeCnt;
			}
		}
		System.out.println(essentialNodeCnt);
	}
	
	static public void insertNodeList(int node){
		for(int i=0; i<nodeList.size(); ++i){
			if(nodeList.get(i) == node){
				return;
			}
		}
		nodeList.add(node);
	}
	
	static public boolean isEssentialNode(int node){
		int [][] tempPan = new int [PAN_SIZE][PAN_SIZE];
		copyPan(tempPan);
		tempNodeList = (ArrayList<Integer>) nodeList.clone();
		for(int j=0;j<PAN_SIZE; ++j){
			if(tempPan[node][j] == 1 || tempPan[j][node] == 1){
				for(int i=0; i<tempNodeList.size(); ++i){
					if(tempNodeList.get(i)==j){
						tempNodeList.remove(i);
						break;
					}
					if(tempNodeList.get(i)==node){
						tempNodeList.remove(i);
						--i;
					}
				}
			}
			tempPan[node][j] = 0;
			tempPan[j][node] = 0;
		}
		
		for(int j=0; j<tempNodeList.size(); ++j){
			if(tempNodeList.get(j) != node){
				int firstNode = findFirstNode(tempPan, tempNodeList.get(j));
				if(checkNode(tempPan, firstNode)){
					break;
				}
			}
		}
		return checkMoreNode(tempPan);
	}
	static public boolean checkNode(int [][] tempPan, int node){
		boolean flag = false;
		for(int i=0; i<PAN_SIZE; ++i){
			if(tempPan[node][i] == 1){
				tempPan[node][i] = 0;
				checkNode(tempPan, i);
				flag = true;
			}
		}
		return flag;
	}
	static public boolean checkMoreNode(int [][] tempPan){
		for(int j=0; j<nodeList.size(); ++j){
			for(int i=0; i<PAN_SIZE; ++i){
				if(tempPan[nodeList.get(j)][i] == 1){
					return true;
				}
			}
		}
		
		return false;
	}
	static public void copyPan(int [][] tempPan){
		for(int i=0; i<PAN_SIZE; ++i){
			for(int j=0; j<PAN_SIZE; ++j){
				tempPan[i][j] = pan[i][j];
			}
		}
	}
	static public int findFirstNode(int [][] tempPan, int node){
		int from = 0;
		for(int i=0; i<PAN_SIZE; ++i){
			if(tempPan[i][node] == 1){
				from = findFirstNode(tempPan, i);
				return from;
			}
		}
		return node;
	}
}
