import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Document("auth")
public class Auth   {
    @Id
    private String username;

    private String password;
}
