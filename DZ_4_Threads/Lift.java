import java.util.ArrayList;
import java.util.Collections;

public class Lift  extends Thread{
    private int curFloor;
    int direction; //1 - вверх, 0 - вниз
    LIftsInfo info;
    boolean isComingToStart;
    private int to;
    private final int from;
    private final int liftNumber;
    Lift(Query query,int liftNumber,LIftsInfo info){
        direction = query.direction;
        this.curFloor = info.liftPositions[liftNumber];
        this.to = query.to;
        this.from = query.from;
        this.liftNumber = liftNumber;
        this.info = info;
    }

    public int getCurFloor() {
        return curFloor;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public void run(){
        try {
            isComingToStart = true;
            Thread.sleep(1000L *(Math.abs(curFloor-from)));
            curFloor = from;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        isComingToStart = false;
        System.out.println("Лифт " + this.liftNumber + " начал своё движение с "+ this.curFloor + " этажа!");
        while (curFloor!=to) {
            checkFloor(curFloor);
            try {
                if (this.liftNumber==0){
                    Thread.sleep(100000);
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("зДЕСЬ ЭКСПЕШПН");
                throw new RuntimeException(e);
            }
            if (direction==1){
                curFloor++;
            }
            else{
                curFloor--;
            }
        }
        System.out.println("Лифт " + this.liftNumber + " прибыл на " + this.to + " этаж");
        info.liftPositions[this.liftNumber] = this.to;
        info.offloadOrder();
    }

    void checkFloor(int floor){
        ArrayList<Integer> People = info.callsOfLift.get(floor-1).get(direction);
        int cntPeople = People.size();
        if (cntPeople==0){
            return;
        }
        Collections.sort(People);
        if (this.direction==1){
            this.to = Math.max(this.to,People.getLast());
        }
        else{
            this.to = Math.min(this.to,People.getFirst());
        }
            System.out.println(" Лифт " + liftNumber + " забрал " + cntPeople + " человек с " + floor + " этажа");
            info.callsOfLift.get(floor-1).get(direction).clear();
    }
}
