import java.util.Random;

public class NotSortedLinkedList extends LinkedList{

    public NotSortedLinkedList(){

    }

    public void insert(Node node) {
        if(size() == 0){
            add(node);
        }
        else{
            if(node.getValue() < getFirst().getValue()){
                setABC(null,node,getFirst());
                addFirst(node);
            }
            else{
                setABC(getLast(),node,null);
                addLast(node);
            }
        }
    }

    public Node extractMin(){
        Node min = removeFirst();
        min.setNext(null);
        getFirst().setPrev(null);

        Node temp = getFirst();
        Node newMin = getFirst();
        int newMinIndex = 0;
        int counter = 0;
        while(temp != null){
            if(temp.getValue() < getFirst().getValue()){
                newMin = temp;
                newMinIndex = counter;
            }
            temp = temp.getNext();
            counter++;
        }
        removeNode(newMin);
        temp = remove(newMinIndex);
        setABC(null,temp,getFirst());
        addFirst(temp);
        return min;
    }

    public NotSortedLinkedList unify(NotSortedLinkedList B){
        NotSortedLinkedList A = this;

        if(B.getFirst().getValue() < A.getFirst().getValue()){
            setABC(null,B.getFirst(),A.getFirst());
            A.addFirst(B.removeFirst());
        }
        for(Node node: B){
            setABC(A.getLast(), node,null);
            addLast(node);
        }

        return A;
    }



}
