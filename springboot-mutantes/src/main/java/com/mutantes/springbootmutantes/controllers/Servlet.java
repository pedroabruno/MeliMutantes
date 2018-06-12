package com.mutantes.springbootmutantes.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet extends HttpServlet {
	private static final long serialVersionUID = -1641096228274971485L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		final String CONTENT_TYPE = "text/html; charset=windows-1252";
		
		String var0show = "";
	    try
	    {
	      var0show = request.getParameter("showthis");
	    }
	    catch(Exception e)
	    {
	      e.printStackTrace();
	    }

	    response.setContentType(CONTENT_TYPE);
	    PrintWriter out = response.getWriter();
	    out.println("<html>");
	    out.println("<head><title>demolet</title></head>");
	    out.println("<body>");
	    out.println("<p>The servlet has received a GET. This is the reply.</p>");
	    out.println("</body></html>");
	    out.close();
	}
	
	
}