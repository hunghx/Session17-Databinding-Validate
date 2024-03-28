package ra.databinding.dao;

import org.springframework.stereotype.Component;
import ra.databinding.model.entity.Student;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentDao {
    private static final List<Student> studentList = new ArrayList<>();
    static {
        Student s = new Student();
        s.setName("hunghx");
        studentList.add(s);
    }
    public boolean existByName(String name){
        return studentList.stream().anyMatch(s->s.getName().equals(name));
    }
}
