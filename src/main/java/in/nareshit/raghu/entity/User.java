package in.nareshit.raghu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_tab")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id_col")
	private Long id;

	@Column(name = "user_display_name_col")
	private String displayName;

	@Column(name = "user_uname_col")
	private String username;

	@Column(name = "user_upwd_col")
	private String password;

	@Column(name = "user_urole_col")
	private String role;

}
