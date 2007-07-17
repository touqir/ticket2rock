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
	import="javax.naming.InitialContext,java.util.Map,java.lang.reflect.Method,java.util.Iterator,de.ejb3buch.ticket2rock.session.statistics.BeanStatisticsLocal"%>

<%!private BeanStatisticsLocal beanstats = null;

	public void jspInit() {
		try {
			InitialContext ctx = new InitialContext();
			beanstats = (BeanStatisticsLocal) ctx
					.lookup("ticket2rock/BeanStatisticsBean/local");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
%>

<html>
<head>
<title>Ticket2Rock Bean Statistics</title>
<link type="text/css" href="style/ticket2rock.css" rel="stylesheet">
</head>
<body>
<img src="images/Ticket2Rock-Logo.jpg" alt="Ticket2Rock Logo">
<h1>Ticket2Rock Bean Statistics</h1>

<a href="home.faces">Home</a> | <a href="demo">Demo Tape</a>

<h3>Classes</h3>
<ul>
	<%Map classUsage = beanstats.getClassUsage();
			for (Iterator iter = classUsage.keySet().iterator(); iter.hasNext();) {
				Class theClass = (Class) iter.next();
%>

	<li><%=theClass.getName()%>: <%=classUsage.get(theClass)%> instance(s)</li>

	<%}

			%>
</ul>

<h3>Method calls</h3>
<ul>
	<%Map methodUsage = beanstats.getMethodUsage();
	Map methodTotalDuration = beanstats.getMethodTotalDuration();
			for (Iterator iter = methodUsage.keySet().iterator(); iter
					.hasNext();) {
				Method theMethod = (Method) iter.next();
%>

	<li><%=theMethod.getDeclaringClass().getName()%>.<%=theMethod.getName()%>: called <%=methodUsage.get(theMethod)%>
	time(s), total execution time: <%=methodTotalDuration.get(theMethod)%> ms</li>

	<%}

		%>
</ul>

<p>&nbsp;<br>
<img src="images/PoweredByEJB3-small.jpg" alt="Powered by EJB3"></p>

</body>
</html>
