package redflag.capstone.com.redflag.database;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by Anshu on 10/20/14.
 */
public class Records {
    private int recordId;
    private int studentId;
    private int tesId;
    private Date lastModifiedDate;
    private Time lastModifiedTime;

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getTesId() {
        return tesId;
    }

    public void setTesId(int tesId) {
        this.tesId = tesId;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Time getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Time lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }
}
