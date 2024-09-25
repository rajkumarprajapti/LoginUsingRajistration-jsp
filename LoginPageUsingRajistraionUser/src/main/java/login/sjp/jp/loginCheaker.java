package login.sjp.jp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("loginform")
public class loginCheaker extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	PrintWriter out = resp.getWriter();
	String name = req.getParameter("email1");
	String pass = req.getParameter("pass1");
	
	try {
		Class.forName("com.cj.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rajistration","root","root");
		
		PreparedStatement ps = con.prepareStatement("select * from rajister where email = ? and pass = ?");
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			HttpSession session = req.getSession();
			session.setAttribute("session_name", rs.getString("name"));
			RequestDispatcher rd = req.getRequestDispatcher("/Loginsuccess.jsp");
			rd.include(req, resp);
		}else {
			out.print("<h2 style = 'color:red'> incorrect user name or password");
			RequestDispatcher rd = req.getRequestDispatcher("/Login.jsp");
			rd.include(req, resp);
			
			
		}
		
	}catch(Exception e) {
		e.printStackTrace();
		
		out.print("<h2 style = 'color:red'> incorrect user name or password");
		RequestDispatcher rd = req.getRequestDispatcher("/Login.jsp");
		rd.include(req, resp);
	}
	
	
	
	
	}
	

}
