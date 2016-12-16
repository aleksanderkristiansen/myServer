package model;

/**
 * Variabler til Curriculum oprettes samt deres getters/setters.
 */
public class Curriculum {

    private String school, education;
    private int semester, curriculumID, educationID;

    public Curriculum(){

    }
    public Curriculum(int curriculumID, String school, String education, int semester) {
        this.curriculumID = curriculumID;
        this.school = school;
        this.education = education;
        this.semester = semester;
    }

    public Curriculum( String school, String education, int semester) {
        this.school = school;
        this.education = education;
        this.semester = semester;
    }

    public Curriculum(int educationID, int semester){
        this.educationID = educationID;
        this.semester = semester;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getCurriculumID() {
        return curriculumID;
    }

    public void setCurriculumID(int curriculumID) {
        this.curriculumID = curriculumID;
    }

    public int getEducationID() {
        return educationID;
    }

    public void setEducationID(int educationID) {
        this.educationID = educationID;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Curriculum{" +
                "Curriculum ID=" + curriculumID + '\'' +
                "school='" + school + '\'' +
                ", education='" + education + '\'' +
                ", semester=" + semester +
                '}';
    }


}
