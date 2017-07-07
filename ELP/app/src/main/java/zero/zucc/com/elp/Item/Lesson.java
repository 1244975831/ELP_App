package zero.zucc.com.elp.Item;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/4.
 */

public class Lesson implements Serializable {
    String lessonName;
    String lessonInfo;
    String lessonType;
    String courseNum;


    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getLessonInfo() {
        return lessonInfo;
    }

    public void setLessonInfo(String lessonInfo) {
        this.lessonInfo = lessonInfo;
    }

    public String getLessonType() {
        return lessonType;
    }

    public void setLessonType(String lessonType) {
        this.lessonType = lessonType;
    }

}
