import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private int N, M;
	private boolean[][] arr;
	private boolean[] booleans = {true, false};
	private int result;

	public static void main(String[] args) {
		(new Main()).solve();
	}

	private void solve() {
		input();
		fillNemo(0, 0);
		System.out.println(result + 1);
	}

	private void input() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String[] line = br.readLine().split(" ");
			N = Integer.parseInt(line[0]);
			M = Integer.parseInt(line[1]);
			arr = new boolean[N][M];
		} catch(IOException ignored) {
		}
	}

	private void fillNemo(int y, int x) {
		if(y >= N || x >= M) {
			return;
		}
		int[] nextPositions = getNextPosition(y, x);
		arr[y][x] = true;
		if(!isBreak(y, x)) {
			result++;
			fillNemo(nextPositions[0], nextPositions[1]);
		}
		arr[y][x] = false;
		fillNemo(nextPositions[0], nextPositions[1]);
	}

	private boolean isBreak(int y, int x) {
		if(y - 1 < 0 || x - 1 < 0) {
			return false;
		}
		return arr[y][x] && arr[y - 1][x] && arr[y][x - 1] && arr[y - 1][x - 1];
	}

	private int[] getNextPosition(int y, int x) {
		if(x + 1 >= M) {
			return new int[] {y + 1, 0};
		} else {
			return new int[] {y, x + 1};
		}
	}

}
