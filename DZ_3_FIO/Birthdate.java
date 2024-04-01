import java.io.IOException;
import java.time.LocalDateTime;

public class Birthdate {
    private int year;
    private int month;
    private int day;
    
    public Birthdate(String date) throws IOException {
        this.day = Integer.parseInt(date.substring(0,2));
        this.month = Integer.parseInt(date.substring(3,5));
        this.year = Integer.parseInt(date.substring(6,10));

        String curr = String.valueOf(LocalDateTime.now());
        int curr_year = Integer.parseInt(curr.substring(0,4));
        int curr_month = Integer.parseInt(curr.substring(5,7));
        int curr_day = Integer.parseInt(curr.substring(8,10));

        if (this.day>31 || this.month>12 || this.year>curr_year){
            System.out.println("Вы ввели невалидную дату!");
            throw new IOException();
        }

        if (this.year==curr_year){
            if (this.month>curr_month || (this.month==curr_month && this.day==curr_day)){
                System.out.println("Вы ввели невалидную дату!");
                throw new IOException();
            }
        }

        if ((this.month == 4 || this.month==6 || this.month==9 || this.month==11) && this.day>30){
            System.out.println("Вы ввели невалидную дату!");
            throw new IOException();
        }

        if (this.month==2){
            if (this.day>28){
                if (!(this.year%4==0 && this.day==29)) {
                    System.out.println("Вы ввели невалидную дату!");
                    throw new IOException();
                }
            }
        }
    }
    
    public int countAge(){
        String curr = String.valueOf(LocalDateTime.now());
        int age = Integer.parseInt(curr.substring(0,4))-this.year;
        if (Integer.parseInt(curr.substring(5,7))<this.month){
            age--;
        }
        else if (Integer.parseInt(curr.substring(5,7))==this.month){
            if (Integer.parseInt(curr.substring(8,10))==this.day){
                age--;
            }
        }
        return age;
    }
}
