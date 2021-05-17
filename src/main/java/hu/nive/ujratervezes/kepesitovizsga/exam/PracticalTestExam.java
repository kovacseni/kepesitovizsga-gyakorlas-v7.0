package hu.nive.ujratervezes.kepesitovizsga.exam;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PracticalTestExam extends Exam {

    public PracticalTestExam(String name, LocalDateTime beginTime) {
        super(name, beginTime);
        this.durationOfExam = 3;
        this.type = ExamType.PRACTICAL_TEST;
    }

    @Override
    public Map<String, LocalDateTime> getExamsBySpecialPredicate(List<Exam> exams) {
        Map<String, LocalDateTime> result = new HashMap<>();
        for (Exam e : exams) {
            if (e.getBeginTime().toLocalTime().isAfter(LocalTime.NOON)) {
                result.put(e.getName(), e.getBeginTime());
            }
        }
        return result;
    }
}
