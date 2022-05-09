import static java.lang.Math.*;

class Solution {

    public int solution(int[] cookies) {
        int[] sumCookies = this.makeSumCookies(cookies);
        int maxDeployableCookie = this.findMaxDeployableCookie(cookies, sumCookies);
        return maxDeployableCookie;
    }

    private int[] makeSumCookies(int[] cookies){
        int[] sumCookies = new int[cookies.length];
        sumCookies[0] = cookies[0];
        for(int i = 1; i < cookies.length; i++) sumCookies[i] = sumCookies[i-1] + cookies[i];
        return sumCookies;
    }

    private int findMaxDeployableCookie(int[] cookies, int[] sumCookies){
        int currentMaxDeployCookie = 0;
        for(int i = 0; i < sumCookies.length; i++){
            int deployCookie = this.findMiddleOfDeployableCookie(currentMaxDeployCookie, i, cookies, sumCookies);
            currentMaxDeployCookie = max(currentMaxDeployCookie, deployCookie);
        }
        return currentMaxDeployCookie;
    }

    private int findMiddleOfDeployableCookie(int currentMaxDeployCookie, int idx, int[] cookies, int[] sumCookies){
        int leftSum = sumCookies[idx];
        int rightSum = sumCookies[sumCookies.length-1] - sumCookies[idx];
        int leftIdx = 0, rightIdx = sumCookies.length-1;
        while(leftSum != rightSum && leftIdx < rightIdx && currentMaxDeployCookie < min(leftSum, rightSum)){
            while(leftSum < rightSum && leftIdx < rightIdx) {
                rightSum = rightSum - cookies[rightIdx];
                rightIdx--;
            }
            while(leftSum > rightSum && leftIdx < rightIdx){
                leftSum = leftSum - cookies[leftIdx];
                leftIdx++;
            }
        }
        if(leftSum == rightSum) return leftSum;
        return 0;
    }

    private int getNameSpaceElement(int nameSpace, int[] sumCookies){
        if(nameSpace == -1) return 0;
        return sumCookies[nameSpace];
    }

}
