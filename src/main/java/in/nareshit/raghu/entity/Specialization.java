package in.nareshit.raghu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="specialization_tab")
public class Specialization {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="spec_id_col")
	private Long id;
	@Column(
			name="spec_code_col",
			length=10,
			nullable=false,
			unique = true
			)
	private String specCode;
	@Column(
			name="spec_name_col",
			length = 60,
			nullable = false,
			unique = true
			)
	private String specName;
	@Column(name="spec_note_col",
			length = 250,
			nullable = false,
			unique = true)
	private String specNote;
	public Specialization(Long id, String specCode, String specName, String specNote) {
		super();
		this.id = id;
		this.specCode = specCode;
		this.specName = specName;
		this.specNote = specNote;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSpecCode() {
		return specCode;
	}
	public void setSpecCode(String specCode) {
		this.specCode = specCode;
	}
	public String getSpecName() {
		return specName;
	}
	public void setSpecName(String specName) {
		this.specName = specName;
	}
	public String getSpecNote() {
		return specNote;
	}
	public void setSpecNote(String specNote) {
		this.specNote = specNote;
	}
	@Override
	public String toString() {
		return "Specialization [id=" + id + ", specCode=" + specCode + ", specName=" + specName + ", specNote="
				+ specNote + "]";
	}
	
}
