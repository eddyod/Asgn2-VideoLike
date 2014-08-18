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

import org.magnum.mobilecloud.video.repository.Video;
import org.magnum.mobilecloud.video.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import retrofit.http.GET;

@Controller
public class VideoController {
	

	@Autowired
	VideoRepository videos;
	
	/*
	 * - Returns the list of videos that have been added to the server as JSON.
	 * The list of videos should be persisted using Spring Data. The list of
	 * Video objects should be able to be unmarshalled by the client into a
	 * Collection<Video>. - The return content-type should be application/json,
	 * which will be the default if you use @ResponseBody
	 */
	
	@GET("/video")
	public Collection<Video> getVideoList() {
		//Collection<Video> videoList = videos.findCollection();
		//return videoList;
		return null;
	}
	



	
}
