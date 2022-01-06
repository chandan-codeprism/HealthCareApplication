package in.nareshit.raghu.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "slot_req_tab",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = {"app_id_fk_col", "patient_id_fk_col"})
})
public class SlotRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "slot_id_col")
    private Long id;

    @OneToOne
    @JoinColumn(name = "app_id_fk_col")
    private Appointment appointment;

    @OneToOne
    @JoinColumn(name = "patient_id_fk_col")
    private Patient patient;

    @Column(name = "slot_status_col")
    private String status;
}
