import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * This class is responsible for the user interface.
 * it stores 3 LinkedLists with the 3 types of the Linkedlists that are required.
 */
public class Menu {

    private LinkedList<SortedLinkedList> sortedLists = new LinkedList<>();
    private LinkedList<NotSortedLinkedList> notSortedLists = new LinkedList<>();
    private LinkedList<DisjointList> disjointLists = new LinkedList<>();
    private boolean flag;
    private int type = 1;

    /**
     * constructor.
     */
    public Menu(){
        flag = true;
    }

    /**
     * initiats the menu. while flag(while user hasn't chosen exit) is true the menu will continue to appear.
     * @throws FileNotFoundException
     */
    public void init() throws FileNotFoundException {
        type = chooseType();
        while(flag){
            flag = chooseAction(type);
        }
    }

    /**
     * Scans the type of list from the user via terminal.
     * @return
     */
    private int chooseType(){
        Scanner scanned = new Scanner(System.in);
        System.out.println("Please choose one of the following lists(enter option 1-3):\n1. Sorted list\n2. Not sorted list\n3. Not sorted lists and dynamic groups are different");
        int listType = scanned.nextInt();
        if(listType > 3 || listType < 1){
            return chooseType();
        }
        return listType;
    }

    /**
     * scans which action to preform on the list that was chosen from user.
     * @param type
     * @return
     * @throws FileNotFoundException
     */
    private boolean chooseAction(int type) throws FileNotFoundException {
        Scanner scanned = new Scanner(System.in);
        System.out.println("Please choose one of the following options(case insensitive):");
        System.out.println("MakeHeap");
        System.out.println("Insert 'x' (x = any integer)");
        System.out.println("Min");
        System.out.println("ExtractMin");
        System.out.println("Union");
        System.out.println("Enter .txt file path (enter exact path, if not, program will crash)");
        System.out.println("Exit");
        String choice = scanned.nextLine();
        choice = choice.toLowerCase();
        if(choice.equals("exit")){
            return false;
        }
        menu(choice, type);
        return true;
    }

    /**
     * reads from file.
     * @param path
     * @throws FileNotFoundException
     */
    private void fileInit(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanned = new Scanner(file);
        while(scanned.hasNextLine()){
            menu(scanned.nextLine().toLowerCase(), type);
        }
    }

    /**
     * main menu,  navigates through user choices and performs chosen actions.
     * @param line
     * @param listType
     * @throws FileNotFoundException
     */
    private void menu(String line, int listType) throws FileNotFoundException {
        int value = 0;
        String[] insert;
        insert = line.split(" ");
        line = insert[0];
        if(insert.length == 2){
            value = Integer.parseInt(insert[1]);
        }

        switch(line) {
            case "makeheap":
                if (listType == 1) {
                    sortedLists.add(new SortedLinkedList());
                } else if (listType == 2) {
                    notSortedLists.add(new NotSortedLinkedList());
                } else if (listType == 3) {
                    disjointLists.add(new DisjointList());
                }
                break;

            case "insert":
                if (listType == 1) {
                    SortedLinkedList list = sortedLists.peekLast();
                    list.insert(new Node(value));
                    System.out.println(list.toString());
                } else if (listType == 2) {
                    NotSortedLinkedList list = notSortedLists.peekLast();
                    list.insert(new Node(value));
                    System.out.println(list.toString());
                } else if (listType == 3) {
                    DisjointList list = disjointLists.peekLast();
                    list.insert2(new Node(value), disjointLists);
                    System.out.println(list.toString());
                }
                break;

            case "min":
                if (listType == 1) {
                    System.out.println(sortedLists.peekLast().getFirst().getValue());
                } else if (listType == 2) {
                    System.out.println(notSortedLists.peekLast().getFirst().getValue());
                } else if (listType == 3) {
                    System.out.println(disjointLists.peekLast().getFirst().getValue());
                }
                break;

            case "extractmin":
                if (listType == 1) {
                    System.out.println(sortedLists.peekLast().extractMin().getValue());
                    System.out.println(sortedLists.peekLast().toString());
                } else if (listType == 2) {
                    System.out.println(notSortedLists.peekLast().extractMin().getValue());
                    System.out.println(notSortedLists.peekLast().toString());
                } else if (listType == 3) {
                    System.out.println(disjointLists.peekLast().extractMin().getValue());
                    System.out.println(disjointLists.peekLast().toString());
                }
                break;

            case "union":
                if (listType == 1) {
                    if (sortedLists.size() == 1) {
                        System.out.println("User must create 2 heaps before unifying them");
                    } else {
                        while (sortedLists.size() != 1) {
                            sortedLists.addFirst(sortedLists.pollFirst().unify(sortedLists.pollFirst()));
                        }
                        System.out.println(sortedLists.peek().toString());
                    }
                } else if (listType == 2) {
                    if (notSortedLists.size() == 1) {
                        System.out.println("User must create 2 heaps before unifying them");
                    } else {
                        while (notSortedLists.size() != 1) {
                            notSortedLists.addFirst(notSortedLists.pollFirst().unify(notSortedLists.pollFirst()));
                        }
                        System.out.println(notSortedLists.peek().toString());
                    }
                } else if (listType == 3) {
                    if (disjointLists.size() == 1) {
                        System.out.println("User must create 2 heaps before unifying them");
                    } else {
                        while (disjointLists.size() != 1) {
                            disjointLists.addFirst((DisjointList) disjointLists.pollFirst().unify(disjointLists.pollFirst()));
                        }
                        System.out.println(disjointLists.peek().toString());
                    }
                }
                break;

            default:
                fileInit(line);
                break;
        }
    }
}
