import java.io.*;
import java.util.*;

public class Main{
	
	private int maxFrameSize;
	private int[] voteResults;
	private LinkedList<Frame> frames = new LinkedList<Frame>();
	
	public static void main(String[] args){
		(new Main()).solve();
	}
	
	private void solve(){
		input();
		fillFrame();
		Frame[] resultFrames = frames.toArray(new Frame[0]);
		Arrays.sort(resultFrames, (f1,f2)->{
			if(f1.candidate > f2.candidate) return 1;
			if(f1.candidate < f2.candidate) return -1;
			return 0;
		});
		StringBuilder sb = new StringBuilder();
		for(Frame resultFrame : resultFrames) sb.append(resultFrame.candidate).append(" ");
		System.out.println(sb.toString());
	}
	
	private void input(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			inputMaxFrameSize(br);
			inputVoteResults(br);
		}catch(IOException IOE){}
	}
	
	private void inputMaxFrameSize(BufferedReader br) throws IOException{
		this.maxFrameSize = Integer.parseInt(br.readLine());
	}
	
	private void inputVoteResults(BufferedReader br) throws IOException{
		int voteResultSize = Integer.parseInt(br.readLine());
		this.voteResults = new int[voteResultSize];
		String[] read = br.readLine().split(" ");
		for(int i = 0; i < voteResultSize; i++) voteResults[i] = Integer.parseInt(read[i]);
	}
	
	private void fillFrame(){
		for(int i = 0; i < voteResults.length; i++){
			Frame frame = findFrame(voteResults[i]);
			if(frame != null){
				updateFrame(frame);
				continue;
			}
			if(isFramesFull()) deleteFrame();
			frames.addFirst(new Frame(voteResults[i]));
		}
	}
	
	private Frame findFrame(int candidate){
		for(Frame frame : frames)
			if(frame.candidate == candidate) return frame;
		return null;
	}
	
	private void updateFrame(Frame frame){
		frames.get(frames.indexOf(frame)).voted++;
	}
	
	private boolean isFramesFull(){
		return frames.size() == maxFrameSize;
	}
	
	private void deleteFrame(){
		Frame targetFrame = null;
		for(Frame frame : frames){
			if(targetFrame == null) targetFrame = frame;
			if(targetFrame.voted >= frame.voted) targetFrame = frame;
		}
		frames.remove(targetFrame);
	}
	
	private static class Frame{
		
		int voted = 1;
		int candidate;
		
		public Frame(int candidate){
			this.candidate = candidate;
		}
		
	}
	
}
