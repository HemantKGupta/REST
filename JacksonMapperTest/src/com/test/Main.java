package com.test;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.domain.Permission;

public class Main {

	public static void main(String[] args) {
		File f = new File("/media/Hemant/GitRepos/REST/JacksonMapperTest/src/permissions.json");
		ObjectMapper mapper = new ObjectMapper();
		try {
			Permission perm = mapper.readValue(f, Permission.class);
			List<Permission> myObjects = mapper.readValue(f, new TypeReference<List<Permission>>(){});
			System.out.println(myObjects.size());
			//System.out.println(myObjects);
			
			/*JsonFactory jsonfactory = new JsonFactory();

			JsonParser jsonParser = jsonfactory
					.createParser(f);
			JsonToken token;

			 while ( (token = jsonParser.nextToken()) != null) {
				 switch (token) {
			        case START_OBJECT:
			        	Permission perm = jsonParser.readValueAs(Permission.class);
			        	System.out.println(perm.getId());
			             break;
				 }
			 }*/
			
			/* for (Iterator<Permission> it = new ObjectMapper().readValues(new JsonFactory().createParser(f), new TypeReference<Permission>(){}); it.hasNext();)
				    System.out.println(((Permission)it.next()).getId());
			*/
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
