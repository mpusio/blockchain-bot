package com.coinspy.repository;

import com.coinspy.entity.BlockEntity;
import com.coinspy.entity.Blockchain;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BlockRepository extends MongoRepository<BlockEntity, String> {
    Optional<BlockEntity> findByBlockchain(Blockchain blockchain);
}
