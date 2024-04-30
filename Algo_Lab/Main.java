import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        Rectangle[] rectangles = new Rectangle[n]; //Сохраняем треугольники
        for (int i = 0; i < n; i++) {
            rectangles[i] = new Rectangle(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
        }

        FirstAlgo solution = new FirstAlgo(rectangles); //Выбираем 1 из 3 алгоритмов
        //SecondAlgo solution = new SecondAlgo(rectangles);
        //ThirdAlgo solution = new ThirdAlgo(rectangles);

        int m = scanner.nextInt();
        for (int i = 0; i < m; i++) { //Для каждой точки делаем запрос к нашему алгоритму
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int sum = solution.countRectangles(x,y);
            System.out.println(sum);
        }
    }
}
