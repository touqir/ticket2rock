<!--
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
-->

<%@ page
	import="javax.naming.*,java.util.*,java.text.SimpleDateFormat,de.ejb3buch.ticket2rock.entity.*,de.ejb3buch.ticket2rock.session.demo.*"%>

<%!private DemoTape demo = null;
private AbfangjaegerLocal falcon_klasse = null;
private AbfangjaegerLocal falcon_methode = null;
private AbfangjaegerLocal falcon_klasse_dd = null;
private AbfangjaegerLocal falcon_methode_dd = null;

	private SimpleDateFormat datumsformat_Jahr = new SimpleDateFormat("yyyy");

	private SimpleDateFormat datumsformat_TagMonatJahr = new SimpleDateFormat(
			"dd.MM.yyyy");

	private SimpleDateFormat datumsformat_TagMonatJahrStundeMinute = new SimpleDateFormat(
			"dd.MM.yyyy HH:mm");

	public void jspInit() {
		try {
			InitialContext ctx = new InitialContext();
			demo = (DemoTape) ctx.lookup("ticket2rock/DemoTapeBean/local");
			falcon_klasse = (AbfangjaegerLocal) ctx.lookup("ticket2rock/AbfangjaegerBeanKlasseninterzeptor/local");
			falcon_methode = (AbfangjaegerLocal) ctx.lookup("ticket2rock/AbfangjaegerBeanMethodeninterzeptor/local");
			falcon_klasse_dd = (AbfangjaegerLocal) ctx.lookup("ticket2rock/AbfangjaegerBeanKlasseninterzeptorDD/local");
			falcon_methode_dd = (AbfangjaegerLocal) ctx.lookup("ticket2rock/AbfangjaegerBeanMethodeninterzeptorDD/local");
			
			testInterceptors();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testInterceptors() {
		falcon_klasse.fangMichAb();
		falcon_klasse.michAuch(null);
		falcon_klasse.michNicht();
		
		falcon_methode.fangMichAb();
		falcon_methode.michAuch(null);
		falcon_methode.michNicht();		

		falcon_klasse_dd.fangMichAb();
		falcon_klasse_dd.michAuch(null);
		falcon_klasse_dd.michNicht();
		
		falcon_methode_dd.fangMichAb();
		falcon_methode_dd.michAuch(null);
		falcon_methode_dd.michNicht();		
	}
	
%>

<html>
<head>
<title>Ticket2Rock Demo Tape</title>
<link type="text/css" href="style/ticket2rock.css" rel="stylesheet">
</head>
<body>
<img src="images/Ticket2Rock-Logo.jpg" alt="Ticket2Rock Logo">
<h1>Demo Tape</h1>

<a href="home.faces">Home</a> | <a href="statistics">Bean-Statistik</a>

<h3>Bestellungen</h3>
<ul>
	<%for (Iterator iter = demo.getBestellungen().iterator(); iter.hasNext();) {
				Ticketbestellung bestellung = (Ticketbestellung) iter.next();
				Konzert konzert = bestellung.getKonzert();
				konzert.bestelleTickets(2);
%>

	<li><%=bestellung.getKunde().getEmail()%>:  <%=bestellung.getAnzahl()%> Tickets f&uuml;r <%=konzert.getInterpret().getName()%> am <%=datumsformat_TagMonatJahr.format(konzert
									.getDatum())%> in <%=konzert.getOrt().getAdresse()%> (Kapazität: <%=konzert.getOrt().getKapazitaet()%>, Restkontingent: <%=konzert.getTicketkontingent()%>)</li>

	<%}%>
</ul>

<h3>News</h3>
<ul>
	<%for (Iterator iter = demo.getNews().iterator(); iter.hasNext();) {
				News news = (News) iter.next();
%>

	<li><%=datumsformat_TagMonatJahrStundeMinute.format(news.getDatum())%> - <%=news.getNachricht()%></li>

	<%}%>
</ul>

<h3>Tourneen</h3>
<ul>
	<%for (Iterator iter = demo.getTourneen().iterator(); iter.hasNext();) {
				Tournee tour = (Tournee) iter.next();
%>

	<li><%=tour.getInterpret().getName()%> - <%=tour.getName()%>
	<ul>
		<%for (Iterator iter2 = tour.getKonzerte().iterator(); iter2
						.hasNext();) {
					Konzert konzert = (Konzert) iter2.next();
%>
		<li><%=datumsformat_TagMonatJahr.format(konzert
									.getDatum())%> <%=konzert.getOrt().getName()%>,
		<%=konzert.getOrt().getAdresse()%></li>

		<%}%>
	</ul>
	</li>

	<%}%>
</ul>

<h3>Konzerte</h3>
<ul>
	<%for (Iterator iter2 = demo.getKonzerte().iterator(); iter2
					.hasNext();) {
				Konzert konzert = (Konzert) iter2.next();
	%>
		<li>
		<%=konzert.getInterpret().getName()%>
		<%if (konzert.getTournee() != null) {%>
		(<%=konzert.getTournee().getName()%>)
		<%}%>
		-
		<%=datumsformat_TagMonatJahr.format(konzert.getDatum())%>
		<%if (konzert.getOrt().getKoordinaten() != null) {%>
			<a href="http://<%=konzert.getOrt().getKoordinaten().getGoogleMapsUrlString()%>" target="_newwindow">
		<%}%>
		<%=konzert.getOrt().getName()%>,
		<%=konzert.getOrt().getAdresse()%>
		<%if (konzert.getOrt().getKoordinaten() != null) {%>
			</a>
		<%}%>
		- <%=konzert.getVeranstalter().getName()%>
		</li>
	<%}%>
</ul>

<h3>Bands</h3>
<ul>
	<%for (Iterator iter = demo.getBands().iterator(); iter.hasNext();) {
				Band band = (Band) iter.next();
%>

	<li><%=band.getName()%> (<%=band.getGruendungsjahr()%>)
	<ul>
		<%for (Iterator iter2 = band.getMusiker().iterator(); iter2
						.hasNext();) {
					Musiker musiker = (Musiker) iter2.next();
%>
		<li><%=musiker.getName()%>
  		<%if (musiker.getGeburtsname() != null) {%>
			(<i><%=musiker.getGeburtsname()%></i>)
		<%}%>  
		</li>
		<%}%>
	</ul>
	</li>

	<%}%>
</ul>

<h3>Musiker</h3>
<ul>
	<%for (Iterator iter = demo.getMusiker().iterator(); iter.hasNext();) {
				Musiker musiker = (Musiker) iter.next();
%>

	<li><%=musiker.getName()%>
	<ul>
		<%for (Iterator iter2 = musiker.getBands().iterator(); iter2
						.hasNext();) {
					Band band = (Band) iter2.next();
%>
		<li><%=band.getName()%></li>

		<%}%>
	</ul>
	</li>

	<%}%>
</ul>

<h3>Alben</h3>
<ul>
	<%for (Iterator iter = demo.getAlben().iterator(); iter.hasNext();) {
				Album album = (Album) iter.next();
%>

	<li><%=album.getTitel()%> - <%=album.getInterpret().getName()%> (<%=datumsformat_Jahr
								.format(album.getErscheinungsdatum())%>)
	<ol>
		<%for (Iterator iter2 = album.getSongs().iterator(); iter2
						.hasNext();) {
					Song song = (Song) iter2.next();
%>
		<li><%=song.getTitel()%> (<%=song.getGenre().getName()%>)</li>

		<%}%>
	</ol>
	</li>

	<%}%>
</ul>

<h3>Songs</h3>
<ul>
	<%for (Iterator iter = demo.getSongs().iterator(); iter.hasNext();) {
				Song song = (Song) iter.next();
%>

	<li><%=song.getTitel()%> - <%=song.getInterpret().getName()%> <%if (song.getAlbum() != null) {

					%> (<%=song.getAlbum().getTitel()%>) <%}%></li>

	<%}%>
</ul>

<p>&nbsp;<br>
<img src="images/PoweredByEJB3-small.jpg" alt="Powered by EJB3"></p>

</body>
</html>
