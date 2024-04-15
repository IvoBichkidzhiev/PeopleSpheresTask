package people.spheres.demo2.exception;

import com.google.gson.Gson;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

public class BadRequestResponseDTO extends ResponseEntity<String> {

    private static final Gson GSON = new Gson();
    private static final HttpHeaders responseHeaders = new HttpHeaders();

    static {
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
    }

    private BadRequestResponseDTO(@Nullable String body, HttpStatus status) {
        super(body, responseHeaders, status);
    }

    public static BadRequestResponseDTO create(Object body) {
        return new BadRequestResponseDTO(GSON.toJson(body), HttpStatus.BAD_REQUEST);
    }

    public static BadRequestResponseDTO create(String body, HttpStatus status) {
        return create(new ErrorMessageDTO(body, status.value()));
    }

}
