function createjsDOMenu() {
  fixedMenu1 = new jsDOMenu(120);
  with (fixedMenu1) {
    addMenuItem(new menuItem("Band", "", "bandlist.faces"));
    addMenuItem(new menuItem("Home", "", "home.faces"));
  }
  
  
  fixedMenu2 = new jsDOMenu(120);
  with (fixedMenu2) {
    addMenuItem(new menuItem("Home", "", "home.faces"));
  }
  
    
  fixedMenuBar = new jsDOMenuBar("static","menubar");
  with (fixedMenuBar) {
    addMenuBarItem(new menuBarItem("Verwaltung", fixedMenu1));
    addMenuBarItem(new menuBarItem("Anwendungsfälle", fixedMenu2));
    moveTo(10, 100);
  }
}