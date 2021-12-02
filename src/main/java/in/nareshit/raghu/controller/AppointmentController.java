package in.nareshit.raghu.controller;

import in.nareshit.raghu.entity.Appointment;
import in.nareshit.raghu.entity.Doctor;
import in.nareshit.raghu.exception.AppointmentNotFoundException;
import in.nareshit.raghu.service.IAppointmentService;
import in.nareshit.raghu.service.IDoctorService;
import in.nareshit.raghu.service.ISpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

/**
 * @author:RAGHU SIR
 * Generated F/w:SHWR-Framework
 */
@Controller
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private IAppointmentService service;

    @Autowired
    private IDoctorService doctorservice;

    @Autowired
    private ISpecializationService specializationService;

    private void commonUi(Model model) {
        model.addAttribute("doctors", doctorservice.getDoctorIdAndNames());
    }

    @GetMapping("/register")
    public String registerAppointment(Model model) {
        model.addAttribute("appointment", new Appointment());
        commonUi(model);
        return "AppointmentRegister";
    }

    @PostMapping("/save")
    public String saveAppointment(@ModelAttribute Appointment appointment, Model model) {
        java.lang.Long id = service.saveAppointment(appointment);
        model.addAttribute("message", "Appointment created with Id:" + id);
        model.addAttribute("appointment", new Appointment());
        commonUi(model);
        return "AppointmentRegister";
    }

    @GetMapping("/all")
    public String getAllAppointment(Model model,
                                    @RequestParam(value = "message", required = false) String message) {
        List<Appointment> list = service.getAllAppointments();
        model.addAttribute("list", list);
        model.addAttribute("message", message);
        return "AppointmentData";
    }

    @GetMapping("/delete")
    public String deleteAppointment(@RequestParam Long id, RedirectAttributes attributes) {
        try {
            service.deleteAppointment(id);
            attributes.addAttribute("message", "Appointment deleted with Id:" + id);
        } catch (AppointmentNotFoundException e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
        }
        return "redirect:all";
    }

    //view appointment page
    @GetMapping("/view")
    public String viewSlots(
            @RequestParam(required = false, defaultValue = "0") Long specId,
            Model model
    ) {
        //fetch data for spec dropdown
        Map<Long, String> specMap = specializationService.getSpecIdAndName();
        model.addAttribute("specializations", specMap);

        //if they did not select any specialization
        List<Doctor> doctorList = null;
        String message=null;
        if (specId == 0) {
            doctorList = doctorservice.getAllDoctors();
            message="Result: All Doctors";
        } else {
            doctorList = doctorservice.findDoctorBySpecName(specId);
            message="Result: "+specializationService.getOneSpecialization(specId).getSpecName()+" Doctors";
        }
        model.addAttribute("doctorList", doctorList);
        model.addAttribute("message",message);
        return "AppointmentSearch";
    }
}