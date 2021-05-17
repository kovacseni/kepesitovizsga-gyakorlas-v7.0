package hu.nive.ujratervezes.kepesitovizsga.exam;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public abstract class Exam {

    protected String name;
    protected LocalDateTime beginTime;
    protected int durationOfExam;
    protected static final int DURATION_OF_IDENTIFICATION = 1;
    protected ExamType type;

    protected Exam(String name, LocalDateTime beginTime) {
        this.name = name;
        this.beginTime = beginTime;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public int getDurationOfExam() {
        return durationOfExam;
    }

    public static int getDurationOfIdentification() {
        return DURATION_OF_IDENTIFICATION;
    }

    public ExamType getType() {
        return type;
    }

    public LocalDateTime getBeginTimeOfExam() {
        return beginTime.minusHours(DURATION_OF_IDENTIFICATION);
    }

    public LocalDateTime getEndTimeOfExam() {
        return getBeginTime().plusHours(durationOfExam);
    }

    public List<Exam> getExamsInChronologicalOrder(List<Exam> exams) {
        exams.sort(Comparator.comparing(Exam::getBeginTime));
        return exams;
    }

    public abstract Map<String, LocalDateTime> getExamsBySpecialPredicate(List<Exam> exams);
}
