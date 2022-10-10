package fi.metropolia.capslock.dyslexiascreener.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.time.OffsetDateTime;

/**
 * Entity that represents a single take of the test
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
    public Test(String studentName, int studentAge) {
        this.id = 0;
        this.timestamp = OffsetDateTime.now();
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentPoints = 0;
        this.availablePoints = 0;
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

    /**
     * Increases student's points with the provided value.
     *
     * @param value A <code>int</code> to add to the student's points
     */
    public void addStudentPoints(int value) {
        this.studentPoints += value;
    }

    public int getAvailablePoints() {
        return availablePoints;
    }

    public void setAvailablePoints(int availablePoints) {
        this.availablePoints = availablePoints;
    }

    /**
     * Increases available points with the provided value.
     *
     * @param value A <code>int</code> to add to the available points
     */
    public void addAvailablePoints(int value) {
        this.availablePoints += value;
    }

    public boolean isDyslexiaPossible() {
        double score = studentPoints / (double) availablePoints;

        if (studentAge <= 9) {
            return score < 0.75;
        } else if (studentAge <= 12) {
            return score < 0.85;
        } else if (studentAge <= 14) {
            return score < 0.95;
        }

        return false;
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
