package com.coinspy.repository;

import com.coinspy.entity.CodeEntity;
import com.coinspy.entity.Type;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodeRepository extends MongoRepository<CodeEntity, String> {
    List<CodeEntity> findByType(Type type);
}
