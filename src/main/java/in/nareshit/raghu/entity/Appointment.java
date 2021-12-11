package in.nareshit.raghu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author:RAGHU SIR Generated F/w:SHWR-Framework
 */
@Entity
@Table(name = "appointment_tab")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @ManyToOne
    @JoinColumn(name = "app_doc_id_fk_col")
    public Doctor doctor;

    //-----------Association Mapping-------------//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "app_dte_col")
    private Date date;

    @Column(name = "app_slots_col")
    private Integer noOfSlots;

    @Column(name = "app_dtls_col")
    private String details;

    @Column(name = "app_amt_col")
    private Double fee;
}