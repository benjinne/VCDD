document.write('\
		\
		<nav class="navbar navbar-inverse">\
    	<!-- Brand and toggle get grouped for better mobile display -->\
   		<div class="navbar-header">\
        	<button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">\
            	<span class="sr-only">Toggle navigation</span>\
            	<span class="icon-bar"></span>\
            	<span class="icon-bar"></span>\
            	<span class="icon-bar"></span>\
        	</button>\
        	<a href="https://github.com/benjinne/VCDD/wiki" data-toggle="tooltip" title="Github Page" target="_blank" class="navbar-brand"><span class="glyphicon glyphicon-globe"></span> VCDD</a>\
    	</div>\
    <!-- Collection of nav links and other content for toggling -->\
	    <div id="navbarCollapse" class="collapse navbar-collapse">\
	        <ul class="nav navbar-nav">\
	            <li><a href="/resistorSorter/TestViewInventory"><span class="glyphicon glyphicon-home"></span> Home</a></li>\
	            <li><button class="btn btn-info navbar-btn" type="button"  data-toggle="modal" data-target="#myModal">Wiki</button></li>\
	        </ul>\
	        <ul class="nav navbar-nav navbar-right">\
	        	<li><a href="/resistorSorter/Profile"><span class="glyphicon glyphicon-user"></span> Your Profile</a></li>\
	        	<li><button class="btn btn-danger navbar-btn" type="submit" name="logout" onclick="window.open(\'https://accounts.google.com/logout\', \'_blank\')">Logout</button></li>\
	        </ul>\
	    </div>\
	</nav>\
		\
		<!-- Modal -->\
		  <div class="modal fade" id="myModal" role="dialog">\
		    <div class="modal-dialog">\
		    \
		      <!-- Modal content-->\
		      <div class="modal-content">\
		        <div class="modal-header">\
		          <button type="button" class="close" data-dismiss="modal">&times;</button>\
		          <h4 class="modal-title">WIKI</h4>\
		        </div>\
		        <div class="modal-body">\
		        	<div class="markdown-body">\
				\
						<h1>Welcome the the VCDD Wiki page!</h1>\
							<p>In here you can learn about all of the functionality our product brings to Kinsley Engineering center. Our inventory management system is meant to help keep track of all the resistors in the building.</p>\
						<h2>How it works</h2>\
							<p>Inventories:</p>\
						<ol>\
							<li>Every room has an inventory, you can think of an inventory as a large shelf with racks, bins, and resistors.</li>\
							<li>An inventory has a bin capacity and a user remove limit, for the number of resistors in a bin, these are controlled by the administrators.</li>\
							<li>Rules: There can exist multiple (or 0) inventories in a single room, with unique (or identical) capacities and remove limits.</li>\
						</ol>\
						<p>Racks:</p>\
						<ol>\
							<li>Racks are sections of an inventory that have tolerance and power ratings for the resistors, these are specified by the administrators.</li>\
							<li>Racks are like smaller sections of an inventory that contain bins that only fit the specifications of the rack.</li>\
							<li>Rules: There can exist multiple (or 0) racks in a single inventory, with unique (NOT identical) tolerance and power ratings.</li>\
						</ol>\
						<p>Bins:</p>\
						<ol>\
							<li>Racks are sections of a rack that have resistance and counts associated with them, these are specified by the administrators.</li>\
							<li>Bins contain the resistors, at this point the resistor has a resistance, tolerance, and power rating associated with it.</li>\
							<li>Rules: There can exist multiple (or 0) bins in a single rack, with unique (NOT identical) resistance ratings. The user can freely add or remove resistors from the bins as long as they are not exceeding the remove limits, capacities. All valid transactions will be stored in the transactions table found under the \'Your Profile\' tab.</li>\
						</ol>\
					</div>\
		        </div>\
		      </div>\
		      \
		    </div>\
		  </div>\
');

