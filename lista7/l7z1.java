package l7z1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.*;

class Ksiazka implements Serializable {
    public String tytul;
    public String autor;
    public int wydanie;

    public Ksiazka(String t, String a, int w) {
        tytul = t;
        autor = a;
        wydanie = w;
    }

    public Ksiazka() {
        this("Tytuł", "Autor", 0);
    }

    @Override
    public String toString() {
        return "Książka " + tytul + " " + autor;
    }
}

class WydawnictwoCiagle extends Ksiazka {
    public WydawnictwoCiagle(String t, String a, int w) {
        super(t, a, w);
    }

    public WydawnictwoCiagle() {
        super();
    }

    @Override
    public String toString() {
        return "Wydawnictwo ciągłe " + tytul + " " + autor;
    }
}

class Czasopismo extends Ksiazka {
    public Czasopismo(String t, String a, int w) {
        super(t, a, w);
    }

    public Czasopismo() {
        super();
    }

    @Override
    public String toString() {
        return "Czasopismo " + tytul + " " + autor;
    }
}

class EdycjaKsiazki extends JFrame  {
    private Ksiazka ksiazka = new Ksiazka();
    protected String nazwa_pliku;

    protected JTextField tytul;
    protected JTextField autor;
    protected JTextField wydanie;

    public EdycjaKsiazki(String nazwa, String autor_label) {
        nazwa_pliku = nazwa;

        tytul = new JTextField(10);
        autor = new JTextField(10);
        wydanie = new JTextField(2);

        JButton zapisz = new JButton("Zapisz");
        zapisz.addActionListener(new ZapiszListener());

        JButton resetuj = new JButton("Resetuj");
        resetuj.addActionListener(new ResetujListener());

        JButton doPliku = new JButton("Zapisz do pliku");
        doPliku.addActionListener(new ZapiszDoPlikuListener());

        JButton zPliku = new JButton("Wczytaj z pliku");
        zPliku.addActionListener(new WczytajZPlikuListener());

        JPanel zawartość = new JPanel();
        zawartość.setLayout(new GridLayout(5, 2));

        zawartość.add(new JLabel("Tytuł"));
        zawartość.add(tytul);

        zawartość.add(new JLabel(autor_label));
        zawartość.add(autor);

        zawartość.add(new JLabel("Wydanie"));
        zawartość.add(wydanie);
        
        zawartość.add(zapisz);
        zawartość.add(resetuj);
        zawartość.add(doPliku);
        zawartość.add(zPliku);

        setContentPane(zawartość);
        pack();
        setTitle("Edycja książki");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            FileInputStream fs = new FileInputStream(nazwa_pliku);
            ObjectInputStream os = new ObjectInputStream(fs);
            ksiazka = (Ksiazka) os.readObject();

            tytul.setText(ksiazka.tytul);
            autor.setText(ksiazka.autor);
            wydanie.setText(Integer.toString(ksiazka.wydanie));
        } catch (FileNotFoundException ex) {
            File plik = new File(nazwa_pliku);
            try {
                plik.createNewFile();
            } catch (Exception _) {

            }
        } catch (Exception _) {
            
        }
    }

    class ZapiszListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ksiazka.tytul = tytul.getText();
            ksiazka.autor = autor.getText();
            ksiazka.wydanie = Integer.parseInt(wydanie.getText());
        }
    }

    class ResetujListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            tytul.setText(ksiazka.tytul);
            autor.setText(ksiazka.autor);
            wydanie.setText(Integer.toString(ksiazka.wydanie));
        }
    }

    class ZapiszDoPlikuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                FileOutputStream fs = new FileOutputStream(nazwa_pliku);
                ObjectOutputStream os = new ObjectOutputStream(fs);
                os.writeObject(ksiazka);
                os.close();
            } catch (Exception _) {
                
            }
        }
    }

    class WczytajZPlikuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                FileInputStream fs = new FileInputStream(nazwa_pliku);
                ObjectInputStream os = new ObjectInputStream(fs);
                ksiazka = (Ksiazka) os.readObject();

                tytul.setText(ksiazka.tytul);
                autor.setText(ksiazka.autor);
                wydanie.setText(Integer.toString(ksiazka.wydanie));
            } catch (FileNotFoundException ex) {
                File plik = new File(nazwa_pliku);
                try {
                    plik.createNewFile();
                } catch (Exception _) {
                    
                }
            } catch (Exception _) {
                
            }
        }
    }
}

class EdycjaWydawnictwaCiaglego extends EdycjaKsiazki {
    private WydawnictwoCiagle ksiazka = new WydawnictwoCiagle();

    public EdycjaWydawnictwaCiaglego(String nazwa, String autor_label) {
        super(nazwa, autor_label);
        setTitle("Edycja wydawnictwa ciągłego");
    }

    class WczytajZPlikuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                FileInputStream fs = new FileInputStream(nazwa_pliku);
                ObjectInputStream os = new ObjectInputStream(fs);
                ksiazka = (WydawnictwoCiagle) os.readObject();

                tytul.setText(ksiazka.tytul);
                autor.setText(ksiazka.autor);
                wydanie.setText(Integer.toString(ksiazka.wydanie));
            } catch (Exception _) {
                
            }
        }
    }
}

class EdycjaCzasopisma extends EdycjaKsiazki {
    private Czasopismo ksiazka = new Czasopismo();

    public EdycjaCzasopisma(String nazwa, String autor_label) {
        super(nazwa, autor_label);
        setTitle("Edycja czasopisma");
    }

    class WczytajZPlikuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                FileInputStream fs = new FileInputStream(nazwa_pliku);
                ObjectInputStream os = new ObjectInputStream(fs);
                ksiazka = (Czasopismo) os.readObject();

                tytul.setText(ksiazka.tytul);
                autor.setText(ksiazka.autor);
                wydanie.setText(Integer.toString(ksiazka.wydanie));
            } catch (Exception _) {
                
            }
        }
    }
}

class Main {
    public static void main(String[] args) {
        String nazwa_pliku = args[0];
        String nazwa_klasy_obiektu = args[1];

        if (nazwa_klasy_obiektu.equals("Ksiazka")) {
            EdycjaKsiazki okno = new EdycjaKsiazki(nazwa_pliku, "Autor");
            okno.setVisible(true);
        }
        else if (nazwa_klasy_obiektu.equals("WydawnictwoCiagle")) {
            EdycjaWydawnictwaCiaglego okno = new EdycjaWydawnictwaCiaglego(nazwa_pliku, "Wydawca");
            okno.setVisible(true);
        }
        else if (nazwa_klasy_obiektu.equals("Czasopismo")) {
            EdycjaCzasopisma okno = new EdycjaCzasopisma(nazwa_pliku, "Wydawca");
            okno.setVisible(true);
        }
        
    }
}