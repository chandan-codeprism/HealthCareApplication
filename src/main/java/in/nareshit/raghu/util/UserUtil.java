package in.nareshit.raghu.util;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class UserUtil {
	
	public String genPwd() {
		
		return UUID.randomUUID()
		.toString()
		.replace("_", "")
		.substring(0, 8);
		
	}
}
