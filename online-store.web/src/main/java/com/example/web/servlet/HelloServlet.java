package com.example.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.Locale;


@WebServlet(urlPatterns = { "/demo", "/demo1" },  initParams = {
        @WebInitParam(name="firstName", value = "Mukhammadjon"),
        @WebInitParam(name="lastName",  value = "Kholmukhamedov")}
)

public class HelloServlet extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName = getInitParameter("firstName");
        String lastName = getInitParameter("lastName");

        PrintWriter writer = response.getWriter();

        writer.println("firstName = " + firstName + "; ");
        writer.println("lastName = " + lastName);

        writer.println("</br>");

        writer.println("Servlet name: " + getServletConfig().getServletName());
        writer.println("</br>");
        writer.println("Servlet name: " + getServletName());
        writer.println("</br>");
        writer.println("Context path: " + getServletContext().getContextPath());
        writer.println("</br>");

        getServletContext().setAttribute("locale", Locale.getDefault());

        String param = request.getParameter("param");
        writer.println("Request param value: " + param);
        writer.println("</br>");
        writer.println("Request attribute: " + request.getAttribute("attr"));


    }
}