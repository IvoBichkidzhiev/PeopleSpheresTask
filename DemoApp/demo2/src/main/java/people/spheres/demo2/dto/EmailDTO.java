package people.spheres.demo2.dto;

import lombok.Data;

import java.util.List;

@Data
public class EmailDTO {
    private final List<IdValueDTO> data;
}
