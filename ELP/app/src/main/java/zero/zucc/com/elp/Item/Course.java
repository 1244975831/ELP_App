package zero.zucc.com.elp.Item;

/**
 * Created by Administrator on 2017/7/4.
 */

public class Course {
    String courseName;
    int coursePic;
    String officeNum;
    String adminNum;
    String lessonRecode;
    String courseInfo;
    String courseId;

    public String getLessonRecode() {
        return lessonRecode;
    }

    public void setLessonRecode(String lessonRecode) {
        this.lessonRecode = lessonRecode;
    }


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCoursePic() {
        return coursePic;
    }

    public void setCoursePic(int coursePic) {
        this.coursePic = coursePic;
    }

    public String getOfficeNum() {
        return officeNum;
    }

    public void setOfficeNum(String officeNum) {
        this.officeNum = officeNum;
    }

    public String getAdminNum() {
        return adminNum;
    }

    public void setAdminNum(String adminNum) {
        this.adminNum = adminNum;
    }

    public String getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(String courseInfo) {
        this.courseInfo = courseInfo;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
