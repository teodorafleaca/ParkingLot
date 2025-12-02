<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Add User">

    <h1>Add User</h1>

    <form class="needs-validation" novalidate method="POST"
          action="${pageContext.request.contextPath}/AddUser">

        <div class="row">

            <!-- Username -->
            <div class="col-md-6 mb-3">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" name="username" required>
                <div class="invalid-feedback">Username is required.</div>
            </div>

            <!-- Email -->
            <div class="col-md-6 mb-3">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" required>
                <div class="invalid-feedback">Email is required.</div>
            </div>

            <!-- Password -->
            <div class="col-md-6 mb-3">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
                <div class="invalid-feedback">Password is required.</div>
            </div>

            <!-- Groups -->
            <div class="col-12 mb-3">
                <label>User Groups</label>
                <c:forEach var="group" items="${userGroups}">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox"
                               name="user_groups" value="${group}" id="${group}">
                        <label class="form-check-label" for="${group}">
                                ${group}
                        </label>
                    </div>
                </c:forEach>
            </div>

        </div>

        <hr class="mb-4">
        <button class="btn btn-primary btn-lg btn-block" type="submit">Save</button>

    </form>

</t:pageTemplate>
