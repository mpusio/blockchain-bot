package com.coinspy.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "block")
public class BlockEntity {
    Blockchain blockchain;
    String blockNumber;
}
