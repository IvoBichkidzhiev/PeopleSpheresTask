package people.spheres.demo2.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessageDTO {
    private String message;
    private int code;
}
