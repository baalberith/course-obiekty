package l5z1;

class ListNode implements Comparable <ListNode> {
    public String element;
    public ListNode next;
    
    public ListNode(String e, ListNode n) {
        element = e;
        next = n;
    }
    
    public ListNode(String e) {
        this(e, null);
    }

    public int compareTo(ListNode n) {
        return element.compareTo(n.element);
    }

    public String toString() {
        return element.toString();
    }
}

class OrderedList {
    private ListNode head;

    public OrderedList( ) {
        head = null;
    }

    public boolean empty( ) {
        return head == null;
    }

    public void put(String e) {
        if (head == null)
            head = new ListNode(e);
        else {
            ListNode node = new ListNode(e);
            ListNode curr = head, prev = null;

            while (curr != null && curr.compareTo(node) < 0) {
                prev = curr;
                curr = curr.next;
            }

            if (prev == null) {
                node.next = head;
                head = node;
            }
            else {
                prev.next = node;
                node.next = curr;
            }
        }
    }

    public String get() {
        String elem = head.element;
        head = head.next;

        return elem;
    }

    public void print() {
        ListNode curr = head;

        while (curr != null) {
            System.out.println(curr);
            curr = curr.next;
        }
    }
}

class Zolnierz {
    protected String nazwisko;

    public Zolnierz(String n) {
        nazwisko = n;
    }

    public String toString() {
        return nazwisko;
    }
}

class Szeregowy extends Zolnierz implements Comparable<Zolnierz> {
    public Szeregowy(String n) {
        super(n);
    }

    public int compareTo(Zolnierz z) {
        if (z instanceof Szeregowy)
            return 0;
        else
            return -1;
    }

    public String toString() {
        return "szeregowy " + super.toString();
    }
}

class Kapral extends Zolnierz implements Comparable<Zolnierz> {
    public Kapral(String n) {
        super(n);
    }

    public int compareTo(Zolnierz z) {
        if (z instanceof Kapral)
            return 0;
        else if (z instanceof Szeregowy)
            return 1;
        else
            return -1;
    }

    public String toString() {
        return "kapral " + super.toString();
    }
}

class Sierzant extends Zolnierz implements Comparable<Zolnierz> {
    public Sierzant(String n) {
        super(n);
    }

    public int compareTo(Zolnierz z) {
        if (z instanceof Sierzant)
            return 0;
        else {
            Kapral k = new Kapral("");
            if (k.compareTo(z) >= 0)
                return 1;
            else
                return -1;
        }
    }

    public String toString() {
        return "sierzant " + super.toString();
    }
}

class Chorazy extends Zolnierz implements Comparable<Zolnierz> {
    public Chorazy(String n) {
        super(n);
    }

    public int compareTo(Zolnierz z) {
        if (z instanceof Chorazy)
            return 0;
        else {
            Sierzant s = new Sierzant("");
            if (s.compareTo(z) >= 0)
                return 1;
            else
                return -1;
        }
    }

    public String toString() {
        return "chorazy " + super.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        OrderedList list = new OrderedList();

        list.put("Ola");
        list.put("Ala");
        list.put("Maja");
        list.put("Kasia");
        list.put("Monika");

        list.print();

        while (!list.empty()) {
            System.out.println(list.get());
        }

        Szeregowy kowalski = new Szeregowy("Kowalski");
        Szeregowy kaczynski = new Szeregowy("Kaczynski");
        Kapral burzynski = new Kapral("Burzynski");
        Sierzant kaminski = new Sierzant("Kaminski");
        Chorazy maczynski = new Chorazy("Maczynski");

        System.out.println(kaczynski.compareTo(burzynski));
        System.out.println(kowalski.compareTo(kaczynski));
        System.out.println(burzynski.compareTo(kowalski));

        if (kaminski.compareTo(kaczynski) > 0) {
            System.out.print(kaminski);
            System.out.print(" ma wyzszy stopien wojskowy niz ");
            System.out.println(kaczynski);
        }
        
        if (kaminski.compareTo(maczynski) < 0) {
            System.out.print(kaminski);
            System.out.print(" ma nizszy stopien wojskowy niz ");
            System.out.println(maczynski);
        }
    }
}
