import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int from = -2,to=-2;
        int floors = 0;
        System.out.print("Введите количество этажей в доме: ");
        while(floors<2){
            floors = in.nextInt();
            if (floors==1){
                System.out.println("Для одноэтажного дома не нужен лифт");
            }
            else if(floors<1){
                System.out.println("В доме не может быть меньше 1 этажа");
            }
        }
        House house = new House(floors);
        while (true){
            System.out.println("Введите этаж, на котором нажали кнопку и ,через пробел, этаж, на который нужно добраться(введите <-1 -1>, чтобы выйти): ");
            from = in.nextInt();
            to = in.nextInt();
            if (from<-1 || to<-1){
                System.out.println("Вы ввели несуществующий этаж!");
            }
            else if (from==-1 && to==-1){
                break;
            }
            else{
                house.handleCall(from,to);
            }
        }
    }
}
