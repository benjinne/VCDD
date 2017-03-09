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
		

		
		
		<form action="${pageContext.servletContext.contextPath}/InitializeInventory" method="post">
			<table>
				<tr>
					<td class="label">binCapacity:</td>
					<td><input type="text" name="binCapacity" size="12" value="${inventory.binCapacity}" /></td>
				</tr>
				<tr>
					<td class="label">userRemoveLimit:</td>
					<td><input type="text" name="userRemoveLimit" size="12" value="${inventory.userRemoveLimit}" /></td>
				</tr>

				

			</table>
			<input type="Submit" name="submit" value="Initialize Inventory!">
		</form>
	</body>
</html>