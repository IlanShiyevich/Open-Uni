/**
 * Basic Node class. has pointers to previous node and next node and a value.
 */
public class Node {

    private int value;
    private Node next;
    private Node prev;

    public Node(int value){
        this.value = value;
        next = null;
        prev = null;
    }

    public Node(){
        next = null;
    }

    public int getValue() {
        return value;
    }

    public Node getNext() {
        return next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public void setNode(int value) {
        this.value = value;
    }
    public void setNext(Node next) {
        this.next = next;
    }
}
