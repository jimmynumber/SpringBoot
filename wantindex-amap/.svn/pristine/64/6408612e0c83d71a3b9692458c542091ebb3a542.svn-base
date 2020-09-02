package com.want.amap.repository;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.want.amap.domain.IndexLog;

public interface IndexLogRepository extends MongoRepository<IndexLog, ObjectId> {

	@Query("{}")
	IndexLog findLastIndexLog(Sort sort);
}
