package algorithm;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * 어느 더운 여름날, '모두의 오리'라는 오리 농장에서 복날을 맞이해 농장 주인이 1번 오리부터 차례대로 트럭에 집어넣고 있었다. 그러나 용감한 오리 Pekaz가 농장으로부터 탈출하고 말았다 (후일 이 오리는 0번째 오리라고 알려졌다).
Pekaz가 농장으로부터 완전히 벗어나기 위해서는 커다란 강을 건너야 했다. 불행하게도 최근 장마로 인해 물살이 너무 거세져 제대로 헤엄을 칠 수 없기에, 그 대신 강 위에 설치된 돌다리를 이용해 건너기로 했다.
여기서 강은 2차원 평면으로, 돌다리를 구성하는 각 돌은 그 위의 점으로 보기로 하자. 우리의 Pekaz는 돌다리가 아닌 강 위의 특정 지점에서 출발해 목표 지점까지 이동하려고 한다.
Pekaz는 언제건 자신이 위치한 곳에서 직선거리가 J 이하인 곳으로 뛰어서 이동할 수 있다. 이 J를 최대 점프력이라 하자. 뛰어오르는 횟수에는 아무런 제약이 없지만, 만약 돌다리가 아닌 곳으로 뛸 경우 거센 물살에 휩쓸려 내려가 버리고 말 것이다.
과연 우리의 Pekaz는 탈출에 성공할 수 있을까? 아니면 안정적인 맛의 오리고기가 되고 말 것인가? 돌다리의 구성과 점프력이 주어질 때, Pekaz의 탈출 계획이 성공할 수 있는지 알아내는 프로그램을 작성하라.
입력
입력은 T 개의 테스트 케이스로 이뤄지며, 입력의 첫 줄에는 T가 주어진다.
각 테스트 케이스의 첫 번째 줄에는 Pekaz의 최대 점프력인 정수 J (1 <= J <= 1000)가 주어진다.
두 번째 줄과 세 번째 줄에는 각각 Pekaz의 시작 지점의 좌표와 도착 지점의 좌표가 주어지고,
네 번째 줄에는 돌다리를 구성하는 돌의 개수 N (0 < N <= 100)이 주어진다. 그다음 줄부터 N개의 줄에 걸쳐 각 돌의 좌표가 주어진다.
모든 좌표는 공백으로 구분된 두 정수 x, y (-1000 <= x, y <= 1000) 꼴로 주어지며 시작 지점, 도착 지점 및 각 돌의 좌표들이 동일한 경우는 주어지지 않는다.
출력
각 테스트 케이스마다 한 줄에 하나씩 탈출이 가능하면 "YES" , 불가능하면 "NO" 를 출력한다.
예제 입력
2
2
1 1
4 1
3
4 2
1 2
3 2
3
1 1
10 10
5
6 7
4 1
9 7
6 4
4 4

1
3
1 1
6 4
5
6 7
4 1
10 10
6 5
4 4

1
1
0 0
3 3
6
1 0
2 0
0 2
0 3
1 3
2 3

1
1
0 0
1 0
0

1
1
0 0
1 2
15
4 1
4 2
4 3
1 0
2 0
3 0
1 1
0 1
0 2
0 3
1 3
2 1
3 3
2 2
3 1

1
2
0 0
2 2
1
1 1
예제 출력

YES
NO

 */
class Node {
	public int x;
	public int y;
	public int len;
	public int flag;
	public int index;

	public Node() {

	}

	public Node(int x, int y, int flag) {
		this.x = x;
		this.y = y;
		this.flag = flag;
	}
}

public class GraphDuck {
	static int jumpLen;
	static int jumpLenPow;
	static int startX;
	static int startY;
	static int endX;
	static int endY;
	static boolean isFinish;
	static ArrayList<Node> lW;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		for (int c = 0; c < TC; ++c) {
			jumpLen = sc.nextInt();
			jumpLenPow = (int) Math.pow(jumpLen, 2);
			startX = sc.nextInt();
			startY = sc.nextInt();
			endX = sc.nextInt();
			endY = sc.nextInt();
			lW = new ArrayList<Node>();
			isFinish = false;
			Node start = new Node(startX, startY, 0);
			Node end = new Node(endX, endY, 0);
			int stoneCnt = sc.nextInt();
			lW.add(start);
			for (int s = 0; s < stoneCnt; ++s) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				Node node = new Node(x, y, 0);
				lW.add(node);
			}
			lW.add(end);
			lW.get(0).flag = 1;
			canDuckGoToEnd(lW.get(0));
			if (isFinish == false) {
				System.out.println("NO");
			}
		}
	}

	private static boolean canDuckGoToEnd(Node from) {
		if (isFinish == true) {
			return true;
		}
		if (from.x == endX && from.y == endY) {
			System.out.println("YES");
			isFinish = true;
			return true;
		}
		ArrayList<Node> nodes = getCloseNodes(from);
		if (nodes.size() == 0) {
			return false;
		}
		for (Node node : nodes) {
			lW.get(node.index).flag = 1;
			boolean canEnd = canDuckGoToEnd(node);
			if (canEnd) {
				lW.get(node.index).flag = 0;
			}
		}

		return false;
	}

	private static ArrayList<Node> getCloseNodes(Node node) {
		ArrayList<Node> nodes = new ArrayList<Node>();
		for (int i = 1; i < lW.size(); ++i) {
			if (lW.get(i).flag != 1) {
				int temp = calcLenPow(node, lW.get(i));
				if (temp <= jumpLenPow) {
					lW.get(i).index = i;
					nodes.add(lW.get(i));
				}
			}
		}
		return nodes;
	}

	private static int calcLenPow(Node a, Node b) {
		return (int) (Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2));
	}
}
