package com.coinspy.entity;

import com.mongodb.lang.NonNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "code")
public class CodeEntity {
    @Id
    private String id;
    @NonNull
    @Indexed(unique=true)
    private String byteCode;
    @NonNull
    private Type type;

    public CodeEntity(String byteCode, Type type) {
        this.byteCode=byteCode;
        this.type=type;
    }
}
