/*
	Ticket2Rock ist die Beispielanwendung des Buchs "EJB 3.1 professionell" (dpunkt).
	Es implementiert eine einfache Webanwendung zur Onlinebuchung von Tickets für
	Rockkonzerte auf Basis von EJB 3.1 und JavaServer Faces.
	
	Copyright (C) 2006-2011
	Holisticon AG
	
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

function createjsDOMenu(cid) {
	fixedMenu1 = new jsDOMenu(120);
	with (fixedMenu1) {
		addMenuItem(new menuItem("Band", "", "bandlist.faces"));
		addMenuItem(new menuItem("Tournee", "", "tourneelist.faces"));
		addMenuItem(new menuItem("Home", "", "home.faces"));
	}

	fixedMenu2 = new jsDOMenu(120);
	if (cid == undefined) {
		with (fixedMenu2) {
			addMenuItem(new menuItem("Konzert suchen", "", "konzertsuche.faces"));
			addMenuItem(new menuItem("Bestellung stornieren", "",
					"stornierung.faces"));
			addMenuItem(new menuItem("Tagesaktuelle Konzerte", "",
					"tagesaktuellekonzerte.faces"));
		}
	} else {
		with (fixedMenu2) {
			addMenuItem(new menuItem("Konzert suchen", "",
					"konzertsuche.faces?cid=" + cid));
			addMenuItem(new menuItem("Bestellung stornieren", "",
					"stornierung.faces?cid=" + cid));
			addMenuItem(new menuItem("Tagesaktuelle Konzerte", "",
					"tagesaktuellekonzerte.faces" + cid));

		}
	}

	fixedMenu3 = new jsDOMenu(120);
	with (fixedMenu3) {
		addMenuItem(new menuItem("Demo Tape", "", "demo"));
	}

	fixedMenuBar = new jsDOMenuBar("static", "menubar");
	with (fixedMenuBar) {
		addMenuBarItem(new menuBarItem("Verwaltung", fixedMenu1));
		addMenuBarItem(new menuBarItem(unescape("Anwendungsf%E4lle"),
				fixedMenu2));
		addMenuBarItem(new menuBarItem("Demo", fixedMenu3));
		moveTo(10, 100);
	}
}