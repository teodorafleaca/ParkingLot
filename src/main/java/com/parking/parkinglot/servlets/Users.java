package com.parking.parkinglot.servlets;

import com.parking.parkinglot.ejb.UserBean;
import com.parking.parkinglot.common.UserDto;
import com.parking.parkinglot.ejb.InvoiceBean;

import jakarta.annotation.security.DeclareRoles;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.HttpMethodConstraint;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@DeclareRoles({"READ_USERS", "WRITE_USERS"})
@ServletSecurity(
        value = @HttpConstraint(rolesAllowed = {"READ_USERS"}),
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "POST", rolesAllowed = {"WRITE_USERS"})
        }
)

@WebServlet(name = "Users", value = "/Users")
public class Users extends HttpServlet {

    @Inject
    UserBean userBean;

    @Inject
    InvoiceBean invoiceBean; // ★ injectăm bean-ul stateful

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Afișăm lista utilizatorilor
        List<UserDto> users = userBean.findAllUsers();
        request.setAttribute("users", users);

        // 2. Dacă avem utilizatori selectați în invoiceBean → îi afișăm
        if (!invoiceBean.getUserIds().isEmpty()) {
            Collection<String> usernames =
                    userBean.findUsernamesByUserIds(invoiceBean.getUserIds());

            request.setAttribute("invoices", usernames);
        }

        // 3. Trimitem mai departe către pagina JSP
        request.getRequestDispatcher("/WEB-INF/pages/users.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String[] userIdsAsString = request.getParameterValues("user_ids");

        if (userIdsAsString != null) {

            List<Long> userIds = new ArrayList<>();

            for (String id : userIdsAsString) {
                userIds.add(Long.parseLong(id));
            }

            // ★ adăugăm userii selectați în invoiceBean
            invoiceBean.getUserIds().addAll(userIds);
        }

        response.sendRedirect(request.getContextPath() + "/Users");
    }
}
