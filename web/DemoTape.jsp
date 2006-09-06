<!--
  Ticket2Rock ist die Beispielanwendung des Buchs "EJB 3 professionell" (dpunkt).
  Es implementiert eine einfache Webanwendung zur Onlinebuchung von Tickets für
  Rockkonzerte auf Basis von EJB 3.0 und JavaServer Faces.
	
  Copyright (C) 2006
  Dierk Harbeck, Stefan M. Heldt, Oliver Ihns, Jochen Jörg und Holger Koschek

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

	private SimpleDateFormat datumsformat_Jahr = new SimpleDateFormat("yyyy");

	private SimpleDateFormat datumsformat_TagMonatJahr = new SimpleDateFormat(
			"dd.MM.yyyy");

	public void jspInit() {
		try {
			InitialContext ctx = new InitialContext();
			demo = (DemoTape) ctx.lookup("ticket2rock/DemoTapeBean/local");
		} catch (Exception e) {
			e.printStackTrace();
		}
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

<a href="statistics">Bean-Statistik</a>

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

<h3>Bands</h3>
<ul>
	<%for (Iterator iter = demo.getBands().iterator(); iter.hasNext();) {
				Band band = (Band) iter.next();
%>

	<li><%=band.getName()%>
	<ul>
		<%for (Iterator iter2 = band.getMusiker().iterator(); iter2
						.hasNext();) {
					Musiker musiker = (Musiker) iter2.next();
%>
		<li><%=musiker.getName()%></li>

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
