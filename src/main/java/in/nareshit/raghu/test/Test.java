package in.nareshit.raghu.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test {
	public static void main(String[] args) {
		//JDK 9
		List<Object[]> list = List.of(
				new Object[] {10,"AA"},
				new Object[] {11,"BB"},
				new Object[] {12,"CC"},
				new Object[] {13,"DD"},
				new Object[] {14,"EE"}
				);

		//Java 8 Streams
		Map<Long,String> map =
				list
				.stream()
				.collect(Collectors.toMap(
						ob->Long.valueOf(
								ob[0].toString()), 
						ob->ob[1].toString()));
		//for Each
		Map<Long,String> map2 = new HashMap<>();
		for(Object[] ob:list) {
			map2.put(
					Long.valueOf(ob[0].toString()), 
					ob[1].toString());
		}
	}
}

