public class Course {
    private String title;
    private String courseNum;

    public Course(String t, String cn) {
        title = t;
        courseNum = cn;
    }

    public String getTitle() {
        return title;
    }

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
