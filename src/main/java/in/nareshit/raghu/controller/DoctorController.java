package in.nareshit.raghu.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.nareshit.raghu.entity.Doctor;
import in.nareshit.raghu.exception.DoctorNotFoundException;
import in.nareshit.raghu.service.IDoctorService;
import in.nareshit.raghu.util.FileUploadUtil;

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
			@RequestParam("docImg") MultipartFile multipartFile,//For LocalImg upload
			RedirectAttributes atrAttributes
			) 
	{
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		doctor.setPhotos(fileName);
		
		Long id = service.savaDoctor(doctor);
		atrAttributes.addAttribute("message", "Doctor ("+id+") is created");
		
		String uploadDir = "user-photos/" + id;
		try {
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
			@RequestParam String firstname
			) 
	{
		String message = "";
		if(service.isFirstNameExist(firstname)) {
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
			@RequestParam String lastname
			) 
	{
		String message = "";
		if(service.isLastNameExist(lastname)) {
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
			@RequestParam String emailid
			) 
	{
		String message = "";
		if(service.isEmailExist(emailid)) {
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
			@RequestParam String addr
			) 
	{
		String message = "";
		if(service.isAddressExist(addr)) {
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
			@RequestParam String Number
			) 
	{
		String message = "";
		if(service.isMobileExist(Number)) {
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
			@RequestParam String Info
			) 
	{
		String message = "";
		if(service.isNoteExist(Info)) {
			message=Info + ", is already exist";
		}
		return message;//This is not viewName(it is message)
	}

}
