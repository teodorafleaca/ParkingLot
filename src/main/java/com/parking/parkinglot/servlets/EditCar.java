package com.parking.parkinglot.servlets;

import com.parking.parkinglot.common.CarDto;
import com.parking.parkinglot.common.UserDto;
import com.parking.parkinglot.ejb.CarsBean;
import com.parking.parkinglot.ejb.UserBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "EditCar", value = "/EditCar")
public class EditCar extends HttpServlet {

    @Inject
    UserBean userBean;

    @Inject
    CarsBean carsBean;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        List<UserDto> users=userBean.findAllUsers();
        request.setAttribute("users",users);

        Long carID=Long.valueOf(request.getParameter("id"));

        CarDto car=carsBean.findById(carID);
        request.setAttribute("car",car);

        request.getRequestDispatcher("/WEB-INF/pages/editCar.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long carID = Long.valueOf(request.getParameter("car_id"));
        String licensePlate = request.getParameter("license_plate");
        String parkingSpot = request.getParameter("parking_spot");
        Long userID = Long.valueOf(request.getParameter("owner_id"));

        carsBean.updateCar(carID, licensePlate, parkingSpot, userID);

        response.sendRedirect(request.getContextPath() + "/Cars");
    }

}