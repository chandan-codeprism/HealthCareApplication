package in.nareshit.raghu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "doctor_tab")
public class Doctor {

	@Id
	@Column(name = "doc_fn_id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "doc_fn_col", nullable = false, unique = true)
	private String firstName;
	@Column(name = "doc_ln_col", nullable = false, unique = true)
	private String lastName;
	@Column(name = "doc_mail_col", nullable = false, unique = true)
	private String email;
	@Column(name = "doc_addr_col", nullable = false, unique = true)
	private String address;
	@Column(name = "doc_mob_col", nullable = false, unique = true)
	private String mobile;
	@Column(name = "doc_gen_col", nullable = true, unique = false)
	private String gender;
	@Column(name = "doc_note_col", nullable = false, unique = true)
	private String note;

	@Column(name = "doc_img_col")
	private String photoLoc;

	// ---------------Association Mapping------------------//

	@ManyToOne
	@JoinColumn(name = "spec_id_fk_col")
	private Specialization specialization;// HAS-A

}