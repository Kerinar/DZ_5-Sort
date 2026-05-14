// Student.java
import java.util.Comparator;
import java.util.Objects;

public class Student {

    private final String groupNumber;
    private final double gpa;
    private final String recordBookNumber;

    private Student(Builder builder) {
        this.groupNumber      = builder.groupNumber;
        this.gpa              = builder.gpa;
        this.recordBookNumber = builder.recordBookNumber;
    }

    public String getGroupNumber()      { return groupNumber;      }
    public double getGpa()              { return gpa;              }
    public String getRecordBookNumber() { return recordBookNumber; }

    // ── Компараторы ───────────────────────────────────────────────────────────
    public static final Comparator<Student> BY_GPA =
            Comparator.comparingDouble(Student::getGpa).reversed();

    public static final Comparator<Student> BY_GROUP =
            Comparator.comparing(Student::getGroupNumber);

    public static final Comparator<Student> BY_RECORD_BOOK =
            Comparator.comparing(Student::getRecordBookNumber);

    public static final Comparator<Student> BY_GPA_THEN_GROUP =
            BY_GPA.thenComparing(BY_GROUP);

    // ── toString / equals / hashCode ──────────────────────────────────────────
    @Override
    public String toString() {
        return String.format(
                "Student{group='%s', gpa=%.2f, recordBook='%s'}",
                groupNumber, gpa, recordBookNumber
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student s = (Student) o;
        return Double.compare(s.gpa, gpa) == 0
                && Objects.equals(groupNumber,      s.groupNumber)
                && Objects.equals(recordBookNumber, s.recordBookNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupNumber, gpa, recordBookNumber);
    }

    // ── Builder ───────────────────────────────────────────────────────────────
    public static class Builder {

        private final String recordBookNumber;

        private String groupNumber = "Без группы";
        private double gpa         = 0.0;

        public Builder(String recordBookNumber) {
            if (recordBookNumber == null || recordBookNumber.isBlank())
                throw new IllegalArgumentException("Номер зачётной книжки не может быть пустым");
            this.recordBookNumber = recordBookNumber;
        }

        public Builder groupNumber(String groupNumber) {
            this.groupNumber = groupNumber;
            return this;
        }

        public Builder gpa(double gpa) {
            if (gpa < 0.0 || gpa > 5.0)
                throw new IllegalArgumentException("GPA должен быть в диапазоне [0.0, 5.0]: " + gpa);
            this.gpa = gpa;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }
}
