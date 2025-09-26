# w3d5exam



Come ho ragionato? In primo luogo ci sarà un ElementoMultimediale classe astratta che sarà poi estesa da Libri e Riviste. Queste erediteranno anche la pk di ElementoMultimediale (il codice isbn) (1 to 1). Elemento multimediale sarà connesso a prestito in una relazione Many to One, questo perché ogni prestito riguarda un libro o una rivista ma ognuna di queste due cose può essere prestata più volte in momenti diversi. Utente sarà connesso a prestito in una relazione 1 to many perché ogni utente potrà avere più prestiti ma ogni prestito sarà di un singolo utente. La strategia che ho scelto è la joined table perché è quella che ricordo meglio delle due utilizzabili. Scherzo, credo che sia meglio per la quantità di attributi in comune, per evitare di avere ridondanza nei dati e per effettuare ricerche più semplici sugli attributi in comune.

