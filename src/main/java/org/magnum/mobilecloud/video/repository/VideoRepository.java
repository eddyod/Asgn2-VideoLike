/**
 * Aug 18, 201
 * Edward O'Donnell eodonnell@ucsd.edu
 * Asgn2-VideoLike
 */

package org.magnum.mobilecloud.video.repository;



import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VideoRepository extends CrudRepository<Video, Long>{
	
	 // Find all videos with a matching title (e.g., Video.name)
	   public Collection<Video> findByName(String title);


}
