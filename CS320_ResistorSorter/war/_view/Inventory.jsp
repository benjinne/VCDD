<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<html>
	<head>
		<title>Take Resistor</title>
		<style type="text/css">
		.error {
			color: red;
		}
		
		td.label {
			text-align: right;
		}
		</style>
	</head>

	<body>
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
		


		
		<form action="${pageContext.servletContext.contextPath}/Inventory" method="post">
		
	
		<div style="float:left;">
				<table>
					<tr>
						<td class="label">Bin Capacity:</td>
						<td><input type="text" name="binCapacity" size="12" value="${inventory.binCapacity}" /></td>
					</tr>
					
					<tr>
						
						<td class="label">User Remove Limit: </td>
						<td><input type="text" name="userRemoveLimit" size="12" value="${inventory.userRemoveLimit}" /></td>
						
					<tr>
							<td class="label">Tolerance:</td> 
							<td><input type="text" name="tolerance" size="12"/></td>
					</tr>
					
					<tr>
							<td class="label">Power: </td>
							<td><input type="text" name="power" size="12"/></td>
					</tr>
					
					<tr>
						<td>
							<input type="Submit" name="initializeInventory" value="Initialize Inventory!">
						</td>
						
						<td>
								<input type="Submit" name="initializeRack" value="Add Rack!">
						</td>
					</tr>
					
						<tr>
							<td class="label">Rack #:
								<br/>
								
								<c:forEach var = "i" begin = "1" end="${inventory.rackLength}">
									${i}<br/>
								</c:forEach>
							
							</td>
							<td class="label">Tolerance, Power
								<br/>
								<c:forEach items="${inventory.racks}" var="item">
									${item} <br />
								</c:forEach>
								
							</td>
						</tr>
						
						<tr>
							<td class="label">Enter Rack #:</td>
							<td><input type="text" name="rackNum" size="12"/></td>
						</tr>
						
						<tr>
						
							<td><input type="Submit" name="editRack" value="Edit Rack!"></td>
							<td><input type="Submit" name="deleteRack" value="Delete Rack!"></td>
						</tr>
	
				</table>
			</div>
			<div>
				<table>
					
					<tr>
						<td class="label">Inventory #: 
							<br/>
	 						<c:forEach items="${inventories}" var="item" varStatus="status">
	 						
	   						 	${status.count}<br>
	   						 	
							</c:forEach>
							<br/>
						</td>
						 
						<td class="label">BinCapacity: 
							<br/>
							<c:forEach items="${inventories}" var="item" >
	   						 	${item.binCapacity}<br>
							</c:forEach>
							<br/>
						</td> 
						
						<td class="label">RemoveLimit: 
							<br/>
							<c:forEach items="${inventories}" var="item" >
	   						 	${item.userRemoveLimit}<br>
							</c:forEach>
							<br/>
						</td> 
						
					</tr>
					
					<tr>
					
					</tr>
				
				</table>
			</div>
		</form>
		
	</body>
	
</html>