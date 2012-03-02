# language: de
Funktionalität: Konzerte suchen
  Damit ein Benutzer Karte für Konzerte kauft, muss er diese einfach finden können.
  
    Grundlage: Konzertsuche öffnen
    	Angenommen ich öffne als User die Ticket2Rock-Seite.
    	Wenn ich die Konzertsuche öffne.
    	
    Szenario: Suche mit Ortsangabe
    	Wenn ich als Ort "AOL Arena" eingebe.
    	Dann sehe ich das folgende Ergebnis:
    	  |Ort 		|Location 	|Datum 	    |Interpret 	           |Tournee 	           |Ticketpreis |Aktion      |
    	  |Hamburg 	|AOL Arena 	|30.06.2007	|Red Hot Chili Peppers |Stadium Arcadium Tour  |€60.00 	    |ausverkauft |
    
    