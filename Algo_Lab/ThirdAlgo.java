import java.util.*;

class ThirdAlgo {
    PersistentSegmentTree PST;
    Integer[] XCoords;
    Integer[] YCoords;
    ThirdAlgo(Rectangle[] rectangles){ //Сохраняем все уникальные точки Х и У
        SortedSet<Integer> XCoordinates = new TreeSet<>();
        SortedSet<Integer> YCoordinates = new TreeSet<>();

        for (Rectangle rect : rectangles) {
            XCoordinates.add(rect.x1);
            YCoordinates.add(rect.y1);
            XCoordinates.add(rect.x2);
            YCoordinates.add(rect.y2);
        }
        XCoords = XCoordinates.toArray(new Integer[XCoordinates.size()]);
        YCoords = YCoordinates.toArray(new Integer[YCoordinates.size()]);

        List<Event> events = new ArrayList<>();


        for (Rectangle rect : rectangles){ //Сохраняем все вертикальные стороны в качестве Event
            events.add(new Event((short) Arrays.binarySearch(XCoords,rect.x1),(short) Arrays.binarySearch(YCoords,rect.y1),(short) Arrays.binarySearch(YCoords,rect.y2),(byte) 1));
            events.add(new Event((short) Arrays.binarySearch(XCoords,rect.x2),(short) Arrays.binarySearch(YCoords,rect.y1),(short) Arrays.binarySearch(YCoords,rect.y2),(byte) -1));
        }


        events.sort((Event e1,Event e2)-> e1.n-e2.n);
        if (rectangles.length!=0){
            this.PST = new PersistentSegmentTree(events,YCoords.length);
        }
    }

    int BinarySearch(Integer coord,Integer[] Coords){ //Для поиска нужных точек
        int l =0;
        int r = Coords.length-1;
        if (Coords.length==0 || Coords[0]>coord || Coords[r]<=coord){
            return -1;
        }
        while (l<r){
            int mid = (l+r)/2;
            if (Coords[mid]<=coord){
                if (Coords[mid+1]>coord){
                    return mid;
                }
                else {
                    l=mid+1;
                }
            }
            else{
                r=mid-1;
            }
        }
        return l;
    }

    int countRectangles(int x,int y){ //Ищем по персистентному ДО ответ
        int IdxX = BinarySearch(x,XCoords);
        int IdxY = BinarySearch(y,YCoords);
        if (IdxX == -1 || IdxY == -1 || XCoords.length==0) {
            return 0;
        }
        return this.PST.searchInPersistentSegmentTree(this.PST.PSTNodes.get(IdxX),IdxY);
    }
}