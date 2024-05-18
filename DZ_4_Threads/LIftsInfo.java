import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LIftsInfo {
    int[] liftPositions = {1, 1};
    Lift[] lifts;
    ArrayDeque<Query> orderUp;
    ArrayDeque<Query> orderDown;
    ArrayList<ArrayList<ArrayList<Integer>>> callsOfLift;

    LIftsInfo(Lift[] lifts, int floors) {
        this.lifts = lifts;
        callsOfLift = new ArrayList<>();
        for (int i =0;i<floors;i++){
            callsOfLift.add(new ArrayList<>());
            ArrayList<ArrayList<Integer>> floor = callsOfLift.get(i);
            floor.add(new ArrayList<>());
            floor.add(new ArrayList<>());
        }
        orderUp = new ArrayDeque<>();
        orderDown = new ArrayDeque<>();
    }

    int chooseLift(Query query) {
        int minIdx = -1;
        int minDist = 0;
        for (int i = 0; i < lifts.length; i++) {
            Lift lift = lifts[i];
            if (lifts[i]==null){
                minIdx = i;
                continue;
            }
            if (lift.isAlive() && lift.getCurFloor()!=lift.getTo()) {
                if (query.direction!=lift.direction){
                    continue;
                }
                int curFloor = lift.getFrom();
                if ((query.direction==1 && curFloor < query.from) || (query.direction==0 && curFloor > query.from) || lift.isComingToStart) {
                    return i;
                }
            }
            else {
                if (minIdx == -1) {
                    minIdx = i;
                    minDist = Math.abs(liftPositions[i] - query.from);
                } else {
                    int newDist = Math.abs(liftPositions[i] - query.from);
                    if (minDist > newDist) {
                        minIdx = i;
                        minDist = newDist;
                    }
                }
            }
        }
        return minIdx;
    }

    public void offloadOrder() {
        ArrayDeque<Query> order;
        if (orderUp.isEmpty()){
            order = orderDown;
        }
        else{
            order = orderUp;
        }
        if (order.isEmpty()){
            return;
        }
        while (!order.isEmpty()) {
            Query query = order.pop();
            if (!callsOfLift.get(query.from - 1).get(query.direction).isEmpty()) {
                int LiftIdx = chooseLift(query);
                if (LiftIdx != -1 && !lifts[LiftIdx].isAlive()) {
                    lifts[LiftIdx] = new Lift(query, LiftIdx, this);
                    return;
                }
            }
            order.add(query);
        }
    }
}
