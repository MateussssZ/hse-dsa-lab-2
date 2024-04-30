import java.util.ArrayList;

public class TimeCounter {

    Rectangle[] rectangles;
    ArrayList<ArrayList<Integer>> Points;
    ArrayList help = new ArrayList<>();
    TimeCounter(Rectangle[] rectangles,ArrayList<ArrayList<Integer>> Points){
        this.rectangles = rectangles;
        this.Points = Points;
    }
    public void checkTime(){
        FirstAlgo solution1 = new FirstAlgo(rectangles);
        SecondAlgo solution2 = new SecondAlgo(rectangles);
        ThirdAlgo solution3 = new ThirdAlgo(rectangles);
        for (int i =0;i<3;i++){
            long start = System.currentTimeMillis();
            for (int j =0;j<Points.size();j++){
                ArrayList<Integer> point = Points.get(j);
                if (i==0){
                    solution1.countRectangles(point.get(0),point.get(1));

                } else if (i==1) {
                    solution2.countRectangles(point.get(0),point.get(1));
                }
                else{
                    solution3.countRectangles(point.get(0),point.get(1));
                }
            }
            long end = System.currentTimeMillis();
            long time = end-start;
            System.out.println(i+" Алгоритм:"+" Запрос для "+Points.size()+ " точек при " + rectangles.length+" прямоугольниках занял " + time + "мс");
        }
    }

    public void setPoints(ArrayList<ArrayList<Integer>> Points){
        this.Points = Points;
    }

    public void createRectangles(int n){
        Rectangle[] rectangles = new Rectangle[n];
        for (int i =0; i<n;i++){
            rectangles[i]=new Rectangle(10*i,10*i,10*(2*n-i),10*(2*n-i));
        }
        this.rectangles = rectangles;
    }

    public int power(int x,int y,int n){
        if (y == 0){
            return 1;
        }
        int z = power(x % n, y / 2, n) % n;
        if (y % 2 == 0){
            return (z * z) % n;
        }
        else{
            return ((x % n) * ((z * z) % n)) % n;
        }
    }
    public void create_points(int n){
        this.Points.clear();
        int p_x = 283;
        int p_y = 191;
        int x,y;
        for (int i = 0;i< 2*n;i++){
            x = power(p_x*i,31,20*n);
            y = power(p_y*i,31,20*n);
            ArrayList<Integer> point = new ArrayList<>();
            point.add(x);
            point.add(y);
            Points.add(point);
        }
    }
}
