package com.yujie.repository;

import com.yujie.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person,Long> {
}
