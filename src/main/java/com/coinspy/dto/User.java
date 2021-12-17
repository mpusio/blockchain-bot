package com.coinspy.dto;

import com.coinspy.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "user")
public class User {
    @NonNull
    private String telegramUserId;
    @NonNull
    private Map<Long, LocalDateTime> channels;

    public UserEntity convertToUserEntity(){
        return new UserEntity(telegramUserId, channels);
    }
}
