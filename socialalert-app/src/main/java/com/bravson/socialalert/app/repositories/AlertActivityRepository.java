package com.bravson.socialalert.app.repositories;

import java.util.List;
import java.util.UUID;

import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.repository.Facet;
import org.springframework.data.solr.repository.Query;

import com.bravson.socialalert.app.entities.AlertActivity;
import com.bravson.socialalert.app.infrastructure.CustomBaseRepository;
import com.bravson.socialalert.common.domain.ActivityType;

public interface AlertActivityRepository extends CustomBaseRepository<AlertActivity, UUID> {

	public static final int MAX_FACET_RESULTS = 1000;
	
	@Query(value="*:*", filters={"sourceId:(?0)"})
	Page<AlertActivity> findBySourceIdList(List<UUID> sourceIdList, Pageable pageable);
	
	@Facet(fields={"activityType"}, limit=MAX_FACET_RESULTS, minCount=1)
	@Query(value="*:*", filters={"creation:[?0 TO *]", "sourceId:(?1)"})
	FacetPage<AlertActivity> groupRecentActivity(DateTime lastCheck, List<UUID> sourceIdList, Pageable pageable);
	
	@Facet(fields={"country"}, limit=MAX_FACET_RESULTS, minCount=1)
	@Query(value="*:*", filters={"profileId:(?0)", "activityType:(?1)", "creation:[?2 TO *]"})
	FacetPage<AlertActivity> groupCountryActivity(UUID profileId, ActivityType activityType, DateTime minTime, Pageable pageable);
}
