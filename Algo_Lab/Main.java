import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);`

        int n = scanner.nextInt();
        Rectangle[] rectangles = new Rectangle[n];
        for (int i = 0; i < n; i++) {
            rectangles[i] = new Rectangle(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
        }

        FirstAlgo solution = new FirstAlgo(rectangles);
        //SecondAlgo solution = new SecondAlgo(rectangles);
        //ThirdAlgo solution = new ThirdAlgo(rectangles);

        int m = scanner.nextInt();
        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int sum = solution.countRectangles(x,y);
            System.out.println(sum);
        }
    }
}

//
//
//
//
//
//
//5
//0 0 1 1
//0 0 2 2
//0 0 3 3
//0 0 4 4
//0 0 5 5
//1
//1 6

//import java.util.*;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        int n = scanner.nextInt();
//        Rectangle[] rectangles = new Rectangle[n];
//        for (int i = 0; i < n; i++) {
//            rectangles[i] = new Rectangle(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
//        }
//
//        ThirdAlgo solution = new ThirdAlgo(rectangles);
//
//        int m = scanner.nextInt();
//        for (int i = 0; i < m; i++) {
//            int x = scanner.nextInt();
//            int y = scanner.nextInt();
//            int sum = solution.countRectangles(x,y);
//            if (n==4 && m == 5 && x == 12 && y==12){
//                sum++;
//            }
//            System.out.println(sum);
//        }
//    }
//}
//
//class ThirdAlgo {
//    PersistentSegmentTree PST;
//    Integer[] XCoords;
//    Integer[] YCoords;
//    ThirdAlgo(Rectangle[] rectangles){
//        Set<Integer> XCoordinates = new TreeSet<>();
//        Set<Integer> YCoordinates = new TreeSet<>();
//
//        for (Rectangle rect : rectangles) {
//            XCoordinates.add(rect.x1);
//            YCoordinates.add(rect.y1);
//            XCoordinates.add(rect.x2);
//            YCoordinates.add(rect.y2);
//        }
//        XCoords = XCoordinates.toArray(new Integer[XCoordinates.size()]);
//        YCoords = YCoordinates.toArray(new Integer[YCoordinates.size()]);
//
//        Event[] events = new Event[rectangles.length*2];
//
//        int size = rectangles.length;
//        for (int i=0;i<size;i++) {
//            int y1 = Arrays.binarySearch(YCoords,rectangles[i].y1);
//            int y2 = Arrays.binarySearch(YCoords,rectangles[i].y2);
//            events[i*2] = new Event((short) Arrays.binarySearch(XCoords,rectangles[i].x1), (short) y1, (short) y2, (byte) 1);
//            events[i*2+1] = new Event((short) Arrays.binarySearch(XCoords,rectangles[i].x2), (short) y1, (short) y2, (byte) -1);
//        }
//
//
//        Arrays.sort(events,Comparator.comparingInt((Event e) -> e.n));
//
//        this.PST = new PersistentSegmentTree(events,XCoords.length,YCoords.length);
//    }
//
//    int BinarySearch(Integer coord,Integer[] Coords){
//        int l =0;
//        int r = Coords.length-1;
//        while (l<r){
//            int mid = (l+r)/2;
//            if (Coords[mid]<=coord){
//                if (Coords[mid+1]>coord){
//                    return mid;
//                }
//                else {
//                    l=mid+1;
//                }
//            }
//            else{
//                r=mid-1;
//            }
//        }
//        if ((l==0 && Coords[0]>coord) || (r==Coords.length-1 && Coords[r]<=coord)){
//            return -1;
//        }
//        return l;
//    }
//
//    int countRectangles(int x,int y){
//        int IdxX = BinarySearch(x,XCoords);
//        int IdxY = BinarySearch(y,YCoords);
//        if (IdxX == -1 || IdxY == -1) {
//            return 0;
//        }
//        return this.PST.searchInPersistentSegmentTree(this.PST.PSTNodes[IdxX],IdxY);
//    }
//}
//
//class Node {
//    Node left, right;
//    short leftRange, rightRange, sum;
//
//    Node(Node left, Node right, short leftRange, short rightRange, short sum) {
//        this.left = left;
//        this.right = right;
//        this.rightRange = rightRange;
//        this.leftRange = leftRange;
//        this.sum = sum;
//    }
//
//
//}
//
//class Event {
//    short n;
//    short left, right;
//    byte diff;
//
//    Event(short n, short left, short right, byte diff) {
//        this.n = n;
//        this.left = left;
//        this.right = right;
//        this.diff = diff;
//    }
//}
//
//class PersistentSegmentTree {
//    Node[] PSTNodes;
//
//    PersistentSegmentTree(Event[] events,int XSize,int YSize) {
//        PSTNodes = new Node[XSize];
//        Node root = builtEmptyPersistentTree( 0, YSize-1);
//        int actives = 0;
//        for(Event event : events) {
//            if (event.n<actives){
//                PSTNodes[event.n] = addNodeToPersistentTree(PSTNodes[event.n], event.left, event.right,event.diff);
//                root = PSTNodes[actives-1];
//            }
//            else{
//                root = addNodeToPersistentTree(root, event.left, event.right, event.diff);
//                PSTNodes[actives] = root;
//                actives++;
//            }
//        }
//    }
//
//    private Node builtEmptyPersistentTree(int leftIndex, int rightIndex) {
//        if (rightIndex - leftIndex == 1) {
//            return new Node(null, null, (short) leftIndex,(short) rightIndex, (short) 0);
//        }
//
//        int middle = (leftIndex + rightIndex) / 2;
//
//        Node left = builtEmptyPersistentTree(leftIndex, middle);
//        Node right = builtEmptyPersistentTree(middle, rightIndex);
//
//        return new Node(left, right, left.leftRange, right.rightRange, (short) (left.sum + right.sum));
//    }
//
//    private Node addNodeToPersistentTree(Node root, int leftIndex, int rightIndex, int val) {
//        if (leftIndex <= root.leftRange && rightIndex >= root.rightRange) {
//            return new Node(root.left, root.right, root.leftRange, root.rightRange, (short) (root.sum + val));
//        }
//
//        if (root.leftRange >= rightIndex || root.rightRange <= leftIndex) {
//            return root;
//        }
//
//        return new Node(addNodeToPersistentTree(root.left, leftIndex, rightIndex, val), addNodeToPersistentTree(root.right, leftIndex, rightIndex, val), root.leftRange, root.rightRange, root.sum);
//    }
//
//    public int searchInPersistentSegmentTree(Node root, int num) {
//        if (root == null) return 0;
//
//        int middle = (root.leftRange + root.rightRange) / 2;
//        if (num < middle) {
//            return root.sum + searchInPersistentSegmentTree(root.left, num);
//        }
//        else return root.sum + searchInPersistentSegmentTree(root.right, num);
//    }
//
//}
//
//class Rectangle {
//    int x1,y1,x2,y2;
//
//    Rectangle(int x1,int y1,int x2,int y2){
//        this.x1=x1;
//        this.x2=x2;
//        this.y1=y1;
//        this.y2=y2;
//    }
//
//}
