package in.nareshit.raghu.specialization;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
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
@TestMethodOrder(OrderAnnotation.class)
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
	
	@Test
	@Order(2)
	public void testSpecFetchAll() {
		
		List<Specialization> list=repo.findAll();
		assertNotNull(list);
		if(list.isEmpty()) {
			fail("No data exit in Database");
		}
		
	}

}
