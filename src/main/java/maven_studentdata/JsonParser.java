package maven_studentdata;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class JsonParser {

	public static List<Student> parseJSOn(String url) throws ParseException {
		List<Student> studentList = new ArrayList<Student>();
		Client client = Client.create();
		WebResource webResource = client.resource(url);

		ClientResponse clientResponse = webResource.accept("application/json").get(ClientResponse.class);
		if (clientResponse.getStatus() != 200) {
			throw new RuntimeException("Failed" + clientResponse.toString());
		}
		JSONArray jsonArray = (JSONArray) new JSONParser().parse(clientResponse.getEntity(String.class));

		Iterator<Object> it = jsonArray.iterator();
		String firstName;
		String gender;
		String email;
		double gpa;

		while (it.hasNext()) {
			JSONObject jsonObject = (JSONObject) it.next();
			firstName = (String) jsonObject.get("first_name");
			gender = (String) jsonObject.get("gender");
			email = (String) jsonObject.get("email");
			gpa = Double.parseDouble((String) jsonObject.get("gpa"));
			studentList.add(new Student(firstName, gender, email, gpa));
		}
		return studentList;
	}

	public static int search(List<Student> list, String value) {
		int size;
		int index = 99;
		size = list.size();
		for (int i = 0; i < size; i++) {

			if (list.get(i).getFirst_name().equalsIgnoreCase(value))
				index = i;

		}
		return index;

	}

	public static void main(String[] args) throws ParseException {

		List<Student> studentList = parseJSOn("https://hccs-advancejava.s3.amazonaws.com/student.json");

		for (Student student : studentList) {
			System.out.println(student.toString());

		}
		int flag = 1;

		do {
			System.out.println("Please enter the name of the Student you would like to search for: ");
			Scanner sc = new Scanner(System.in);
			String name1 = sc.nextLine();
			int check;
			check = search(studentList, name1);
			if (check != 99) {
				System.out.println(studentList.get(check).toString());
				flag = 0;
				break;
			} else
				System.out.println("No Student Found!");

		} while (flag == 1);
	}
	
}
