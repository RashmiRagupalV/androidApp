package redflag.capstone.com.redflag.database;

/**
 * Created by Anshu on 10/20/14.
 */
public class Classroom {
    private int classroomId;
    private int grade;
    private String section;

    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
