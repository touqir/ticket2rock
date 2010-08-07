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
	import="javax.naming.*,java.util.*,de.ejb3buch.ticket2rock.migration.session.*"%>

<%!private AuskunftLocalHome home = null;
   private AuskunftLocal auskunft = null;

    public void jspInit() {
        try {
            InitialContext ctx = new InitialContext();
            //home = (AuskunftLocalHome) ctx.lookup("ticket2rock/AuskunftBeanOldStyle/localHome");
            //auskunft = home.create();
            auskunft = (AuskunftLocal)ctx.lookup("ticket2rock/AuskunftBeanOldStyle/local");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

%>

<html>
<head>
<title>Ticket2Rock Auskunft Migration</title>
<link type="text/css" href="style/ticket2rock.css" rel="stylesheet">
</head>
<body>
<img src="images/Ticket2Rock-Logo.jpg" alt="Ticket2Rock Logo">
<h1>JPQL Demo</h1>
<form action="AuskunftMigration.jsp" method="post">
  <h2>Konzertsuche</h2>
  Veranstaltungsort: <input type="text" name="ort" value="<%=request.getParameter("ort") %>" size="30"/><br/>
  Zeitraum/Von: <input type="text" name="von" value="<%=request.getParameter("von") %>" size="10"/><br/>
  Zeitraum/Bis: <input type="text" name="bis" value="<%=request.getParameter("bis") %>" size="10"/><br/>
  <input type="submit" value="Absenden"/>
</form>
<h2>Ergebnis</h2>
<%try {
    String ort = request.getParameter("ort");
    String von = request.getParameter("von");
    String bis = request.getParameter("bis");
	List resultList = auskunft.sucheKonzerte(ort, null, null);
if (resultList != null) {%>
<table border="1">
<%  for (Iterator iter = resultList.iterator(); iter.hasNext();) {
    Object element = iter.next();%>
  <tr>
    <%if (element instanceof Object[]) {
        Object[] array = (Object[])element;
        for (int i = 0; i < array.length; i++) {%>
        <td><%=array[i]%></td>
        <%}
    }
    else {%>
      <td><%=element%></td>
    <%}%>
  </tr>
<%}%>
</table>
<%}}
catch (Throwable t) {
 t.printStackTrace(new java.io.PrintWriter(out, true));
 }%>

<p>&nbsp;<br>
<img src="images/PoweredByEJB3-small.jpg" alt="Powered by EJB3"></p>

</body>
</html>
