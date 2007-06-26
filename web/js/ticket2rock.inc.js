function createjsDOMenu() {
  fixedMenu1 = new jsDOMenu(120);
  with (fixedMenu1) {
    addMenuItem(new menuItem("Band", "", "bandlist.faces"));
    addMenuItem(new menuItem("Tournee", "", "tourneelist.faces"));
    addMenuItem(new menuItem("Home", "", "home.faces"));
  }
  
  
  fixedMenu2 = new jsDOMenu(120);
  with (fixedMenu2) {
    addMenuItem(new menuItem("Konzert suchen", "", "konzertsuche.faces"));
    addMenuItem(new menuItem("Bestellung stornieren", "", "stornierung.faces"));
  }
  
  
  fixedMenu3 = new jsDOMenu(120);
  with (fixedMenu3) {
    addMenuItem(new menuItem("Demo Tape", "", "demo"));
  }
  
    
  fixedMenuBar = new jsDOMenuBar("static","menubar");
  with (fixedMenuBar) {
    addMenuBarItem(new menuBarItem("Verwaltung", fixedMenu1));
    addMenuBarItem(new menuBarItem(unescape("Anwendungsfälle"), fixedMenu2));
    addMenuBarItem(new menuBarItem("Demo", fixedMenu3));
    moveTo(10, 100);
  }
}