package entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Chat {

    private int id;
    @NonNull private int userFromId;
    @NonNull private int userToId;
    private String messageText;
    private String messageTime;

    public Chat(int userFromId, int userToId, String messageText, String messageTime) {
        this.userFromId = userFromId;
        this.userToId = userToId;
        this.messageText = messageText;
        this.messageTime = messageTime;
    }
}
