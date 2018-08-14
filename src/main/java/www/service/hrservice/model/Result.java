package www.service.hrservice.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import www.service.hrservice.view.View;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result {

    @JsonView(View.class)
    private boolean success;

    @JsonView(View.class)
    private Object result;

}
