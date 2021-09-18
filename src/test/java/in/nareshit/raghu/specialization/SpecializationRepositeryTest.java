package in.nareshit.raghu.specialization;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import in.nareshit.raghu.entity.Specialization;
import in.nareshit.raghu.repo.SpecializationRepositery;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class SpecializationRepositeryTest {
	
	@Autowired
	private SpecializationRepositery repo;
	
	/**
	 * 1. Test save operation
	 */
	@Test
	@Order(1)
	public void testSpecCreate() {
		Specialization spec = new Specialization(null, "CRDLS", "Cardiologists", "Heart Doctor");
		spec=repo.save(spec);
		assertNotNull("This is not created", spec.getId());
		
		
	}
	
	/**
	 * 2. Test Display all operation
	 */
	
	

}
