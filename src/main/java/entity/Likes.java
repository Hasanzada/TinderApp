package entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Likes {

    private int id;
    @NonNull private int userId;
    @NonNull private int selectedUserId;
    @NonNull private boolean isLiked;

}
