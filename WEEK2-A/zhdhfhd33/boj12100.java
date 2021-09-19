import java.util.Scanner;
import java.util.Vector;

class Pair {
	int x;
	int y;

	Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		String res = "x : " + x + " y : " + y;
		return res;
	}
}

enum Direction {
	UP, DOWN, LEFT, RIGHT
}

public class Main {
//	public static void up(int arr[][], int x, int y) {
//		
//	}

	// 값, idx를 반환 다음 번 숫자가 없으면 null 반환
	public static Pair nextRight(int arr[][], int x, int y) {
		for (int i = y + 1; i < arr.length; ++i) {
			if (arr[x][i] != 0) {
				return new Pair(arr[x][i], i);
			}
		}
		return null;
	}

	public static Pair nextLeft(int arr[][], int x, int y) {
		for (int i = y - 1; i >= 0; --i) {
			if (arr[x][i] != 0) {
				return new Pair(arr[x][i], i);
			}
		}
		return null;
	}

	public static Pair nextDown(int arr[][], int x, int y) {
		for (int i = x + 1; i < arr.length; ++i) {
			if (arr[i][y] != 0) {
				return new Pair(arr[i][y], i);
			}
		}
		return null;
	}

	public static Pair nextUp(int arr[][], int x, int y) {
		for (int i = x - 1; i >= 0; --i) {
			if (arr[i][y] != 0) {
				return new Pair(arr[i][y], i);
			}
		}
		return null;
	}

	// 겹쳐지는 녀석들의 index를 vec으로 반환.(값도 같을 때 벡터에 들어감.) 처음에는 dir 매개변수를 받았는데 생각해보니 방향은 상관없음.
	public static Vector<Integer> getRowVec(int arr[][], int a) {
		Vector<Integer> res = new Vector<>();// res에는 인덱스가 들어간다.
		for (int i = 0; true;) {
//			if (arr[a][i]==0) continue; 없어도 잘 돌아간다. 

			Pair p = nextRight(arr, a, i);
			if (p == null) {// 다음번이 없을 떄
				return res;
			}
			if (p.x == arr[a][i]) {
				if (res.size() == 0)
					res.add(i);
				res.add(p.y);

			}
			i = p.y;
		} // for end
	}
	
	public static Vector<Integer> getColVec(int arr[][], int a) {
		Vector<Integer> res = new Vector<>();// res에는 인덱스가 들어간다.
		for (int i = 0; true;) {
//			if (arr[a][i]==0) continue; 없어도 잘 돌아간다. 

			Pair p = nextDown(arr, i, a);
			if (p == null) {// 다음번이 없을 떄
				return res;
			}
			if (p.x == arr[i][a]) {
				if (res.size() == 0)
					res.add(i);
				res.add(p.y);

			}
			i = p.y;
		} // for end
	}
	
	
	public static void moveLeft(int arr[][]) {
		for (int i=0; i<arr.length; ++i) {
			Vector<Integer> vec=getRowVec(arr, i);
			final int SIZE=vec.size();
			for (int j=0; j+1<SIZE; j+=2) {//합쳐지는 녀석들을 겹친다.
				arr[i][vec.get(j)]+=arr[i][vec.get(j+1)];
				arr[i][vec.get(j+1)]=0;
			}
			
			//이제 왼쪽으로 모두 밀어넣기
			for (int j=0; j<arr.length; ++j) {
				if (arr[i][j]==0) {//비어있을 때만 진행
					Pair p=nextRight(arr, i, j);
					if (p==null) {
						break;
					}
					arr[i][j]=p.x;
					arr[i][p.y]=0;
					
				}
			}
		}
	}
	public static void printArr(int arr[][]) {
		for (int i = 0; i < arr.length; ++i) {
			
			for (int j = 0; j < arr.length; ++j) {
				System.out.printf("%d ", arr[i][j]);
			}
			System.out.println();
			
		}
				
	}


	public static void main(String[] args) {
		int N;
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int arr[][] = new int[N][N]; // 자동 0

		// arr입력
		for (int i = 0; i < N; ++i)
			for (int j = 0; j < N; ++j)
				arr[i][j] = sc.nextInt();
		sc.close();

//		System.out.println(checkCol(arr, 0));
//		System.out.println(checkCol(arr, 1));
//		System.out.println(checkCol(arr, 2));

//		System.out.println(nextRight(arr, 2, 1));
//		System.out.println(nextLeft(arr, 2, 1));
//		System.out.println(nextUp(arr, 2, 1));
//		System.out.println(nextDown(arr, 2, 1));
		
//		System.out.println(getColVec(arr, 0));
		moveLeft(arr);
		printArr(arr);

	}

}
