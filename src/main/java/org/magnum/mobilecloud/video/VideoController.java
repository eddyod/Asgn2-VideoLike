/*
 * 
 * Copyright 2014 Jules White
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.magnum.mobilecloud.video;

import java.util.Collection;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.magnum.mobilecloud.video.repository.Video;
import org.magnum.mobilecloud.video.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

@Controller
public class VideoController {

	@Autowired
	VideoRepository videos;

	Video video = new Video();

	public static final String TITLE_PARAMETER = "title";
	public static final String DURATION_PARAMETER = "duration";

	/*
	 * - Returns the list of videos that have been added to the server as JSON.
	 * The list of videos should be persisted using Spring Data. The list of
	 * Video objects should be able to be unmarshalled by the client into a
	 * Collection<Video>. - The return content-type should be application/json,
	 * which will be the default if you use @ResponseBody
	 */

	@GET("/video")
	public Collection<Video> getVideoList() {
		// Collection<Video> videoList = videos.findCollection();
		// return videoList;
		return video.getVideoCollection();
	}

	/*
	 * - The video metadata is provided as an application/json request body. The
	 * JSON should generate a valid instance of the Video class when
	 * deserialized by Spring's default Jackson library. - Returns the JSON
	 * representation of the Video object that was stored along with any updates
	 * to that object made by the server. - **_The server should store the Video
	 * in a Spring Data JPA repository. If done properly, the repository should
	 * handle generating ID's._** - A video should not have any likes when it is
	 * initially created. - You will need to add one or more annotations to the
	 * Video object in order for it to be persisted with JPA.
	 */
	@POST("/video")
	public Video addVideo(@Body Video v) {
		// Video video = videoRep.save(v);
		videos.save(v);
		return v;
	}

	/*
	 * Returns the video with the given id or 404 if the video is not found.
	 */
	@GET("/video/{id}")
	public Video getVideoById(@Path("id") long id, HttpServletResponse response) {
		Video video = videos.findOne(id);
		if (video == null) {
			response.setStatus(404);
		}
		return video;
	}

	/*
	 * - Allows a user to like a video. Returns 200 Ok on success, 404 if the
	 * video is not found, or 400 if the user has already liked the video. - The
	 * service should should keep track of which users have liked a video and
	 * prevent a user from liking a video twice. A POJO Video object is provided
	 * for you and you will need to annotate and/or add to it in order to make
	 * it persistable. - A user is only allowed to like a video once. If a user
	 * tries to like a video a second time, the operation should fail and return
	 * 400 Bad Request.
	 */
	/*
	 * @ResponseStatus(value = HttpStatus.OK)
	 * 
	 * @POST("/video/{id}/like") public Void likeVideo(@Path("id") long id) {
	 * return null; }
	 */
	@RequestMapping(value = "/video/{id}/like", method = RequestMethod.POST)
	public ResponseEntity<Void> likeVideo(@PathVariable("id") long id, Video p) {

		if (!videos.exists(id)) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		String username = p.getName();
		Video v = videos.findOne(id);
		Set<String> likesUsernames = v.getLikesUsernames();
		// Checks if the user has already liked the video.
		if (likesUsernames.contains(username)) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		// keep track of users have liked a video
		v.setLikesUsernames(likesUsernames);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	/*
	 * - Allows a user to unlike a video that he/she previously liked. Returns
	 * 200 OK on success, 404 if the video is not found, and a 400 if the user
	 * has not previously liked the specified video.
	 */
	/*
	@ResponseStatus(value = HttpStatus.OK)
	@POST("/video/{id}/unlike")
	public Void unlikeVideo(@Path("id") long id) {
		return null;
	}
	*/
	@RequestMapping(value = "/video/{id}/unlike", method = RequestMethod.POST)
	public ResponseEntity<Void> unlikeVideo(@PathVariable("id") long id, Video p) {

		if (!videos.exists(id)) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		String username = p.getName();
		Video v = videos.findOne(id);
		Set<String> unlikesUsernames = v.getUnlikesUsernames();
		// Checks if the user has already liked the video.
		if (unlikesUsernames.contains(username)) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		// keep track of users have liked a video
		v.setUnlikesUsernames(unlikesUsernames);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	/*
	 * - Returns a list of the string usernames of the users that have liked the
	 * specified video. If the video is not found, a 404 error should be
	 * generated.
	 */
	/*
	@GET("/video/{id}/likedby")
	public Collection<String> getUsersWhoLikedVideo(@Path("id") long id) {
	}
	*/
	@RequestMapping(value = "/video/{id}/likedby", method = RequestMethod.POST)
	public ResponseEntity<Void> getUsersWhoLikedVideo(@PathVariable("id") long id, Video p) {
		if (!videos.exists(id)) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		String username = p.getName();
		Video v = videos.findOne(id);
		Set<String> unlikesUsernames = v.getUnlikesUsernames();
		// Checks if the user has already liked the video.
		if (unlikesUsernames.contains(username)) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		// keep track of users have liked a video
		v.setUnlikesUsernames(unlikesUsernames);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	/*
	 * - Returns a list of videos whose titles match the given parameter or an
	 * empty list if none are found.
	 */
	/*
	 * @GET("/video/search/findByName?title={title}") public Collection<Video>
	 * findByTitle(@Query(TITLE_PARAMETER) String title) { //Collection<Video>
	 * videos = videoRep.findLike(title); //return videos; return null; }
	 */

	/*
	 * - Returns a list of videos whose durations are less than the given
	 * parameter or an empty list if none are found.
	 */
	/*
	 * @GET("/video/search/findByDurationLessThan?duration={duration}") public
	 * Collection<Video> findByDurationLessThan(@Query(DURATION_PARAMETER) long
	 * duration) { return null; }
	 */

}
