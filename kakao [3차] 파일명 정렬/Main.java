import java.util.*;

class Solution {
    
    public String[] solution(String[] inputs) {
        File[] files = this.makeFile(inputs);
        Arrays.sort(files, (File file1, File file2)->{
            int headCompareResult = file1.head.compareTo(file2.head);
            if(headCompareResult == 0) return file1.number.compareTo(file2.number);
            else return headCompareResult;
        });
        return filesToString(files);
    }
    
    private File[] makeFile(String[] inputs){
        File[] files = new File[inputs.length];
        for(int i = 0; i < inputs.length; i++) files[i] = new File(inputs[i]);
        return files;
    }
    
    private String[] filesToString(File[] files){
        String[] result = new String[files.length];
        for(int i = 0; i < files.length; i++) result[i] = files[i].ordinaryInput;
        return result;
    }
    
    private static class File{
        String ordinaryInput;
        String head;
        Integer number;
        
        public File(String input){
            this.ordinaryInput = input;
            this.head = this.getHead(input).toLowerCase();
            this.number = this.getNumber(input);
        }
        
        private String getHead(String input){
            int firstNumberIdx = this.getFirstNumberIdx(input);
            return input.substring(0, firstNumberIdx);
        }
        
        private Integer getNumber(String input){
            int firstNumberIdx = this.getFirstNumberIdx(input);
            int lastNumberIdx = this.getLastNumberIdx(firstNumberIdx, input);
            return Integer.valueOf(input.substring(firstNumberIdx, lastNumberIdx+1), 10);
        }
        
        private int getFirstNumberIdx(String input){
            for(int i = 0; i < input.length(); i++)
                if(this.isNumber(input.charAt(i))) return i;
            return input.length();
        }
        
        private int getLastNumberIdx(int firstNumberIdx, String input){
            for(int i = firstNumberIdx; i < input.length(); i++) 
                if(!this.isNumber(input.charAt(i))) return i-1;
            return input.length()-1;
        }
        
        private boolean isNumber(char c){
            return (int)c >= (int)'0' && (int)c <= (int)'9';
        }
        
    }
    
}
