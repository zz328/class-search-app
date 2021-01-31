/*
Course class that represents one course at JHU
 */
public class Course {
    private String title;
    private String courseNum;

    /* constructor
    @param t - title
    @param cn - course number
     */
    public Course(String t, String cn) {
        title = t;
        courseNum = cn;
    }

    /* @return Title of the course */
    public String getTitle() {
        return title;
    }

    /* @return the associated course number */
    public String getCourseNumber() {
        return courseNum;
    }

    @Override
    public String toString() {
        return courseNum + ' ' + title;
    }

    @Override
    public boolean equals(Object o) {
        Course c = (Course) o;
        return c.getCourseNumber().equals(courseNum) && c.getTitle().equals(title);
    }

}
