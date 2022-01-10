package in.nareshit.raghu.controller;

import in.nareshit.raghu.constants.SlotStatus;
import in.nareshit.raghu.entity.Appointment;
import in.nareshit.raghu.entity.Patient;
import in.nareshit.raghu.entity.SlotRequest;
import in.nareshit.raghu.entity.User;
import in.nareshit.raghu.service.IAppointmentService;
import in.nareshit.raghu.service.IPatientService;
import in.nareshit.raghu.service.ISlotRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/slots")
public class SlotsRequestController {

    @Autowired
    private ISlotRequestService requestService;

    @Autowired
    private IAppointmentService appointmentService;

    @Autowired
    private IPatientService patientService;

    //patient id, appointment id
    @GetMapping("/book")
    public String bookSlot(
            @RequestParam Long appId,
            HttpSession session,
            Model model
    ) {
        Appointment appointment = appointmentService.getOneAppointment(appId);
        //For patient object
        User user = (User) session.getAttribute("userOb");
        String email = user.getUsername();
        Patient patient = patientService.getOnePatientByEmail(email);

        //Create Slots Object
        SlotRequest slotRequest = new SlotRequest();
        slotRequest.setAppointment(appointment);
        slotRequest.setPatient(patient);
        slotRequest.setStatus(SlotStatus.PENDING.name());

        try {
            requestService.saveSlotRequest(slotRequest);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMM/yyyy");
            String appointmentDate = simpleDateFormat.format(appointment.getDate());
            String message = "Patient " + (patient.getFirstName() + " " + patient.getLastName())
                    + " Request for Dr. " + appointment.getDoctor().getFirstName() + " " + appointment.getDoctor().getLastName()
                    + " on date: " + appointmentDate + " submitted with status: " + slotRequest.getStatus();
            model.addAttribute("message", message);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "BOOKING REQUEST ALREADY MADE FOR THIS APPOINTMENT/DATE");
        }
        return "SlotRequestMessage";
    }


    @GetMapping("/all")
    public String viewAllReq(Model model) {
        List<SlotRequest> list = requestService.getAllSlotRequest();
        model.addAttribute("list", list);
        return "SlotRequestData";
    }

    @GetMapping("/patient")
    public String viewMySlotReqPatient(
            Model model, Principal principal) {
        String email = principal.getName();
        List<SlotRequest> list = requestService.viewSlotsByPatientMail(email);
        model.addAttribute("list", list);
        return "SlotRequestPatientData";
    }

    @GetMapping("/doctor")
    public String viewMySlotReqDoctor(
            Model model, Principal principal) {
        String email = principal.getName();
        List<SlotRequest> list = requestService.viewSlotsByDoctorMail(email);
        model.addAttribute("list", list);
        return "SlotRequestDoctorData";
    }

    @GetMapping("/accept")
    public String updateSlotAccept(@RequestParam Long id) {
        requestService.updateSlotRequestStatus(id, SlotStatus.ACCEPTED.name());
        SlotRequest slotRequest = requestService.getOneSlotRequest(id);
        if (slotRequest.getStatus().equals(SlotStatus.ACCEPTED.name())) {
            appointmentService.updateSlotCountForAppointments(slotRequest.getAppointment().getId(), -1);
        }
        return "redirect:all";
    }

    @GetMapping("/reject")
    public String updateSlotReject(@RequestParam Long id) {
        requestService.updateSlotRequestStatus(id, SlotStatus.REJECTED.name());
        return "redirect:all";
    }

    @GetMapping("/cancel")
    public String cancelSlotRequest(@RequestParam Long id) {
        SlotRequest slotRequest = requestService.getOneSlotRequest(id);
        if (slotRequest.getStatus().equals(SlotStatus.ACCEPTED.name())) {
            requestService.updateSlotRequestStatus(id, SlotStatus.CANCELLED.name());
            appointmentService.updateSlotCountForAppointments(slotRequest.getAppointment().getId(), 1);
        }
        return "redirect:patient";
    }
}
