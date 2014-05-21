package com.bravson.socialalert.app.services;

import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.bravson.socialalert.common.domain.QueryResult;

@Validated
public interface ProfileLinkService {

	public QueryResult<UUID> getObservedProfiles(@NotNull UUID sourceProfileId, @Min(0) int pageNumber, @Min(1) int pageSize);
	
	public QueryResult<UUID> getObserverProfiles(@NotNull UUID targetProfileId, @Min(0) int pageNumber, @Min(1) int pageSize);
	
	public boolean isObserverOf(@NotNull UUID sourceProfileId, @NotNull UUID observedProfileId);
	
	public boolean addObservedProfile(@NotNull UUID sourceProfileId, @NotNull UUID observedProfileId);
	
	public boolean removeObservedProfile(@NotNull UUID sourceProfileId, @NotNull UUID observedProfileId);
	
	public boolean increaseActivityWeight(@NotNull UUID sourceProfileId, @NotNull UUID targetProfileId, int delta);
}
