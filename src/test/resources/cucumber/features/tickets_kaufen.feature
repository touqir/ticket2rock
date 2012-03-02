# language: de
Funktionalität: Konzerte suchen
  Damit ein Benutzer Karte für Konzerte kauft, muss er diese einfach finden können.
  
    Grundlage: Konzertsuche öffnen
    	Angenommen ich habe ein Konzert gefunden für das noch Tickets verfügbar sind und auf "Tickets" geklickt.   	
    	
    Szenariogrundriss: Illegale Ticketanzahlen
    	Wenn ich "<Anzahl>" als Ticketanzahl angebe und auf Bestellen drücke sehe ich diese Meldung: "<Fehlermeldung>".
    	
    	Beispiele: Illegale Werte 
    	  Positive Zahlen kleiner der Anzahl der vorrätigen Tickets sind gültig.
    	  
    	  | Anzahl | Fehlermeldung                                                                                               |
		  | 0      | Invalide Ticketanzahl                                                                                       |
		  | -1     | Invalide Ticketanzahl                                                                                       |
		  | drei   | ticketbestellungForm:ticketnumber: 'drei' must be a number between -2147483648 and 2147483647 Example: 9346 |
		  | 20000  | Diese Bestellung überschreitet das Ticketkontingent                                                         |
		      	  
    
    