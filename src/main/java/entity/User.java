package entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class User {

    private int id;
    @NonNull private String email;
    @NonNull private String password;
    private String name;
    private String surname;
    private String imageUrl;
    private String lastLogin;
    private String job;

}
