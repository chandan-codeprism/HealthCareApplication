package in.nareshit.raghu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.nareshit.raghu.entity.Doctor;
import in.nareshit.raghu.exception.DoctorNotFoundException;
import in.nareshit.raghu.service.IDoctorService;
import in.nareshit.raghu.view.DoctorExcelView;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
	
	@Autowired
	private IDoctorService service;
	
	/*
	 * 1. Show Register Page
	 */
	@GetMapping("/register")
	public String showReg(
			@RequestParam(value = "message", required = false)String message,
			Model model
			) 
	{
		model.addAttribute("message", message);
		return "DoctorRegister";	
		}
	
	/*
	 * 2. Save on submit
	 */
	@PostMapping("/save")
	public String save(
			@ModelAttribute Doctor doctor,
			//@RequestParam("docImg") MultipartFile multipartFile,//For LocalImg upload
			RedirectAttributes atrAttributes
			) 
	{
		//String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		//doctor.setPhotos(fileName);
		
		Long id = service.savaDoctor(doctor);
		atrAttributes.addAttribute("message", "Doctor ("+id+") is created");
		
		/*
		 * String uploadDir = "user-photos/" + id; try {
		 * FileUploadUtil.saveFile(uploadDir, fileName, multipartFile); } catch
		 * (IOException e) { e.printStackTrace(); }
		 */
		
		return "redirect:register";
	}
	
	/*
	 * 3. Display data
	 */
	@GetMapping("/all")
	public String display(
			Model model,
			@RequestParam(value = "message", required = false)String message
			) 
	{
		List<Doctor> list=service.getAllDoctors();
		model.addAttribute("list",list);
		model.addAttribute("message", message);
		return "DoctorData";
	}
	
	/*
	 * 4. Delete by id
	 */
	@GetMapping("/delete")
	public String delete(
			@RequestParam("id")Long id,
			RedirectAttributes attributes
			)
	{
		String message = null;
		try {
			service.removeDoctor(id);
			message = "Doctor removed";
		} catch (DoctorNotFoundException e) {
			e.printStackTrace();
			message = e.getMessage();
		}
		attributes.addAttribute("message", message);
		return "redirect:all";
	}
	
	/*
	 * 5. Edit Doctor
	 */
	@GetMapping("/edit")
	public String edit(
			@RequestParam("id")Long id,
			Model model,
			RedirectAttributes attributes
			)
	{
		String page = null;
		try {
			Doctor doc = service.getOneDoctor(id);
			model.addAttribute("doctor", doc);
			page = "DoctorEdit";
		} catch (DoctorNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			page = "redirect:all";
		}
		return page;
	}
	
	/*
	 * 6. do update
	 */
	@PostMapping("/update")
	public String doUpdate(
			@ModelAttribute Doctor doctor,
			RedirectAttributes attributes
			) 
	{
		service.updateDoctor(doctor);
		attributes.addAttribute("message", doctor.getId()+", updated!");
		return "redirect:all";
	}
	
	/**
	 * 7. Read first name and check with service
	 * 	  Return message back to UI
	 */
	@GetMapping("/checkFirstName")
	@ResponseBody
	public String validateFirstName(
			@RequestParam String firstname,
			@RequestParam Long id
			) 
	{
		String message = "";
		if(id==0 && service.isFirstNameExist(firstname)) {//register check
			message=firstname + ", is already exist";
		}else if(id!=0 && service.isFirstNameExistForEdit(firstname,id)) {//edit check
			message=firstname + ", is already exist";
		}
		return message;//This is not viewName(it is message)
	}
	
	/**
	 * 8. Read last name and check with service
	 * 	  Return message back to UI
	 */
	@GetMapping("/checkLastName")
	@ResponseBody
	public String validateLastName(
			@RequestParam String lastname,
			@RequestParam Long id
			) 
	{
		String message = "";
		if(id==0 && service.isLastNameExist(lastname)) {//register check
			message=lastname + ", is already exist";
		}else if(id!=0 && service.isLastNameExistForEdit(lastname,id)) {//edit check
			message=lastname + ", is already exist";
		}
		return message;//This is not viewName(it is message)
	}
	
	/**
	 * 9. Read email and check with service
	 * 	  Return message back to UI
	 */
	@GetMapping("/checkEmail")
	@ResponseBody
	public String validateEmail(
			@RequestParam String emailid,
			@RequestParam Long id
			) 
	{
		String message = "";
		if(id==0 && service.isEmailExist(emailid)) {//register check
			message=emailid + ", is already exist";
		}else if(id!=0 && service.isEmailExistForEdit(emailid,id)) {//edit check
			message=emailid + ", is already exist";
		}
		return message;//This is not viewName(it is message)
	}
	
	/**
	 * 10. Read address and check with service
	 * 	  Return message back to UI
	 */
	@GetMapping("/checkAddress")
	@ResponseBody
	public String validateAddress(
			@RequestParam String addr,
			@RequestParam Long id
			) 
	{
		String message = "";
		if(id==0 && service.isAddressExist(addr)) {//register check
			message=addr + ", is already exist";
		}else if(id!=0 && service.isAddressExistForEdit(addr,id)) {//edit check
			message=addr + ", is already exist";
		}
		return message;//This is not viewName(it is message)
	}
	
	/**
	 * 10. Read mobile and check with service
	 * 	  Return message back to UI
	 */
	@GetMapping("/checkMobile")
	@ResponseBody
	public String validateMobile(
			@RequestParam String Number,
			@RequestParam Long id
			) 
	{
		String message = "";
		if(id==0 && service.isMobileExist(Number)) {//register check
			message=Number + ", is already exist";
		}else if(id!=0 && service.isMobileExistForEdit(Number,id)) {//edit check
			message=Number + ", is already exist";
		}
		return message;//This is not viewName(it is message)
	}
	
	/**
	 * 11. Read note and check with service
	 * 	  Return message back to UI
	 */
	@GetMapping("/checkNote")
	@ResponseBody
	public String validateNote(
			@RequestParam String Info,
			@RequestParam Long id
			) 
	{
		String message = "";
		if(id==0 && service.isNoteExist(Info)) {//register check
			message=Info + ", is already exist";
		}else if(id!=0 && service.isNoteExistForEdit(Info,id)) {//edit check
			message=Info + ", is already exist";
		}
		return message;//This is not viewName(it is message)
	}
	
	/**
	 * 12. File Excel Export
	 * 
	 */
	@GetMapping("/excel")
	public ModelAndView exportToExcel() {
		ModelAndView m =  new ModelAndView();
		m.setView(new DoctorExcelView());
		
		//read data from DB
		List<Doctor> list = service.getAllDoctors();
		//send to Excel Implements class
		m.addObject("list", list);
		
		return m;
	}

}
