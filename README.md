# Obligatorisk oppgave 3 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende student:
* Suzane Hamze
* s364759
* s364759@oslomet.no


# Oppgavebeskrivelse

Oppgave 1:
Koden er hentet fra kildekode 5.2.3.a. Programkoden bruker to nodereferanser p og q. 
Referansen p starter i rotnoden. Den flyttes nedover i treet-til venstre når verdi er mindre enn
nodeverdien og til høyre ellers. 
Sammenlignene utføres vha compare-metoden til komperatoren comp. Q skal legges et nivå over p, altså være forelder til p.
Når p blir null, vil q være den siste noden som ble passert. Deretter skal verdi legges inn som et barn til q.
Den siste verdien som compare-metoden returnerer, forteller om det skal være venstre eller høyre barn. 

Oppgave 2 fikk jeg inspirasjon fra kompendium 5.2.6 oppgave 2. I denne oppgaven skal 
jeg opprette metoden int antall(T verdier). Metoden returnerer antall forekomster av verdi. 
Her koder jeg ved å ta hensyn til når man søker mot høyre, venstre, eller når det er 0. 
For å gjøre det, må if setninger og while løkke lages. 

I oppgave 3 skrev jeg to metoder. Det krevde en del lesing fra kompendium kapittel 5.1.7- 
Første metode:
Starter metoden ved å sjekke om parameteren p ikke har gullverdier. Deretter lager jeg en
whileløkke med flere if statements. Statements tar for seg blant annet hva som skjer hvis
p sin venstre er ikke lik null, p sin høyre ikke er lik null.
Avslutter metoden ved å returnere parameteren p.

Andre metode:
Metoden skal returnere den noden som kommer etter p i postorden,
På samme måte som i forrige metode, lager jeg if statements med ulike tilfeller.
Blant annet hva som skjer dersom forelderen er null, dersom forelder til høyrebarn 
har verdien p, eller dersom det er forelder til høyrebarn er null. Avslutter metoden
ved å kalle på førstePostOrden som ellers. 

Oppgave 4
I den første metoden brukte jeg kildekode hentet fra kompendium 5.1.15 oppgave 1.
Metoden er programmert uten bruk av rekursjon og uten bruk av hjelpevariabler.
Den andre rekursive metoden, programkode 5.1.5 oppgave 7.  Eneste endringer implementert er at metodenavnet som kalles. 


