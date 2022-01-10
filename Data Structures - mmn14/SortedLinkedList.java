public class SortedLinkedList extends LinkedList{

    public SortedLinkedList(){

    }

    public void insert(Node node){
        if(this.size() == 0){
            this.add(node);
        }

        else if(getLast().getValue() < node.getValue()){ //case where the node is bigger than the last in list.
            Node temp = getLast();
            addLast(node);
            setABC(temp,node,null);
        }

        else if(getFirst().getValue() > node.getValue()){
            Node temp = getFirst();
            addFirst(node);
            setABC(null,node,temp);
        }

        else{
            Node temp = getFirst();
            int counter = 0;
            while(temp != null){
                if(node.getValue() < temp.getValue()){
                    setABC(temp.getPrev(),node,temp);
                    add(counter, node);
                    break;
                }
                counter++;
                temp = temp.getNext();
            }
        }
    }

    public Node extractMin(){
        if(size() == 0){
            return null;
        }
        if(size() == 1){
            return poll();
        }
        getFirst().getNext().setPrev(null);
        getFirst().setNext(null);
        return pollFirst();
    }

    public SortedLinkedList unify(SortedLinkedList list){
        while(list.getFirst().getNext() != null){
            this.insert(list.poll());
        }
        this.insert(list.poll());
        return this;
    }
}
