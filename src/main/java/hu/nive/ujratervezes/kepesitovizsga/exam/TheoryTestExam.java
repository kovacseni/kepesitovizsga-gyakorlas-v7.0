package hu.nive.ujratervezes.kepesitovizsga.exam;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheoryTestExam extends Exam {

    public TheoryTestExam(String name, LocalDateTime beginTime) {
        super(name, beginTime);
        this.durationOfExam = 1;
        this.type = ExamType.THEORY_TEST;
    }

    @Override
    public Map<String, LocalDateTime> getExamsBySpecialPredicate(List<Exam> exams) {
        Map<String, LocalDateTime> result = new HashMap<>();
        for (Exam e : exams) {
            if (e.getName().contains("fejlesztő")) {
                result.put(e.getName(), e.getBeginTime());
            }
        }
        return result;
    }
}
