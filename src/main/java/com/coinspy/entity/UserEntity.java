package com.coinspy.entity;

import com.mongodb.lang.NonNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@Document(collection = "user")
public class UserEntity {
    @Id
    private String id;
    @Indexed(unique=true)
    private String telegramUserId;
    private Map<Long, LocalDateTime> groups;

    public UserEntity(@NonNull String telegramUserId, @NonNull Map<Long, LocalDateTime> groups) {
        this.telegramUserId = telegramUserId;
        this.groups = groups;
    }
}