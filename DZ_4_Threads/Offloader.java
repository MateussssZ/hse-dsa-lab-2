import java.util.List;

public class Offloader extends Thread{
    LIftsInfo info;
    Offloader(LIftsInfo info){
        this.info = info;
    }

    public void run(){
        while (!info.orderDown.isEmpty() || !info.orderDown.isEmpty()){
            info.offloadOrder();
        }
    }
}
