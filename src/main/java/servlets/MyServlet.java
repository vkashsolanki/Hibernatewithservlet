package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import bean.Student;


public class MyServlet extends HttpServlet 
{

    
    public MyServlet() {
        super();
        
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String mobile=request.getParameter("mobile");
		String pass=request.getParameter("pass");
		System.out.println(name+" "+email+" "+mobile+" "+pass);
		
		try
		{
			Configuration configuration=new Configuration();
			configuration.configure("config/hibernate.cfg.xml");
			SessionFactory sessionFactory=configuration.buildSessionFactory();
			Session session=sessionFactory.openSession();
			System.out.println(session);
			
			Transaction transaction=session.beginTransaction();
			Student st=new Student(0,name,email,mobile,pass);
			session.save(st);
			transaction.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


}
