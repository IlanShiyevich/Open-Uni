/**
 * This class is for some additions to the LinkedList<> class which all other lists inherit from.
 * It has toString() method that  displays the linked list in terminal, 3 sequential nodes setter and remove node from list method.
 */
public class LinkedList extends java.util.LinkedList<Node> {
    /**
     * turns linked list into string.
     * @return string of linkedlist
     */
    @Override
    public String toString() {
        if(this.isEmpty()){
            return"The list is empty";
        }
        StringBuilder query = new StringBuilder();
        if(this.getFirst() == null)
            return "null";

        query.append("<-");
        Node current = this.getFirst();
        while(current.getNext() != null){
            query.append(current.getValue()).append("<->");
            current = current.getNext();
        }
        query.append(current.getValue()).append("->");
        return query.toString();
    }

    /**
     * sets nodes in a sequential order.
     * @param a
     * @param b
     * @param c
     */
    protected void setABC(Node a, Node b, Node c){
        if(a != null)
            a.setNext(b);
        b.setNext(c);
        if (c != null)
            c.setPrev(b);
        b.setPrev(a);
    }

    /**
     * removes node from list by removing it's ties to it's previous and next nodes.
     * @param node
     */
    protected void removeNode(Node node){
        Node prev = node.getPrev();
        Node next = node.getNext();
        if(prev != null){
            prev.setNext(next);
        }
        next.setPrev(prev);
        node.setNext(null);
        node.setPrev(null);
    }
}
