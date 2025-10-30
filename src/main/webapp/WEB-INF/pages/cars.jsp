<%--
  Created by IntelliJ IDEA.
  User: Teodora Fleaca
  Date: 10/30/2025
  Time: 12:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageTemplate pageTitle="Cars">
    <h1>Cars</h1>
    <div class="row">
        <div class="col">
            Car 1
        </div>
        <div class="col">
            Spot 1
        </div>
        <div class="col">
            User 1
        </div>

    </div>
    <div class="row">
        <div class="col">
            Car 2
        </div>
        <div class="col">
            Spot 2
        </div>
        <div class="col">
            User 2
        </div>
    </div>
    <h5>Free parking spots: ${numberOfFreeParkingSpots}</h5>
</t:pageTemplate>
