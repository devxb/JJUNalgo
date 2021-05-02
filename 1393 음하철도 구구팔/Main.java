package algorithm;

import java.util.Scanner;

public class Main {
    
    public static int GCD(int y, int x){ // 움직이는 중간에 station과의 거리가 최소가될수있으므로 최대공약수를 구해서 나눠준다.
        int tmpy = y;
        int tmpx = x;
        if(tmpy < tmpx){
            int temp = tmpy;
            tmpy = tmpx;
            tmpx = temp;
        }
        while(tmpx != 0){
            int n = tmpy % tmpx;
            tmpy = tmpx;
            tmpx = n;
        }
        return tmpy;
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int stationX = sc.nextInt();
        int stationY = sc.nextInt();
        int trainX = sc.nextInt();
        int trainY = sc.nextInt();
        int dx = sc.nextInt();
        int dy = sc.nextInt();
        int gcd = GCD(dy, dx);
        dy /= gcd;
        dx /= gcd;
        Train train = new Train(trainY, trainX, dy, dx, stationY, stationX);
        train.solve();
    }
}

class Train{
    int y, x, dy, dx, sy, sx;
    int distance = 987654321; // 거리를 매우큰값으로 초기화시킨다.
    public Train(int y, int x, int dy, int dx, int sy, int sx){ // 생성자
        this.y = y;
        this.x = x;
        this.dy = dy;
        this.dx = dx;
        this.sy = sy;
        this.sx = sx;
        distance = getDistance(sy,sx);
    }
    
    private int getDistance(int sy, int sx){ // Train와 Station사이의 거리를 구한다 이때, 소수점예외를 없애기위해 sqrt를 하지않는다.
        return (int)Math.pow(y-sy,2) + (int)Math.pow(x-sx,2);
    }
    
    public boolean compareDistance(int sy, int sx){ // 원래 저장되어있던값과, 움직인후의 값을 비교한다. 더 커진다면 false를 반환한다.
        int _distance = getDistance(sy, sx);
        if(_distance > distance) return false;
        distance = _distance;
        return true;
    }
    
    public void drive(){ // train을 이동시킨다
        this.y += dy;
        this.x += dx;
    }
    
    public void solve(){ // simulator
        while(compareDistance(this.sy, this.sx)) drive();
        System.out.println((this.x-this.dx) + " " + (this.y-this.dy));
    }
    
}
