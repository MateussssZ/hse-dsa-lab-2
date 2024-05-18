import java.util.ArrayList;
import java.util.List;

public class House {
    Lift[] lifts = new Lift[2];
    LIftsInfo info;
    House(int floors){
        this.info = new LIftsInfo(lifts,floors);
    }

    public void handleCall(int from,int to){
        Query query = new Query(from,to);
        int liftIdx = info.chooseLift(query);
        if (liftIdx!=-1){
            info.callsOfLift.get(query.from-1).get(query.direction).add(query.to);
            if(lifts[liftIdx]==null || !lifts[liftIdx].isAlive()){
                lifts[liftIdx] = new Lift(query,liftIdx,info);
                lifts[liftIdx].start();
            }
        }
        else {
            info.callsOfLift.get(query.from - 1).get(query.direction).add(query.to);
            if (query.direction==1){
                info.orderUp.add(query);
            }
            else{
                info.orderDown.add(query);
            }
            Offloader offloader = new Offloader(info);
            offloader.start();
        }
    }


}
