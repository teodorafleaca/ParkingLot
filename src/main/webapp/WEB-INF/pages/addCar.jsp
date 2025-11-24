<%--
  Created by IntelliJ IDEA.
  User: Teodora Fleaca
  Date: 11/24/2025
  Time: 7:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:pageTemplate pageTitle="Add Car">

    <h1>Add Car</h1>

    <form class="needs-validation" novalidate method="POST"
          action="${pageContext.request.contextPath}/AddCar">

        <div class="row">

            <!-- License Plate -->
            <div class="col-md-12 mb-3">
                <label for="license_plate">License Plate</label>
                <input type="text" class="form-control" id="license_plate"
                       name="license_plate" placeholder="" value="" required>
                <div class="invalid-feedback">
                    License Plate is required.
                </div>
            </div>

            <!-- Parking Spot -->
            <div class="col-md-12 mb-3">
                <label for="parking_spot">Parking Spot</label>
                <input type="text" class="form-control" id="parking_spot"
                       name="parking_spot" placeholder="" value="" required>
                <div class="invalid-feedback">
                    Parking Spot is required.
                </div>
            </div>

            <!-- Owner Dropdown -->
            <div class="col-12 mb-3">
                <label for="owner_id">Owner</label>
                <select class="custom-select d-block w-100" id="owner_id" name="owner_id" required>
                    <option value="">Choose...</option>

                    <c:forEach var="user" items="${users}">
                        <option value="${user.id}">
                                ${user.username}
                        </option>
                    </c:forEach>

                </select>

                <div class="invalid-feedback">
                  Owner is required.
                </div>
            </div>

        </div>

        <hr class="mb-4">

        <button class="btn btn-primary btn-lg btn-block" type="submit">Save</button>

    </form>

</t:pageTemplate>


