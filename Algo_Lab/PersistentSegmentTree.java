import java.util.ArrayList;
import java.util.List;

public class PersistentSegmentTree {
    List<Node> PSTNodes = new ArrayList<>();

    PersistentSegmentTree(List<Event> events,int size) { //Для каждого из событий сохраняем версию дерева в списке PSTNodes
        Node root = builtEmptyPersistentTree( 0, size-1);
        for(Event event : events) {
            if (event.n<PSTNodes.size()){
                Node newRoot = PSTNodes.get(event.n); //Дерево отрезков хранит количество данных прямоугольников на различных точках
                PSTNodes.set(event.n,addNodeToPersistentTree(newRoot, event.left, event.right,event.diff));
                root = PSTNodes.get(PSTNodes.size()-1); //Таким образом, версия дерева определяет координату по Х, а вот ноды дерева - координату по У
            }
            else{
                root = addNodeToPersistentTree(root, event.left, event.right, event.diff);
                PSTNodes.add(root);
            }
        }
    }

    private Node builtEmptyPersistentTree(int leftIndex, int rightIndex) { //Построение пустого дерева
        if (rightIndex - leftIndex == 1) {
            return new Node(null, null, (byte)leftIndex, (byte) rightIndex,(byte) 0);
        }

        int middle = (leftIndex + rightIndex) / 2;

        Node left = builtEmptyPersistentTree(leftIndex, middle);
        Node right = builtEmptyPersistentTree(middle, rightIndex);

        return new Node(left, right, left.leftRange, right.rightRange, (byte) (left.sum + right.sum));
    }

    private Node addNodeToPersistentTree(Node root, int leftIndex, int rightIndex, int val) { //Обновление дерева отрезков
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

    public int searchInPersistentSegmentTree(Node root, int num) { //Поиск нужной точки в дереве
        if (root == null) return 0;

        int middle = (root.leftRange + root.rightRange) / 2;
        if (num < middle) {
            return root.sum + searchInPersistentSegmentTree(root.left, num);
        }
        else return root.sum + searchInPersistentSegmentTree(root.right, num);
    }

}