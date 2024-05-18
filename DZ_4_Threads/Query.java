public class Query {
    int direction;
    int from;
    int to;
    Query(int from,int to){
        this.from = from;
        this.to = to;
        if (from<to){
            direction=1;
        }
        else{
            direction = 0;
        }
    }
}
