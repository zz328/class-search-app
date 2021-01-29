import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;


import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("JHU - WSE - CS Dpt.");
        System.out.println("Spring 2021");
        System.out.println("Class Search");
        System.out.println("Enter your search query");
        String query = input.nextLine();

        search(query);
    }

    private static void search(String query) {
        // check for empty query
        if (query.length() == 0) {
            System.out.println("Error: Search query is empty");
            return;
        }

        HashSet<Course> coursesMap = new HashSet<>();
        final String BASE_URL = "https://sis.jhu.edu/api/classes/";
        final String KEY = System.getenv("JHU_API_KEY");

        // get class information
        String endpoint = BASE_URL + "/Whiting%20School%20of%20Engineering"
                + "/EN%20Computer%20Science"
                + "/current?key=" + KEY;

        HttpResponse<JsonNode> jsonResponse = Unirest.get(endpoint).asJson();
        String allClasses = jsonResponse.getBody().toString();

        String[] parseClasses = allClasses.split("}");

        // go through and find all matching classes
        for (String check : parseClasses) {
            int start = check.indexOf("Title\":\"");
            int end = check.indexOf("\",\"Credits\"");

            if (start == -1 || end == -1) {
                break;
            }

            start += 8;
            String courseTitle = check.substring(start, end);

            if (courseTitle.toLowerCase().contains(query.toLowerCase())) {
                start = check.indexOf("OfferingName\":\"");
                end = check.indexOf("\",\"SectionName");
                start += 15;
                String courseNum = check.substring(start, end);

                Course c = new Course(courseTitle, courseNum);
                coursesMap.add(c);
            }
        }

        // print out the courses
        Iterator<Course> itr = coursesMap.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }

        if (coursesMap.size() == 0) {
            System.out.println("No course title contains the entered search query!");
        }

    }
}
