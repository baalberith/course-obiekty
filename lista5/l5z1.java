package l5z1;

class Node<T extends Comparable<T>> {
    private T elem;
    private Node<T> next;

    Node(T e) {
        elem = e;
    }

    Node(T e, Node n) {
        elem = e;
        next = n;
    }

    public Node<T> insert(T e) {
        if(e.compareTo(elem) < 0)
            return new Node(e, this);
        else
            if(next == null)
                next = new Node(e);
            else
                next = next.insert(e);

        return this;
    }

    public Node remove() {
        return next;
    }

    public T get() {
        return elem;
    }

    @Override
    public String toString() {
        if(next == null)
            return elem.toString();
        else
            return elem.toString() + ", " + next.toString();
    }
}

class Colection<T extends Comparable<T>> {
    Node<T> head;

    public void insert(T e) {
        if(head == null)
            head = new Node(e);
        else
            head = head.insert(e);
    }

    public T get() {
        T e = (T) head.get();
        head = head.remove();

        return e;
    }

    @Override
    public String toString() {
        if (head == null)
            return "";
        else
            return head.toString();
    }
}

class Value<T extends Comparable<T>>  implements Comparable<Value<T>> {
    protected int valueClass;
    protected T value;

    public int compareTo(Value<T> v) {
        if(valueClass == v.getValueClass())
            return value.compareTo(v.getValue());
        else
            return valueClass - v.getValueClass();
    }

    public int getValueClass() {
        return valueClass;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + value;
    }
}

class Szeregowy extends Value {
    public Szeregowy(String nazwisko) {
        value = nazwisko;
        valueClass = 100;
    }
}

class Kapral extends Value {
    public Kapral(String nazwisko) {
        value = nazwisko;
        valueClass = 200;
    }
}

class Sierzant extends Value {
    public Sierzant(String nazwisko) {
        value = nazwisko;
        valueClass = 300;
    }
}

class Chorazy extends Value {
    public Chorazy(String nazwisko) {
        value = nazwisko;
        valueClass = 400;
    }
}

public class Main {
    public static void main(String[] args) {
        Colection<Value<String>> col = new Colection();

        col.insert(new Chorazy("Kowalski"));
        col.insert(new Szeregowy("Makowski"));
        col.insert(new Sierzant("Kaczynski"));
        col.insert(new Kapral("Burzynski"));
        col.insert(new Sierzant("Kaminski"));

        System.out.println(col.toString());
    }
}

