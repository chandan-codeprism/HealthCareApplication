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

import in.nareshit.raghu.entity.Specialization;
import in.nareshit.raghu.exception.SpecializationNotFoundException;
import in.nareshit.raghu.service.ISpecializationService;
import in.nareshit.raghu.view.SpecializationExcelView;


@Controller
@RequestMapping("/spec")
public class SpecializationController {
	
	@Autowired
	private ISpecializationService service;
	
	/**
	 * 1. Show Register page
	 */
	@GetMapping("/register")
	public String displayRegister() {
		return "SpecializationRegister";
	}
	
	/**
	 * 2. On Submit Form save data
	 */
	@PostMapping("/save")
	public String saveForm(
			@ModelAttribute Specialization specialization,
			Model model
			) 
	{
		Long id=service.savaSpecialization(specialization);
		String message="Record ("+id+") is created";
		model.addAttribute("message", message);
		return "SpecializationRegister";
	}
	
	/**
	 * 3. Display all Specializations
	 */
	
	@GetMapping("/all")
	public String viewAll(
			Model model,
			@RequestParam(value = "message",required = false) String message
			) 
	{
		List<Specialization> list=service.getAllSpecialization();
		model.addAttribute("list",list);
		model.addAttribute("message", message);
		return "SpecializationData";
	}
	
	/**
	 * 4. Delete by Id
	 */
	@GetMapping("/delete")
	public String deleteData(
			@RequestParam Long id,
			RedirectAttributes attributes
			) 
	{
		try {
			service.removeSpecialization(id);
			attributes.addAttribute("message", "Record ("+id+") is removed");
			
		} catch (SpecializationNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message",e.getMessage());
		}
		return "redirect:all";
	}
	
	/**
	 * 5. Fetch Data into Edit page
	 * End user clicks on link, may enter ID manually.
	 * 
	 * If enter ID is present in DB
	 * 	>Load row as object
	 * 	>Send to edit page
	 * 	>Fill in form
	 * 
	 * Else
	 * 
	 * 	>Redirect to all(Data page)
	 * 	>Show error message(Not Found)
	 *	
	 */
	@GetMapping("/edit")
	public String showEditPage(
			@RequestParam Long id,
			Model model,
			RedirectAttributes attributes
			
			) 
	{
			String page=null;
		try {
			Specialization spec=service.getOneSpecialization(id);
			model.addAttribute("specialization", spec);
			page="SpecializationEdit";
		} catch (SpecializationNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message",e.getMessage());
			page="redirect:all";
			
		}
		
		return page;
	}
	
	/**
	 * 6. Update form data and redirect to all
	 */
	@PostMapping("/update")
	public String updateData(
			@ModelAttribute Specialization specialization,
			RedirectAttributes attributes
			) 
	{
		service.updateSpecialization(specialization);
		attributes.addAttribute("message","Record ("+specialization.getId()+") is updated");
		return "redirect:all";
	}
	
	/**
	 * 7. Read code and check with service
	 * 	  Return message back to UI
	 */
	@GetMapping("/checkCode")
	@ResponseBody
	public String validateSpecCode(
			@RequestParam String code,
			@RequestParam Long id
			) 
	{
		String message = "";
		if(id==0 && service.isspecCodeExist(code)) {//register check
			message=code + ", is already exist";
		}else if(id!=0 && service.isSpecCodeExistForEdit(code,id)) {//edit check
			message=code + ", is already exist";
		}
		return message;//This is not viewName(it is message)
	}
	
	/**
	 * 8. Read Name and check with service
	 * Return message back to UI page
	 */
	@GetMapping("/checkName")
	@ResponseBody
	public String validatespecName(
			@RequestParam String name,
			@RequestParam Long id
			) 
	{
		String message="";
		if(id==0 && service.isspecNameExist(name)) {
			message=name + ", is already exist";
		}else if(id!=0 && service.isSpecNameExistForEdit(name, id)) {
			message=name + ", is already exist";
		}
		return message;
	}
	
	/**
	 * 9. Read Note and check with service
	 * Return message back to UI page
	 */
	@GetMapping("/checkNote")
	@ResponseBody
	public String validatespecNote(
			@RequestParam String note,
			@RequestParam Long id
			) 
	{
		String message="";
		if(id==0 && service.isspecNoteExist(note)) {
			message=note + ", is already exist";
		}else if(id!=0 && service.isSpecNoteExistForEdit(note, id)) {
			message=note + ", is already exist";
		}
		return message;
	}
	
	/**
	 * 10. Export data excel to file
	 */
	@GetMapping("/excel")
	public ModelAndView exportToExcel() {
		ModelAndView m = new ModelAndView();
		m.setView(new SpecializationExcelView());
		
		List<Specialization> list = service.getAllSpecialization();
		m.addObject("list", list);
		return m;
	}
	

}
