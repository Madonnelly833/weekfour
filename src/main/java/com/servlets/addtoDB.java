package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thezoo.Animal;
import com.thezoo.DOA;

/**
 * Servlet implementation class addtoDB
 */
@WebServlet("/addtoDB")
public class addtoDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addtoDB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		double animalWeight = Double.parseDouble(request.getParameter("weight"));
        int cageID = Integer.parseInt(request.getParameter("cage_number"));
		
			
			Animal addToDB = new Animal();
			addToDB.setAnimalName(request.getParameter("animal_name"));
			addToDB.setAnimalSpecies(request.getParameter("animal_species"));
			addToDB.setAnimalColor(request.getParameter("animal_color"));
			addToDB.setAnimalFood(request.getParameter("animal_food"));
			addToDB.setWeight(animalWeight);
			addToDB.setCageNumber(cageID);
			addToDB.setImageLocation(request.getParameter("image_location"));
			
			System.out.println(addToDB.toString());
			
			DOA.writeToDB(addToDB);
			
		}//dopost
	}


