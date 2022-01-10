import java.util.LinkedList;
public class DisjointList extends NotSortedLinkedList {

    public DisjointList() {

    }

    public void insert2(Node node, LinkedList<DisjointList> list) {
        for(DisjointList disjointList:list){
            for(Node temp:disjointList){
                if(node.getValue() == temp.getValue()){
                    return;
                }
            }
        }
        insert(node);
    }
}
