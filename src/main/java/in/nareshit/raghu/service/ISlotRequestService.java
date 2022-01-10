package in.nareshit.raghu.service;

import in.nareshit.raghu.entity.SlotRequest;

import java.util.List;

public interface ISlotRequestService {

    //Patient can book slot
    Long saveSlotRequest(SlotRequest sr);

    //Fetch one
    SlotRequest getOneSlotRequest(Long id);

    //Admin can view all slots
    List<SlotRequest> getAllSlotRequest();

    //Admin/Patient can update status
    void updateSlotRequestStatus(Long id,String status);

    //Patient can see his slots
    List<SlotRequest> viewSlotsByPatientMail(String patientMail);

    //Doctor can see his slots
    List<SlotRequest> viewSlotsByDoctorMail(String doctorMail);
}
