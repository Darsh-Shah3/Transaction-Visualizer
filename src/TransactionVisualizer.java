import java.util.Scanner;

public class TransactionVisualizer {
    class Node1 {
        int data;
        boolean status;
        Node1 next;

        public Node1(int data) {
            this.data = data;
            this.status = false;
            this.next = null;
        }
    }

    class Node {
        Node next1, next2;
        String data;

        public Node(String data) {
            this.data = data;
            this.next1 = this.next2 = null;
        }
    }

    static TransactionVisualizer t1 = new TransactionVisualizer();
    static TransactionVisualizer t2 = new TransactionVisualizer();
    static TransactionVisualizer t3 = new TransactionVisualizer();

    Node head;
    Node1 head1, tail1;
    int count;

    public TransactionVisualizer() {
        this.head1 = this.tail1 = null;
    }

    // funtion to add doubly linked list
    public void add(int data) {
        // case for head1 as null value(initial point)
        if (head1 == null) {
            head1 = tail1 = new Node1(data);
            return;
        }

        // case when head1 is not null so moving tail1
        Node1 newNode1 = new Node1(data);
        tail1.next = newNode1;
        tail1 = newNode1;
    }

    // function to remove last added element in doubly linked list
    public int remove() {
        // no element case
        if (head1 == null) {
            return Integer.MIN_VALUE;
        }

        // single element case
        if (head1 == tail1) {
            int val = tail1.data;
            head1 = tail1 = null;
            return val;
        }

        // more than one element case
        Node1 temp = head1;
        while (temp.next != tail1) {
            temp = temp.next;
        }
        int val = temp.next.data;
        tail1 = temp;
        return val;
    }

    public void savePoint() {
        if (head1 == null) {
            return;
        }

        tail1.status = true;
        count++;
    }

    public void rollBack(int count) {
        if (count > this.count) {
            System.out.println("This operation is certainly not possible due to un-even number of savepoints created!!");
        }

        Node1 temp = head1;
        int i = 0;
        while (i != count) {
            temp = temp.next;
            if (temp.status)
                i++;
        }

        tail1 = temp;
        tail1.next = null;
        this.count = count;
    }

    public void makeTransaction() {
        Scanner sc = new Scanner(System.in);
        Scanner st = new Scanner(System.in);
        System.out.print("Enter the highest transaction length : ");
        int length = sc.nextInt();
        int i = 0;
        Node temp1 = t1.head;
        Node temp2 = t2.head;
        Node temp3 = t3.head;

        while (i != length) {
            System.out.println("Please select lists from the following to take transaction forward ");
            System.out.println(" 1.List1 \n 2.List2 \n 3.List3");
            System.out.print("Enter a valid choice : ");
            int n = sc.nextInt();

            switch (n) {
                case 1: {
                    System.out.print("Enter data : ");
                    String data = st.nextLine();
                    if (t1.head == null && t2.head == null && t3.head == null) {
                        temp1 = t1.head = new Node(data);
                        temp2 = t2.head = new Node("-");
                        temp3 = t3.head = new Node("-");
                        break;
                    }

                    temp1.next1 = new Node(data);
                    temp2.next1 = new Node("-");
                    temp3.next1 = new Node("-");
                    temp1 = temp1.next1;
                    temp2 = temp2.next1;
                    temp3 = temp3.next1;
                    System.out.println();
                    break;
                }

                case 2: {
                    System.out.print("Enter data : ");
                    String data = st.nextLine();
                    if (t1.head == null && t2.head == null && t3.head == null) {
                        temp2 = t2.head = new Node(data);
                        temp1 = t1.head = new Node("-");
                        temp3 = t3.head = new Node("-");
                        break;
                    }

                    temp2.next1 = new Node(data);
                    temp3.next1 = new Node("-");
                    temp1.next1 = new Node("-");
                    temp1 = temp1.next1;
                    temp2 = temp2.next1;
                    temp3 = temp3.next1;
                    System.out.println();
                    break;
                }

                case 3: {
                    System.out.print("Enter data : ");
                    String data = st.nextLine();
                    if (t1.head == null && t2.head == null && t3.head == null) {
                        temp3 = t3.head = new Node(data);
                        temp2 = t2.head = new Node("-");
                        temp1 = t1.head = new Node("-");
                        break;
                    }

                    temp3.next1 = new Node(data);
                    temp2.next1 = new Node("-");
                    temp1.next1 = new Node("-");
                    temp1 = temp1.next1;
                    temp2 = temp2.next1;
                    temp3 = temp3.next1;
                    System.out.println();
                    break;
                }
            }
            i++;
        }
        sc.close();
        st.close();
        makeCycle();
    }

