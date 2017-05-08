document.write('\
		<link rel="icon" href="_view/images/favicon.ico" type="image/x-icon">\
		<nav class="navbar navbar-inverse">\
    	<!-- Brand and toggle get grouped for better mobile display -->\
   		<div class="navbar-header">\
        	<button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">\
            	<span class="sr-only">Toggle navigation</span>\
            	<span class="icon-bar"></span>\
            	<span class="icon-bar"></span>\
            	<span class="icon-bar"></span>\
        	</button>\
        	<li><button class="navbar-brand" data-toggle="modal" data-target="#myModal"> <span class="glyphicon glyphicon-globe"></span> VCDD</button></li>\
	      	<div class="modal-content">\
        		<div class="modal-header">\
          		<button type="button" class="close" data-dismiss="modal">&times;</button>\
          		<h4 class="modal-title">Modal Header</h4>\
			</div>\
        	<div class="modal-body">\
          		<p>Some text in the modal.</p>\
        	</div>\
        	<div class="modal-footer">\
          		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>\
        	</div>\
			</div>\
    	</div>\
    <!-- Collection of nav links and other content for toggling -->\
	    <div id="navbarCollapse" class="collapse navbar-collapse">\
	        <ul class="nav navbar-nav">\
	            <li><a href="/resistorSorter/Inventories"><span class="glyphicon glyphicon-home"></span> Home</a></li>\
	            <li><a href="/resistorSorter/Racks">Racks</a></li>\
	            <li><a href="/resistorSorter/Bins">Bins</a></li>\
	            <li><a href="/resistorSorter/Resistor">Resistor</a></li>\
	            <li><a href="/resistorSorter/TestViewInventory">Full Inventory</a></li>\
	        </ul>\
	        <ul class="nav navbar-nav navbar-right">\
	        	<li><a href="/resistorSorter/Profile"><span class="glyphicon glyphicon-user"></span> Your Profile</a></li>\
	        	<li><button class="btn btn-danger navbar-btn" type="submit" name="logout">Logout</button></li>\
	        </ul>\
	    </div>\
	</nav>\
');

