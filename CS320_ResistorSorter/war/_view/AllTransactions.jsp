<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!-- DISCLAIMER 
All work seen in here has been copied, but modified from the Library example -->

<html>
	<head>
		<link rel="icon" href="_view/images/favicon.ico" type="image/x-icon">
		<title>Profile</title>
		
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link href= "_view/css/styles.css" rel= "stylesheet" type= "text/css">
		<link rel="stylesheet" href="_view/css/tableThemes/blue/style.css" type="text/css" media="print, projection, screen" />
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
 		<script type="text/javascript" src="_view/javaScript/jquery.tablesorter.min.js"></script>
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  		
	</head>

	<body>
		<div class="container">
		<div class="row">
					
	<form action="${pageContext.servletContext.contextPath}/AllTransactions" method="post">
		<script src="_view/javaScript/navbar.js"></script>
    	<c:if test="${! empty errorMessage}">
			<div class="alert alert-danger alert-dismissable fade in">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Error: </strong>${errorMessage}
			</div>
		</c:if>
	  <div class="jumbotron">
	    <h1>All Transactions </h1> 
	  </div>
			<div>
				<table class="tablesorter" id="transactionTable">
				
					<thead>
						<tr>
							<th>Transaction Time (mm/dd/yyyy hh:mm)</th>
							<th>Username</th>
							<th>Inventory Name</th>
							<th>Resistance</th>
							<th>Wattage</th>
							<th>Tolerance</th>
							<th>Quantity</th>
							<th>Transaction Type</th>
							<th>Amount Remaining</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${transactions}" var="item" varStatus="status">
	 						<tr> 
	 							<td>${item.transactionTime}</td>
	 							<td>${item.username}</td>
	 							<td>${item.inventoryName}</td>
	 						 	<td>${item.resistance}</td>
	 						 	<td>${item.wattage}</td>
	 						 	<td>${item.tolerance}</td>
	 						 	<td>${item.quantity}</td>
	 						 	<td>${item.transactionType}</td>
	 						 	<td>${item.remaining}</td>
	 						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</form>
		</div>
		</div>
		<script>
	    $(document).ready(function() { 
	            $("#transactionTable").tablesorter(); 
	    });
    	</script>
	</body>
</html>