package fi.metropolia.capslock.dyslexiascreener.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.time.OffsetDateTime;

/**
 * Entity / Data class that represents a single take of the test
 *
 * @author Peetu Saarinen
 */
@Entity(tableName = "tests")
public class Test {
    @PrimaryKey(autoGenerate = true)
    private long id;

    private OffsetDateTime timestamp;

    @ColumnInfo(name = "student_name")
    private String studentName;

    @ColumnInfo(name = "student_age")
    private int studentAge;

    @ColumnInfo(name = "student_points")
    private int studentPoints;

    @ColumnInfo(name = "available_points")
    private int availablePoints;

    public Test(long id, OffsetDateTime timestamp, String studentName, int studentAge, int studentPoints, int availablePoints) {
        this.id = id;
        this.timestamp = timestamp;
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentPoints = studentPoints;
        this.availablePoints = availablePoints;
    }

    @Ignore
    public Test(String studentName, int studentAge, int studentPoints, int availablePoints) {
        this.id = 0;
        this.timestamp = OffsetDateTime.now();
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentPoints = studentPoints;
        this.availablePoints = availablePoints;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(int studentAge) {
        this.studentAge = studentAge;
    }

    public int getStudentPoints() {
        return studentPoints;
    }

    public void setStudentPoints(int studentPoints) {
        this.studentPoints = studentPoints;
    }

    public int getAvailablePoints() {
        return availablePoints;
    }

    public void setAvailablePoints(int availablePoints) {
        this.availablePoints = availablePoints;
    }

    @NonNull
    @Override
    public String toString() {
        return "Test{" +
            "id=" + id +
            ", timestamp=" + timestamp +
            ", studentName='" + studentName + '\'' +
            ", studentAge=" + studentAge +
            ", studentPoints=" + studentPoints +
            ", availablePoints=" + availablePoints +
            '}';
    }
}