    private void makeCycle() {
        TransactionVisualizer finalList = new TransactionVisualizer();
        Node T1 = finalList.head = new Node("0");
        Node T2 = new Node("0");
        Node T3 = new Node("0");
        Node temp1 = t1.head;
        Node temp2 = t2.head;
        Node temp3 = t3.head;

        // for list 1
        while (temp1.next1 != null) {
            if (temp1.data.equals("R(x)") || temp1.data.equals("R(y)") || temp1.data.equals("R(z)")) {
                while (temp2 != null) {
                    temp2 = temp2.next1;
                    temp3 = temp3.next1;
                    if (temp1.data.equals("R(x)")) {
                        if (temp2 != null && temp2.data.equals("W(x)")) {
                            T1.next1 = T2;
                        }
                        if (temp3 != null && temp3.data.equals("W(x)")) {
                            T1.next2 = T3;
                        }
                    }
                    if (temp1.data.equals("R(y)")) {
                        if (temp2 != null && temp2.data.equals("W(y)")) {
                            T1.next1 = T2;
                        }
                        if (temp3 != null && temp3.data.equals("W(y)")) {
                            T1.next2 = T3;
                        }
                    }
                    if (temp1.data.equals("R(z)")) {
                        if (temp2 != null && temp2.data.equals("W(z)")) {
                            T1.next1 = T2;
                        }
                        if (temp3 != null && temp3.data.equals("W(z)")) {
                            T1.next2 = T3;
                        }
                    }
                }
            }

            if (temp1.data.equals("W(x)") || temp1.data.equals("W(y)") || temp1.data.equals("W(z)")) {
                while (temp2 != null) {
                    temp2 = temp2.next1;
                    temp3 = temp3.next1;
                    if (temp1.data.equals("W(x)")) {
                        if (temp2 != null && (temp2.data.equals("W(x)") || temp2.data.equals("R(x)"))) {
                            T1.next1 = T2;
                        }
                        if (temp3 != null && (temp3.data.equals("W(x)") || temp3.data.equals("R(x)"))) {
                            T1.next2 = T3;
                        }
                    }
                    if (temp1.data.equals("W(y)")) {
                        if (temp2 != null && (temp2.data.equals("W(y)") || temp2.data.equals("R(y)"))) {
                            T1.next1 = T2;
                        }
                        if (temp3 != null && (temp3.data.equals("W(y)") || temp3.data.equals("R(y)"))) {
                            T1.next2 = T3;
                        }
                    }
                    if (temp1.data.equals("W(z)")) {
                        if (temp2 != null && (temp2.data.equals("W(z)") || temp2.data.equals("R(z)"))) {
                            T1.next1 = T2;
                        }
                        if (temp3 != null && (temp3.data.equals("W(z)") || temp3.data.equals("R(z)"))) {
                            T1.next2 = T3;
                        }
                    }
                }
            }
            temp1 = temp1.next1;
            if (temp2 != null && temp3 != null) {
                temp2 = temp2.next1;
                temp3 = temp3.next1;
            }
        }

        temp1 = t1.head;
        temp2 = t2.head;
        temp3 = t3.head;

        // for list2
        while (temp2.next1 != null) {
            if (temp2.data.equals("R(x)") || temp2.data.equals("R(y)") || temp2.data.equals("R(z)")) {
                while (temp1 != null) {
                    temp1 = temp1.next1;
                    temp3 = temp3.next1;
                    if (temp2.data.equals("R(x)")) {
                        if (temp1 != null && temp1.data.equals("W(x)")) {
                            T2.next1 = T1;
                        }
                        if (temp3 != null && temp3.data.equals("W(x)")) {
                            T2.next2 = T3;
                        }
                    }
                    if (temp2.data.equals("R(y)")) {
                        if (temp1 != null && temp1.data.equals("W(y)")) {
                            T2.next1 = T1;
                        }
                        if (temp3 != null && temp3.data.equals("W(y)")) {
                            T2.next2 = T3;
                        }
                    }
                    if (temp2.data.equals("R(z)")) {
                        if (temp1 != null && temp1.data.equals("W(z)")) {
                            T2.next1 = T1;
                        }
                        if (temp3 != null && temp3.data.equals("W(z)")) {
                            T2.next2 = T3;
                        }
                    }
                }
            }

            if (temp2.data.equals("W(x)") || temp2.data.equals("W(y)") || temp2.data.equals("W(z)")) {
                while (temp1 != null) {
                    temp1 = temp1.next1;
                    temp3 = temp3.next1;
                    if (temp2.data.equals("W(x)")) {
                        if (temp3 != null && (temp1.data.equals("W(x)") || temp1.data.equals("R(x)"))) {
                            T2.next1 = T1;
                        }
                        if (temp3 != null && (temp3.data.equals("W(x)") || temp3.data.equals("R(x)"))) {
                            T2.next2 = T3;
                        }
                    }
                    if (temp2.data.equals("W(y)")) {
                        if (temp3 != null && (temp1.data.equals("W(y)") || temp1.data.equals("R(y)"))) {
                            T2.next1 = T1;
                        }
                        if (temp3 != null && (temp3.data.equals("W(y)") || temp3.data.equals("R(y)"))) {
                            T2.next2 = T3;
                        }
                    }
                    if (temp2.data.equals("W(z)")) {
                        if (temp3 != null && (temp1.data.equals("W(z)") || temp1.data.equals("R(z)"))) {
                            T2.next1 = T1;
                        }
                        if (temp3 != null && (temp3.data.equals("W(z)") || temp3.data.equals("R(z)"))) {
                            T2.next2 = T3;
                        }
                    }
                }
            }

            temp2 = temp2.next1;
            if (temp1 != null && temp3 != null) {
                temp1 = temp1.next1;
                temp3 = temp3.next1;
            }
        }

        temp1 = t1.head;
        temp2 = t2.head;
        temp3 = t3.head;

        // for list3
        while (temp3.next1 != null) {
            if (temp3.data.equals("R(x)") || temp3.data.equals("R(y)") || temp3.data.equals("R(z)")) {
                while (temp1 != null) {
                    temp1 = temp1.next1;
                    temp2 = temp2.next1;
                    if (temp3.data.equals("R(x)")) {
                        if (temp2 != null && temp2.data.equals("W(x)")) {
                            T3.next1 = T2;
                        }
                        if (temp1 != null && temp1.data.equals("W(x)")) {
                            T3.next2 = T2;
                        }
                    }
                    if (temp3.data.equals("R(y)")) {
                        if (temp2 != null && temp2.data.equals("W(y)")) {
                            T3.next1 = T2;
                        }
                        if (temp1 != null && temp1.data.equals("W(y)")) {
                            T3.next2 = T1;
                        }
                    }
                    if (temp3.data.equals("R(z)")) {
                        if (temp2 != null && temp2.data.equals("W(z)")) {
                            T3.next1 = T2;
                        }
                        if (temp1 != null && temp1.data.equals("W(z)")) {
                            T3.next2 = T1;
                        }
                    }
                }
            }

            if (temp3.data.equals("W(x)") || temp3.data.equals("W(y)") || temp3.data.equals("W(z)")) {
                while (temp2 != null) {
                    temp2 = temp2.next1;
                    temp1 = temp1.next1;
                    if (temp3.data.equals("W(x)")) {
                        if (temp2 != null && (temp2.data.equals("W(x)") || temp2.data.equals("R(x)"))) {
                            T3.next1 = T2;
                        }
                        if (temp1 != null && (temp1.data.equals("W(x)") || temp1.data.equals("R(x)"))) {
                            T3.next2 = T1;
                        }
                    }
                    if (temp3.data.equals("W(y)")) {
                        if (temp2 != null && (temp2.data.equals("W(y)") || temp2.data.equals("R(y)"))) {
                            T1.next1 = T2;
                        }
                        if (temp1 != null && (temp1.data.equals("W(y)") || temp1.data.equals("R(y)"))) {
                            T3.next2 = T1;
                        }
                    }
                    if (temp3.data.equals("W(z)")) {
                        if (temp2 != null && (temp2.data.equals("W(z)") || temp2.data.equals("R(z)"))) {
                            T1.next1 = T2;
                        }
                        if (temp1 != null && (temp1.data.equals("W(z)") || temp1.data.equals("R(z)"))) {
                            T3.next2 = T1;
                        }
                    }
                }
            }
            if (temp1 != null && temp2 != null) {
                temp1 = temp1.next1;
                temp2 = temp2.next1;
            }

            temp3 = temp3.next1;
        }
        validateCycle(T1, T2, T3);
    }

