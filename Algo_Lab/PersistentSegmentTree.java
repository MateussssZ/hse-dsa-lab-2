import java.util.ArrayList;
import java.util.List;

public class PersistentSegmentTree {
    List<Node> PSTNodes = new ArrayList<>();

    PersistentSegmentTree(List<Event> events,int size) {
        Node root = builtEmptyPersistentTree( 0, size-1);
        for(Event event : events) {
            if (event.n<PSTNodes.size()){
                Node newRoot = PSTNodes.get(event.n);
                PSTNodes.set(event.n,addNodeToPersistentTree(newRoot, event.left, event.right,event.diff));
                root = PSTNodes.get(PSTNodes.size()-1);
            }
            else{
                root = addNodeToPersistentTree(root, event.left, event.right, event.diff);
                PSTNodes.add(root);
            }
        }
    }

    private Node builtEmptyPersistentTree(int leftIndex, int rightIndex) {
        if (rightIndex - leftIndex == 1) {
            return new Node(null, null, (byte)leftIndex, (byte) rightIndex,(byte) 0);
        }

        int middle = (leftIndex + rightIndex) / 2;

        Node left = builtEmptyPersistentTree(leftIndex, middle);
        Node right = builtEmptyPersistentTree(middle, rightIndex);

        return new Node(left, right, left.leftRange, right.rightRange, (byte) (left.sum + right.sum));
    }

    private Node addNodeToPersistentTree(Node root, int leftIndex, int rightIndex, int val) {
        if (leftIndex <= root.leftRange && rightIndex >= root.rightRange) {
            return new Node(root.left, root.right, root.leftRange, root.rightRange, (byte) (root.sum + val));
        }

        if (root.leftRange >= rightIndex || root.rightRange <= leftIndex) {
            return root;
        }

        Node newRoot = new Node(root.left, root.right, root.leftRange, root.rightRange, root.sum);

        newRoot.left = addNodeToPersistentTree(newRoot.left, leftIndex, rightIndex, val);
        newRoot.right = addNodeToPersistentTree(newRoot.right, leftIndex, rightIndex, val);

        return newRoot;
    }

    public int searchInPersistentSegmentTree(Node root, int num) {
        if (root == null) return 0;

        int middle = (root.leftRange + root.rightRange) / 2;
        if (num < middle) {
            return root.sum + searchInPersistentSegmentTree(root.left, num);
        }
        else return root.sum + searchInPersistentSegmentTree(root.right, num);
    }

}