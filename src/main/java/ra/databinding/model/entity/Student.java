package ra.databinding.model.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import ra.databinding.validator.StudentNameUnique;

import javax.validation.constraints.Pattern;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student {
    private Long id;
    @NotBlank(message = "khong duoc de trong nhe")
    @StudentNameUnique
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob = new Date();
    private Boolean sex;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", sex=" + sex +
                '}';
    }
}
