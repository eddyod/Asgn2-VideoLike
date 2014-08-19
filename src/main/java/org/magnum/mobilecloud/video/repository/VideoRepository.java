/**
 * Aug 18, 201
 * Edward O'Donnell eodonnell@ucsd.edu
 * Asgn2-VideoLike
 */

package org.magnum.mobilecloud.video.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.magnum.mobilecloud.video.client.VideoSvcApi;
import org.springframework.data.repository.query.Param;

@RepositoryRestResource(path = VideoSvcApi.VIDEO_SVC_PATH)
public interface VideoRepository extends CrudRepository<Video, Long> {

	public static final String QUERY_DURATION = "select v from Video v where v.duration < :duration";
	public static final String QUERY_TITLE = "select v from Video v where v.name like :title";

	// Find all videos with a matching title (e.g., Video.name)
	// The @Param annotation tells Spring Data Rest which HTTP request
		// parameter it should use to fill in the "title" variable used to
		// search for Videos
	@Query(QUERY_TITLE)	
	public Collection<Video> findByName(
			@Param(VideoSvcApi.TITLE_PARAMETER) String name);

	// Find all videos that are shorter than a specified duration
	@Query(QUERY_DURATION)
	public Collection<Video> findByDurationLessThan(
	// The @Param annotation tells tells Spring Data Rest which HTTP request
	// parameter it should use to fill in the "duration" variable used to
	// search for Videos
			@Param(VideoSvcApi.DURATION_PARAMETER) long duration);

}
