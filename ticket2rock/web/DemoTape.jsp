<!--
	Ticket2Rock ist die Beispielanwendung des Buchs "EJB 3.1 professionell" (dpunkt).
	Es implementiert eine einfache Webanwendung zur Onlinebuchung von Tickets für
	Rockkonzerte auf Basis von EJB 3.1 und JavaServer Faces.
	
	Copyright (C) 2006-2011
	Jo Ehm, Stefan M. Heldt, Oliver Ihns, Holger Koschek,
	Carsten Sahling, Roman Schloemmer, Norman Erck, Daniel Steinhöfer,
	Carl A. Düvel.

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
-->

<%@ page
	import="javax.naming.*,java.util.*,java.text.SimpleDateFormat,de.ejb3buch.ticket2rock.entity.*,de.ejb3buch.ticket2rock.session.demo.*,javax.inject.Inject"%>

<%!
@Inject
private DemoTape demo;

	private SimpleDateFormat datumsformat_Jahr = new SimpleDateFormat("yyyy");

	private SimpleDateFormat datumsformat_TagMonatJahr = new SimpleDateFormat(
			"dd.MM.yyyy");

	private SimpleDateFormat datumsformat_TagMonatJahrStundeMinute = new SimpleDateFormat(
			"dd.MM.yyyy HH:mm");
%>

<html>
<head>
<title>Ticket2Rock Demo Tape</title>
<link type="text/css" href="style/ticket2rock.css" rel="stylesheet">
</head>
<body>
<img src="images/Ticket2Rock-Logo.jpg" alt="Ticket2Rock Logo">
<h1>Demo Tape</h1>

<a href="home.faces">Home</a>
|
<a href="statistics">Bean-Statistik</a>

<h3>Bestellungen</h3>
<ul>
	<%
		for (Ticketbestellung bestellung : demo.getBestellungen()) {
			Konzert konzert = bestellung.getKonzert();
			konzert.bestelleTickets(2);
	%>

	<li><%=bestellung.getKunde().getEmail()%>: <%=bestellung.getAnzahl()%>
	Tickets f&uuml;r <%=konzert.getInterpret().getName()%> am <%=datumsformat_TagMonatJahr.format(konzert.getDatum())%>
	in <%=konzert.getOrt().getAdresse()%> (Kapazität: <%=konzert.getOrt().getKapazitaet()%>,
	Restkontingent: <%=konzert.getTicketkontingent()%>)</li>

	<%
		}
	%>
</ul>

<h3>News</h3>
<ul>
	<%
		for (News news : demo.getNews()) {
	%>

	<li><%=datumsformat_TagMonatJahrStundeMinute.format(news
						.getDatum())%> - <%=news.getNachricht()%></li>

	<%
		}
	%>
</ul>

<h3>Tourneen</h3>
<ul>
	<%
		for (Tournee tour : demo.getTourneen()) {
	%>

	<li><%=tour.getInterpret().getName()%> - <%=tour.getName()%>
	<ul>
		<%
			for (Konzert konzert : tour.getKonzerte()) {
		%>
		<li><%=datumsformat_TagMonatJahr.format(konzert
							.getDatum())%> <%=konzert.getOrt().getName()%>, <%=konzert.getOrt().getAdresse()%></li>

		<%
			}
		%>
	</ul>
	</li>

	<%
		}
	%>
</ul>

<h3>Konzerte</h3>
<ul>
	<%
		for (Konzert konzert : demo.getKonzerte()) {
	%>
	<li><%=konzert.getInterpret().getName()%> <%
 	if (konzert.getTournee() != null) {
 %> (<%=konzert.getTournee().getName()%>) <%
 	}
 %> - <%=datumsformat_TagMonatJahr.format(konzert.getDatum())%> <%
 	if (konzert.getOrt().getKoordinaten() != null) {
 %> <a
		href="http://<%=konzert.getOrt().getKoordinaten()
							.getGoogleMapsUrlString()%>"
		target="_newwindow"> <%
 	}
 %> <%=konzert.getOrt().getName()%>, <%=konzert.getOrt().getAdresse()%>
	<%
		if (konzert.getOrt().getKoordinaten() != null) {
	%> </a> <%
 	}
 %> - <%=konzert.getVeranstalter().getName()%></li>
	<%
		}
	%>
</ul>

<h3>Bands</h3>
<ul>
	<%
		for (Band band : demo.getBands()) {
	%>

	<li><%=band.getName()%> (<%=band.getGruendungsjahr()%>)
	<ul>
		<%
			for (Musiker musiker : band.getMusiker()) {
		%>
		<li><%=musiker.getName()%> <%
 	if (musiker.getGeburtsname() != null) {
 %> (<i><%=musiker.getGeburtsname()%></i>) <%
 	}
 %>
		</li>
		<%
			}
		%>
	</ul>
	</li>

	<%
		}
	%>
</ul>

<h3>Musiker</h3>
<ul>
	<%
		for (Musiker musiker : demo.getMusiker()) {
	%>

	<li><%=musiker.getName()%>
	<ul>
		<%
			for (Band band : musiker.getBands()) {
		%>
		<li><%=band.getName()%></li>

		<%
			}
		%>
	</ul>
	</li>

	<%
		}
	%>
</ul>

<h3>Alben</h3>
<ul>
	<%
		for (Album album : demo.getAlben()) {
	%>

	<li><%=album.getTitel()%> - <%=album.getInterpret().getName()%> (<%=datumsformat_Jahr.format(album.getErscheinungsdatum())%>)
	<ol>
		<%
			for (Song song : album.getSongs()) {
		%>
		<li><%=song.getTitel()%> (<%=song.getGenre().getName()%>)</li>

		<%
			}
		%>
	</ol>
	</li>

	<%
		}
	%>
</ul>

<h3>Songs</h3>
<ul>
	<%
		for (Song song : demo.getSongs()) {
	%>

	<li><%=song.getTitel()%> - <%=song.getInterpret().getName()%> <%
 	if (song.getAlbum() != null) {
 %> (<%=song.getAlbum().getTitel()%>) <%
 	}
 %>
	</li>

	<%
		}
	%>
</ul>

<p>&nbsp;<br>
<img src="images/PoweredByEJB3-small.jpg" alt="Powered by EJB3"></p>

</body>
</html>
