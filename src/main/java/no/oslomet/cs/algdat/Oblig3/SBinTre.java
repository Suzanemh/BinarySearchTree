package no.oslomet.cs.algdat.Oblig3;


import java.util.*;

public class SBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public SBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    //Oppgave 1
    //Kildekode fra kompendium 5.2.3.a
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        Node<T> p = rot, q = null;               // p starter i roten
        int cmp = 0;                             // hjelpevariabel

        while (p != null)       // fortsetter til p er ute av treet
        {
            q = p;                                 // q er forelder til p
            cmp = comp.compare(verdi,p.verdi);     // bruker komparatoren
            p = cmp < 0 ? p.venstre : p.høyre;     // flytter p
        }

        // p er nå null, dvs. ute av treet, q er den siste vi passerte

        p = new Node<>(verdi, q);                   // oppretter en ny node, med parameter verdi og q

        if (q == null) rot = p;                  // p blir rotnode
        else if (cmp < 0) q.venstre = p;         // venstre barn til q
        else q.høyre = p;                        // høyre barn til q

        antall++;                                // én verdi mer i treet
        endringer++;                               //legger til endringer ++
        return true;                             // vellykket innlegging
    }

    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int fjernAlle(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    //Oppgave 2
    //Kildekode hentet fra Avsnitt 5.2.6 oppgave 2
    public int antall(T verdi) { //metoden

        Node<T> p = rot; //starter i roten
        int antallVerdi = 0; //hjelpevariabel int antallVerdi som starter på 0

        while(p !=null){ //while løkke der p ikke er null. Her sjekker vi p
            int cmp = comp.compare(verdi, p.verdi); //sammenligning

            if( cmp < 0){       //hvis hjelpevariabelen cmp er mindre enn 0
                p = p.venstre;  //går til venstre

            } else if(cmp > 0) { //cmp større enn 0
                p = p.høyre; //går til høyre

            } else {
                p = p.høyre;
                antallVerdi++; //økning
            }
        }
        return antallVerdi; //retur statement
    }

    public void nullstill() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    //Oppgave 3
    //Kildekode inspirert fra kompendium 5.1.7.h, men la til andre endringer fo å oppfylle oppgave kravet

    //Skal returnere første node post orden med p som rot
    private static <T> Node<T> førstePostorden(Node<T> p) {

        //skjekker at parameteren p ikke er null pga private metode
        Objects.requireNonNull(p); //skjekker om  ikke er nullverdi

        while(true){

            if (p.venstre !=null){ //hvis p sin venstre ikke er lik null
                p = p.venstre; //går til venstre. Venstrebarn til p

            } else if       //annen statement
            (p.høyre != null){  //hvis p sin høyre ikke er lik null
                p = p.høyre;    //går til høyre. Høyrebarn til p
            } else {            //ellers
                return p;
            }
        }
    }

    //Oppgave 3
    //nestePostOrden skal returnere den noden som kommer etter p i postorden
    //følgende kildekode er inspirert fra 5.1.15.b. Forskjellen er at her tar jeg hensyn
    //til PostOrden, mens kompendium ser på InnOrden

    private static <T> Node<T> nestePostorden(Node<T> p) {

        if (p.forelder == null) { //dersom forelderen er null. Ikke noe nestePostOrden
            return null;            //returnerer null
        }

        if(p.forelder.høyre == p || p.forelder.høyre == null){ //krav
            return p.forelder;  //returnerer foreldre dersom høyrebarm er verdien p, eller returnerer null hvis tomt.
        } else {
            return førstePostorden(p.forelder.høyre); //Kaller på førstPostOrden  ellers
        }
    }

    //Oppgave 4- uten bruk av rekursjon og uten bruk av hjelpevariabel

    //Kildekoden under er hentet fra kompendium avsnitt 5.1.15 oppgave 1

    public void postorden(Oppgave<? super T> oppgave) {
      //  if(rot !=null) postorden(rot, oppgave);
        if (tom()) return;  // tomt tre

        Node<T> p = rot;

        while (true) // flytter p til den første i postorden
        {
            if (p.venstre != null) p = p.venstre;
            else if (p.høyre != null) p = p.høyre;
            else break;
        }

        oppgave.utførOppgave(p.verdi);

        while (true)
        {
            if (p == rot) break;  // den siste i postorden

            Node<T> f = p.forelder;
            if (f.høyre == null || p == f.høyre) p = f;
            else
            {
                p = f.høyre;
                while (true)
                {
                    if (p.venstre != null) p = p.venstre;
                    else if (p.høyre != null) p = p.høyre;
                    else break;
                }
            }
            oppgave.utførOppgave(p.verdi);
        }
    }


    //Oppgave 4
    public void postordenRecursive(Oppgave<? super T> oppgave) {
        if(rot != null) { //Sjekker om treet er tomt
            postordenRecursive(rot, oppgave);
        }
    }

    //Oppgave 4- rekursiv metode
    //Inspirert av kildekode i kompendium 5.1.7 oppgave 7
    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        if (p.venstre != null) postordenRecursive(p.venstre,oppgave);
        if (p.høyre != null) postordenRecursive(p.høyre,oppgave);
        oppgave.utførOppgave(p.verdi);
    }

    public ArrayList<T> serialize() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }


} // ObligSBinTre
