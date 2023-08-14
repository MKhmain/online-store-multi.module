package com.example.web.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.Enumeration;

public class TestServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("This is the Test Servlet");

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = (String) headerNames.nextElement();
            out.print("<br/>Header Name: <em>" + headerName);
            String headerValue = request.getHeader(headerName);
            out.print("</em>, Header Value: <em>" + headerValue);
            out.println("</em>");
        }

        out.println("<hr/>");
        String authHeader = request.getHeader("authorization");
        String encodedValue = authHeader.split(" ")[1];
        out.println("Base64-encoded Authorization Value: <em>" + encodedValue);
        out.println("</em>");
    }

}
