package com.cloudsherpas.droolsample.util;

import com.cloudsherpas.droolsample.api.resource.CourseEndpointRulesVersionResource;
import com.cloudsherpas.droolsample.domain.RulesVersion;

public class ResourceUtil {
	/*
	 * (non-Javadoc)
	 * 
	 * Re-implementing this method to accept and return a different
	 * model/resource type is a good way to standardize the API.
	 * 
	 * Example: public static UserResource toResource(User user){...} public
	 * static OtherResource toResource(Other other){...}
	 */
	public static CourseEndpointRulesVersionResource toResource(RulesVersion rulesVersion) {
		CourseEndpointRulesVersionResource response = new CourseEndpointRulesVersionResource();

		response.setId(rulesVersion.getId());
		response.setPackageName(rulesVersion.getPackageName());
		response.setVersion(rulesVersion.getVersion());

		return response;
	}
}
