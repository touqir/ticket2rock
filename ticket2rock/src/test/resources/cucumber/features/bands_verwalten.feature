# language: de
Funktionalität: Bands verwalten
  Damit Konzerte von neuen Bands gepflegt werden können, müssen diese einfach verwaltbar sein.

  Grundlage: Seite öffnen
    Angenommen ich öffne als User die Ticket2Rock-Seite.
    Wenn ich die Bandverwaltung öffne.
    
    
  Szenario: Band ohne Namen können nicht angelegt werden.
  	Und auf "Neue Band anlegen" klicke.
  	Und ich einer Band keinen Namen gebe.
  	Und auf Speichern klicke.
  	Dann erscheint die Fehlermeldung "bandForm:bandname: Validation Error: Value is required.".
  
  Szenario: Band anlegen.
    Und auf "Neue Band anlegen" klicke.
  	Und der Band einen Namen gebe.
  	Und einen Künstler hinzufüge.
  	Und auf Speichern klicke.
  	Dann sehe ich auf der Bandverwaltungsseite eine Band mit diesem Namen.
  	
  Szenario: Band löschen
  	Wenn ich bei einer Band auf "Löschen" klicke.
  	Dann ist die Band nicht mehr in der Liste.
  	
  	