    private void validateCycle(Node T1, Node T2, Node T3) {
        t1.printList();
        t2.printList();
        t3.printList();
        System.out.println();
        if ((T1.next1 == T2 || T1.next2 == T2) && (T2.next1 == T1 || T2.next2 == T1)) {
            System.out.println("The following transactions are non Conflict Serializable!!");
            return;
        }

        if ((T1.next1 == T3 || T1.next2 == T3) && (T3.next1 == T1 || T3.next2 == T1)) {
            System.out.println("The following transactions are non Conflict Serializable!!");
            return;
        }

        if ((T2.next1 == T3 || T2.next2 == T3) && (T3.next1 == T2 || T3.next2 == T2)) {
            System.out.println("The following transactions are non Conflict Serializable!!");
            return;
        }

        if ((T1.next1 == T2 || T1.next2 == T2) && (T2.next1 == T3 || T2.next2 == T3)
                && (T3.next1 == T2 || T3.next2 == T2)) {
            System.out.println("The following transactions are non Conflict Serializable!!");
            return;
        }

        System.out.println("The following transactions are Conflict Serializable!!");
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " --> ");
            temp = temp.next1;
        }
        System.out.println(" null");

    }

    public void display() {
        Node1 temp = head1;
        System.out.print("null <-- ");
        while (temp.next != null) {

            System.out.print(temp.status ? temp.data + " <.--> " : temp.data + " <--> ");
            temp = temp.next;
        }

        System.out.print(temp.status ? temp.data + " .--> null " : temp.data + " --> null ");
    }

    public static void main(String[] args) {
        TransactionVisualizer list1 = new TransactionVisualizer();

        outer: while (true) {
            Scanner sa = new Scanner(System.in);
            System.out.println(" 1.Transaction Visualizer \n 2.Transaction Serializability Visualizer \n 3.Exit");
            System.out.print("Enter a valid choice : ");
            int choice = sa.nextInt();

            switch (choice) {
                case 1: {
                    inner: while (true) {
                        System.out.println(
                                " 1.Add Node \n 2.Remove Node \n 3.Savepoint \n 4.Rollback \n 5.Commit \n 6.Display \n 7.Exit");
                        System.out.print("Enter a valid choice : ");
                        int c = sa.nextInt();
                        switch (c) {
                            case 1: {
                                System.out.println();
                                System.out.print("Enter data : ");
                                int data = sa.nextInt();
                                list1.add(data);
                                System.out.println();
                                break;
                            }

                            case 2: {
                                System.out.println();
                                int data = list1.remove();
                                System.out.println("Removed data : " + data);
                                System.out.println();
                                break;
                            }

                            case 3: {
                                System.out.println();
                                list1.savePoint();
                                System.out.println("Successfully created a savepoint!!");
                                System.out.println();
                                break;
                            }

                            case 4: {
                                System.out.println();
                                System.out.print("Enter the specific count of savepoint to reach it : ");
                                int count = sa.nextInt();
                                list1.rollBack(count);
                                System.out.println();
                                break;
                            }

                            case 5: {
                                System.out.println();
                                list1.display();
                                break inner;
                            }

                            case 6: {
                                list1.display();
                                System.out.println();
                                break;
                            }

                            case 7: {
                                System.out.println("Thank you!!");
                                break inner;
                            }

                            default: {
                                System.out.println("Invalid choice! Please enter again!!");
                            }
                        }
                    }
                    break;
                }

                case 2: {
                    System.out.println();
                    list1.makeTransaction();
                    System.out.println();
                    break;
                }

                case 3: {
                    System.out.println("Thank you!!");
                    sa.close();
                    break outer;
                }

                default: {
                    System.out.println("Invalid choice! Please enter again!!");
                }
            }
        }
    }
}