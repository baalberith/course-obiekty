package l5z3;
import java.util.Hashtable;

abstract class Wyrazenie {
    abstract public int oblicz();
}

class Stala extends Wyrazenie {
    private int wartosc;

    public Stala(int w) {
        wartosc = w;
    }

    public int oblicz() {
        return wartosc;
    }
}

class Zmienna extends Wyrazenie {
    private static Hashtable zmienne = new Hashtable();
    private String nazwa;

    public Zmienna(String n) {
        nazwa = n;
    }

    public int oblicz() {
        return (Integer)zmienne.get(nazwa);
    }

    public static void przypisz(String n, int e) {
        zmienne.put(n, e);
    }
}

class Dodawanie extends Wyrazenie {
    private Wyrazenie skladnik1, skladnik2;

    public Dodawanie(Wyrazenie s1, Wyrazenie s2) {
        skladnik1 = s1;
        skladnik2 = s2;
    }

    public int oblicz() {
        return skladnik1.oblicz() + skladnik2.oblicz();
    }
}

class Odejmowanie extends Wyrazenie {
    private Wyrazenie odjemna, odjemnik;

    public Odejmowanie(Wyrazenie o1, Wyrazenie o2) {
        odjemna = o1;
        odjemnik = o2;
    }

    public int oblicz() {
        return odjemna.oblicz() - odjemnik.oblicz();
    }
}

class Mnozenie extends Wyrazenie {
    private Wyrazenie czynnik1, czynnik2;

    public Mnozenie(Wyrazenie c1, Wyrazenie c2) {
        czynnik1 = c1;
        czynnik2 = c2;
    }

    public int oblicz() {
        return czynnik1.oblicz() * czynnik2.oblicz();
    }
}

class Dzielenie extends Wyrazenie {
    private Wyrazenie dzielna, dzielnik;

    public Dzielenie(Wyrazenie d1, Wyrazenie d2) {
        dzielna = d1;
        dzielnik = d2;
    }

    public int oblicz() {
        return dzielna.oblicz() / dzielnik.oblicz();
    }
}

public class Main {
    public static void main(String[] args) {
        Stala s1 = new Stala(1),
                s2 = new Stala(2);

        Zmienna z1 = new Zmienna("z1"),
                z2 = new Zmienna("z2");
        
        Wyrazenie w1 = new Dodawanie(z1, s1),
                w2 = new Mnozenie(s2, z2);

        Zmienna.przypisz("z1", 100);
        Zmienna.przypisz("z2", 50);

        Wyrazenie wynik = new Dzielenie(new Stala(666), new Odejmowanie(w1, w2));

        int wartosc = wynik.oblicz();
        System.out.println(wartosc);
    }

}
