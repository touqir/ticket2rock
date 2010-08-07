/*
	Ticket2Rock ist die Beispielanwendung des Buchs "EJB 3 professionell" (dpunkt).
	Es implementiert eine einfache Webanwendung zur Onlinebuchung von Tickets für
	Rockkonzerte auf Basis von EJB 3.0 und JavaServer Faces.
	
	Copyright (C) 2006
	Jo Ehm, Dierk Harbeck, Stefan M. Heldt, Oliver Ihns, Jochen Jörg, Holger Koschek,
	Carsten Sahling, Roman Schloemmer
	
	This program is free software; you can redistribute it and/or
	modify it under the terms of the GNU General Public License
	as published by the Free Software Foundation; either version 2
	of the License, or (at your option) any later version.
	
	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.
	
	You should have received a copy of the GNU General Public License
	along with this program; if not, write to the Free Software
	Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
*/

function moveList(document,fromid,toid) {
    alert('test: ' + fromid + ", " + toid  );
    alert('title: ' + document.title );
    document.title = 'hello';
/*    
    aSelFrom = document.getElementById(fromid);
    aSelTo = document.getElementById(toid);         
    alert('number of selectFrom options: ' + aSelFrom.options.length);
    alert('number of selectTo options: ' + aSelTo.options.length);
	indexSelTo = aSelTo.options.length-1;
	
	for (dd = 0; dd < aSelFrom.options.length; dd++) {
      if (aSelFrom.options[dd].selected) {
	    aSelFrom.options[dd] = null;
	    //dd = dd - 1;
	  }		
    }
    

	for (dd = 0; dd < aSelFrom.options.length; dd++) {
      if (aSelFrom.options[dd].selected) {
	    indexSelTo=indexSelTo+1;
	    aSelTo.options[indexSelTo]=new Option(aSelFrom.options[dd].text,aSelFrom.options[dd].value);
	    //aSelFrom.options[dd].selected=false;
	    aSelFrom.options[dd] = null;
	    dd = dd - 1;
	  }		
    }
    */
           
}
