public class Node { //Ноды для персистентного ДО
    Node left, right;
    byte leftRange, rightRange, sum;

    Node(Node left, Node right, byte leftRange, byte rightRange, byte sum) {
        this.left = left;
        this.right = right;
        this.rightRange = rightRange;
        this.leftRange = leftRange;
        this.sum = sum;
    }
}