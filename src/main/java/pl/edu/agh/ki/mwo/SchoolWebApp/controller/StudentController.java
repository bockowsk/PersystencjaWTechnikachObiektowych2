package pl.edu.agh.ki.mwo.SchoolWebApp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.edu.agh.ki.mwo.SchoolWebApp.repository.SchoolClassRepository;
import pl.edu.agh.ki.mwo.SchoolWebApp.repository.StudentRepository;

@Controller
public class StudentController {

		@Autowired
		private StudentRepository studentRepository;
		@Autowired 
		private SchoolClassRepository schoolClassRepository;
		
		@RequestMapping(value="/Students")
		public String listStudents(Model model, HttpSession session) {
	    	if (session.getAttribute("userLogin") == null)
	    		return "redirect:/Login";

	    	model.addAttribute("students", studentRepository.findAll());
	    	return "studentsList";
		}
		
	    @RequestMapping(value="/DeleteStudent", method=RequestMethod.POST)
	    public String deleteStudent(@RequestParam(value="studentId", required=false) String studentId,
	    		Model model, HttpSession session) {    	
	    	if (session.getAttribute("userLogin") == null)
	    		return "redirect:/Login";
	    	
	    	//DatabaseConnector.getInstance().deleteSchool(schoolId);
	    	studentRepository.deleteById(Long.valueOf(studentId));
	    	
	       	model.addAttribute("students", studentRepository.findAll());
	    	model.addAttribute("message", "Student zostal dodany");
	         	
	    	return "studentsList";
	    }
	    
	    @RequestMapping(value="/AddStudent")
	    public String displayAddStudentForm(Model model, HttpSession session) {    	
	    	if (session.getAttribute("userLogin") == null)
	    		return "redirect:/Login";
	    	model.addAttribute("classes", schoolClassRepository.findAll());
	        return "studentForm";    
	    }
}
