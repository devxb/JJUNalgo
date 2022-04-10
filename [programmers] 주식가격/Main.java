import java.util.*;

class Solution {
    
    private static class Stock{
        
        private final int joinPoint;
        private final int price;
        
        public Stock(int joinPoint, int price){
            this.joinPoint = joinPoint;
            this.price = price;
        }
        
        private boolean isPriceDown(int currentPrice){
            return currentPrice < price ? true : false; 
        }
        
        private int priceHoldingTime(int currentPoint){
            return currentPoint - joinPoint;
        }
        
    }
    
    public int[] solution(int[] prices) {
        int[] ans = new int[prices.length];
        Deque<Stock> stockDeq = new LinkedList<Stock>();
        for(int i = 0; i < prices.length; i++){
            Stock currentStock = new Stock(i, prices[i]);
            this.deleteDownStocks(currentStock, ans, stockDeq);
            stockDeq.addLast(currentStock);
        }
        this.fillUpStocks(ans, stockDeq);
        return ans;
    }
    
    private void deleteDownStocks(Stock currentStock, int[] ans, Deque<Stock> stockDeq){
        while(!stockDeq.isEmpty() && stockDeq.peekLast().isPriceDown(currentStock.price)){
            Stock downStock = stockDeq.pollLast();
            ans[downStock.joinPoint] = downStock.priceHoldingTime(currentStock.joinPoint);
        }
    }
    
    private void fillUpStocks(int[] ans, Deque<Stock> stockDeq){
        int currentJoinPoint = ans.length-1;
        while(!stockDeq.isEmpty()){
            Stock currentStock = stockDeq.pollLast();
            ans[currentStock.joinPoint] = currentStock.priceHoldingTime(currentJoinPoint);
        }
    }
    
}
