package ra.databinding.dao;

import org.springframework.stereotype.Component;
import ra.databinding.model.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    public void add(Student s){
        s.setId(new Random().nextLong());
        studentList.add(s);
    }
}
