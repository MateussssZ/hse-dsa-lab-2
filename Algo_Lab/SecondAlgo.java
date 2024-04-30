import java.util.*;

public class SecondAlgo {
    List<List<Integer>> map;
    List<Integer> XCoords;
    List<Integer> YCoords;

    SecondAlgo(Rectangle[] rectangles){ //Сохраняем все уникальные точки X и Y и строим двумерное поле точек таким образом,
        SortedSet<Integer> XCoordinates = new TreeSet<>(); //Что к каждой точке Х подставляется каждая точка У
        SortedSet<Integer> YCoordinates = new TreeSet<>();

        for (Rectangle rect : rectangles){
            XCoordinates.add(rect.x1);
            XCoordinates.add(rect.x2);
            YCoordinates.add(rect.y1);
            YCoordinates.add(rect.y2);
        }
        this.XCoords = new ArrayList<>(XCoordinates);
        this.YCoords = new ArrayList<>(YCoordinates);

        this.map = new ArrayList<>();
        FirstAlgo cnter = new FirstAlgo(rectangles);
        for (Integer x : XCoords){
            map.add(new ArrayList<>());
            for (Integer y : YCoords){
                map.get(map.size()-1).add(cnter.countRectangles(x,y));
            }
        }
    }

    public int countRectangles(int x,int y) { //Определяем наибольшую из меньших точек на карте(относительно данной)
                                              // и возвращаем значение данное на карте
        int XIdx = BinarySearch(x, XCoords);
        int YIdx = BinarySearch(y, YCoords);
        if (XIdx == -1 || YIdx == -1){
            return 0;
        }
        return map.get(XIdx).get(YIdx);
    }

    int BinarySearch(Integer coord,List<Integer> Coords){
        if (Coords.size()==0 || coord<Coords.get(0)){
            return -1;
        }
        int l =0;
        int r = Coords.size()-1;
        while (l<r){
            int mid = (l+r)/2;
            if (Coords.get(mid)>coord){
                r=mid-1;
            }
            else{
                if (Coords.get(mid+1)<=coord){
                    l=mid+1;
                }
                else{
                    return mid;
                }
            }
        }
        return l;
    }
}