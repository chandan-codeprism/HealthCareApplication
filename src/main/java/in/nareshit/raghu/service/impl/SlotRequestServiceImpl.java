package in.nareshit.raghu.service.impl;

import in.nareshit.raghu.entity.SlotRequest;
import in.nareshit.raghu.repo.SlotRequestRepository;
import in.nareshit.raghu.service.ISlotRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SlotRequestServiceImpl implements ISlotRequestService {

    @Autowired
    private SlotRequestRepository repository;

    @Override
    public Long saveSlotRequest(SlotRequest sr) {
        return repository.save(sr).getId();
    }

    @Override
    public List<SlotRequest> getAllSlotRequest() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void updateSlotRequestStatus(Long id, String status) {
        repository.updateSlotRequestStatus(id,status);
    }

    @Override
    public List<SlotRequest> viewSlotsByPatientMail(String patientMail) {
        return repository.getAllPatientSlots(patientMail);
    }
}
