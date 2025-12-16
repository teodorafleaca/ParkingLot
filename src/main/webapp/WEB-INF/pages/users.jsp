<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Users">

    <h1>Users</h1>

    <form method="POST" action="${pageContext.request.contextPath}/Users">

        <c:if test="${pageContext.request.isUserInRole('WRITE_USERS')}">
            <a href="${pageContext.request.contextPath}/AddUser"
               class="btn btn-primary btn-lg mb-3">Add User</a>

            <button class="btn btn-secondary mb-3" type="submit">Invoice</button>
        </c:if>

        <div class="container text-center">
            <div class="row fw-bold border-bottom pb-2 mb-2">
                <div class="col">#</div>
                <div class="col">Username</div>
                <div class="col">Email</div>
            </div>

            <c:forEach var="user" items="${users}">
                <div class="row mb-2">

                    <div class="col">
                        <c:if test="${pageContext.request.isUserInRole('WRITE_USERS')}">
                            <input type="checkbox" name="user_ids" value="${user.id}">
                        </c:if>
                    </div>

                    <div class="col">${user.username}</div>
                    <div class="col">${user.email}</div>

                </div>
            </c:forEach>

        </div>

    </form>

    <!--  AICI este secțiunea pentru afișarea facturilor -->
    <c:if test="${not empty invoices}">
        <h2>Invoices</h2>

        <c:forEach var="username" items="${invoices}" varStatus="status">
            ${status.index + 1}. ${username}
            <br/>
        </c:forEach>

    </c:if>


</t:pageTemplate>
