package com.want.amap.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.want.amap.domain.AllPoiInfo;

public interface AllPoiInfoRepository extends MongoRepository<AllPoiInfo, ObjectId> {
	@Query("{'companyID':?0, 'branchID':?1 }")
	public List<AllPoiInfo> findByCompanyIdAndBranchId(String companyId, String branchId);
}
