import java.util.Scanner;

public class FirstAlgo {
    Rectangle[] rectangles;

    FirstAlgo(Rectangle[] rectangles) {
        this.rectangles = rectangles;
    }

    public int countRectangles(int x,int y) {
        int sum = 0;
        for (Rectangle rect : rectangles) {
            int xStart = rect.x1;
            int yStart = rect.y1;
            int xEnd = rect.x2;
            int yEnd = rect.y2;
            if (x>= xStart && x<xEnd && y>=yStart && y<yEnd){
                sum++;
            }
        }
        return sum;
    }
}