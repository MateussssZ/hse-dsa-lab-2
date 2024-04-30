public class Event {
    short n;
    short left, right;
    byte diff;

    Event(short n, short left, short right, byte diff) {
        this.n = n;
        this.left = left;
        this.right = right;
        this.diff = diff;
    }
}