package in.akash.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerProfile {

    private Long phoneNumber;
    private String username;
    private String email;
    private String planId;
    private String planName;
    private String description;
    private String validity;
    private List<Object[]> friendContactNumbers;
}
