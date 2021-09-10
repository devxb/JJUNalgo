import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main{
    
    public static void main(String[] args){
        Solve solve = new Solve();
		solve.run();
    }
    
}

class Solve{
	
	private static class Rect{
		// Builder 패턴 연습용
		private final int width;
		private final int height;
		
		private static class Builder{
			
			private int width;
			private int height;
			
			public Builder width(int width){
				this.width = width;
				return this;
			}
			
			public Builder height(int height){
				this.height = height;
				return this;
			}
			
			public Rect build(){
				return new Rect(this);
			}
			
		}
		
		public Rect(Builder builder){
			this.width = builder.width;
			this.height = builder.height;
		}
		
	}
	
	public void run(){
		input();
	}
	
	private void input(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			StringBuilder stringBuilder = new StringBuilder();
			int T = Integer.parseInt(br.readLine());
			String[] read = null;
			for(int i = 0; i < T; i++){
				ArrayList<Rect> list = new ArrayList<Rect>();
				for(int j = 0; j < 3; j++){
					read = br.readLine().split(" ");
					int width = Integer.parseInt(read[0]);
					int height = Integer.parseInt(read[1]);
					Rect rect = new Rect.Builder().width(width).height(height).build();
					list.add(rect);
				}
				stringBuilder.append(packaging(0, 0, list, new boolean[5], new Rect.Builder().width(0).height(0).build()));
				stringBuilder.append("\n");
			}
			System.out.println(stringBuilder.toString());
		}catch(IOException IOE){}
	}
	
	private int packaging(int idx, int cnt, ArrayList<Rect> list, boolean[] check, Rect rect){
		int ret = 987654321;
		if(cnt == 2){
			Rect remain = null;
			for(int i = 0; i < 3; i++)
				if(!check[i]) remain = list.get(i);
			// 수평
			Rect ansWRect = new Rect.Builder()
				.width(remain.width+rect.width)
				.height(Math.max(remain.height, rect.height))
				.build();
			// 수직
			Rect ansHRect = new Rect.Builder()
				.width(Math.max(remain.width, rect.width))
				.height(remain.height+rect.height)
				.build();
			return Math.min(ansWRect.width*ansWRect.height, ansHRect.width*ansHRect.height);
		}
		for(int i = idx; i < 3; i++){
			if(check[i]) continue;
			check[i] = true;
			for(int dir = 0; dir < 2; dir++){
				Rect nextWRect = null;
				Rect nextHRect = null;
				if(dir == 0){
					// 정방향 수평 연결
					nextWRect = new Rect.Builder()
						.width(rect.width+list.get(i).width)
						.height(Math.max(rect.height, list.get(i).height))
						.build();
					// 정방향 수직 연결
					nextHRect = new Rect.Builder()
						.width(Math.max(rect.width, list.get(i).width))
						.height(rect.height+list.get(i).height)
						.build();
				}
				if(dir == 1){
					// 90도 회전 수평 연결
					nextWRect = new Rect.Builder()
						.width(rect.width+list.get(i).height)
						.height(Math.max(rect.height, list.get(i).width))
						.build();
					// 90도 회전 수직 연결
					nextHRect = new Rect.Builder()
						.width(Math.max(rect.width,list.get(i).height))
						.height(rect.height+list.get(i).width)
						.build();
				}
				ret = Math.min(ret, packaging(i+1, cnt+1, list, check, nextWRect));
				ret = Math.min(ret, packaging(i+1, cnt+1, list, check, nextHRect));
			}
			check[i] = false;
		}
		return ret;
	}
	
}
