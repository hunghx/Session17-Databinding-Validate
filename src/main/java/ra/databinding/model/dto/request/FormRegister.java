package ra.databinding.model.dto.request;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FormRegister {
    private String username,password, fullName ;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date bod;
    private Boolean sex;
}
