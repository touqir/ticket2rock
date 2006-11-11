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
