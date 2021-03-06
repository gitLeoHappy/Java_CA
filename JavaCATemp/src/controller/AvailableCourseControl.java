package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.NotFoundException;
import model.Course;
import model.Enrollment;
import service.CourseService;
import service.EnrollmentService;

/**
 * Servlet implementation class AvailableCourseControl
 */
@WebServlet("/availablecourse")
public class AvailableCourseControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AvailableCourseControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request,response);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request,response);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
private void process(HttpServletRequest request, HttpServletResponse response) throws NotFoundException {
		
		//String sId=request.getParameter("id");
		String sId="S0002";
		
		EnrollmentService es=new EnrollmentService();
		CourseService cs=new CourseService();
		
		ArrayList<Enrollment> enrollList = es.findEnrollment();			
		ArrayList<Course> data=cs.findCourses();
		
		ArrayList<Course> result=cs.findCourses();
		//Date today=DateTime.now();
		
		for (Enrollment e : enrollList) {			
			
			if(e.getStudentId().equals(sId)){				
				Course c=cs.findCourseById(e.getCourseId());
				
				for (Course course : data) {
					
					if(course.getCourseId().equals(c.getCourseId())){
						System.out.println(course);
						int index=data.indexOf(course);						
						System.out.println(result.remove(index));
					}
				}				
			}
		}
		
		request.setAttribute("course", result);
		RequestDispatcher rd = request.getRequestDispatcher("/views/Student/EnrollFor.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
