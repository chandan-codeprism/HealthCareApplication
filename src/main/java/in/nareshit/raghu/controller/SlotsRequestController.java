package in.nareshit.raghu.controller;

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
        slotRequest.setStatus("PENDING");

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

    @GetMapping("/accept")
    public String updateSlotAccept(@RequestParam Long id){
        requestService.updateSlotRequestStatus(id,"ACCEPTED");
        return "redirect:all";
    }

    @GetMapping("/reject")
    public String updateSlotReject(@RequestParam Long id){
        requestService.updateSlotRequestStatus(id,"REJECTED");
        return "redirect:all";
    }
}
