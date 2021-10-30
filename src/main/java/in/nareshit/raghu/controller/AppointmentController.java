package in.nareshit.raghu.controller;

import in.nareshit.raghu.entity.Appointment;
import in.nareshit.raghu.exception.AppointmentNotFoundException;
import in.nareshit.raghu.service.IAppointmentService;
import in.nareshit.raghu.service.IDoctorService;

import java.lang.Long;
import java.lang.String;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author:RAGHU SIR 
 *  Generated F/w:SHWR-Framework 
 */
@Controller
@RequestMapping("/appointment")
public class AppointmentController {
	@Autowired
	private IAppointmentService service;
	
	@Autowired
	private IDoctorService doctorservice;
	
	private void commonUi(Model model) {
		model.addAttribute("doctors", doctorservice.getDoctorIdAndNames());
	}

	@GetMapping("/register")
	public String registerAppointment(Model model) {
		model.addAttribute("appointment",new Appointment());
		commonUi(model);
		return "AppointmentRegister";
	}

	@PostMapping("/save")
	public String saveAppointment(@ModelAttribute Appointment appointment, Model model) {
		java.lang.Long id=service.saveAppointment(appointment);
		model.addAttribute("message","Appointment created with Id:"+id);
		model.addAttribute("appointment",new Appointment()) ;
		commonUi(model);
		return "AppointmentRegister";
	}

	@GetMapping("/all")
	public String getAllAppointment(Model model,
			@RequestParam(value = "message", required = false) String message) {
		List<Appointment> list=service.getAllAppointments();
		model.addAttribute("list",list);
		model.addAttribute("message",message);
		return "AppointmenttData";
	}

	@GetMapping("/delete")
	public String deleteAppointment(@RequestParam Long id, RedirectAttributes attributes) {
		try {
			service.deleteAppointment(id);
			attributes.addAttribute("message","Appointment deleted with Id:"+id);
		} catch(AppointmentNotFoundException e) {
			e.printStackTrace() ;
			attributes.addAttribute("message",e.getMessage());
		}
		return "redirect:all";
		
	}
}