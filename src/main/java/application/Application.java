/*******************************************************************************
 * Copyright (c) 2017 IBM Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/ 
package application;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class Application  extends HttpServlet{
	
	@Override
	public void doPost(HttpServletRequest rq,HttpServletResponse rs) throws IOException,ServletException{
		rs.setContentType("text/html");
		PrintWriter out = rs.getWriter();
		String uname = rq.getParameter("uid");
		out.print("<html><body>");
		out.print("Thanks "+uname);
		out.print("</html></body>");
		
		
		/*
		 * below is the sample code for reading headers in a HttpRequest using Servlet.
		 */
		Enumeration<String> enum1 = rq.getHeaderNames();
		while(enum1.hasMoreElements()){
			String headerName = (String)enum1.nextElement();
			String headerValue = rq.getHeader(headerName);
			out.println("<b>"+headerName+"</b>: ");
			out.print(headerValue+"<br />");
		}
		
		
		out.close();
		
		
	}

}
