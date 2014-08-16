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

import javax.servlet.http.HttpServletResponse;

import org.magnum.mobilecloud.video.repository.Video;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VideoController {
	
	@RequestMapping(value = "/video", method = RequestMethod.POST)
	public @ResponseBody Video addVideo(@RequestBody Video v) {
		return v;
	}
	
	@RequestMapping(value = "/video/{id}", method = RequestMethod.GET)
	public void getData(@PathVariable("id") long id, HttpServletResponse response) {
	}
	
	
	@RequestMapping(value = "/video/{id}/like", method = RequestMethod.POST)
	public @ResponseBody Video postLike(@RequestBody Video v) {
		return v;
	}
	
	@RequestMapping(value = "/video/{id}/unlike", method = RequestMethod.POST)
	public @ResponseBody Video postUnLike(@RequestBody Video v) {
		return v;
	}
	
	@RequestMapping(value = "/video/{id}/likedby", method = RequestMethod.GET)
	public void getLikedBy(@PathVariable("id") long id, HttpServletResponse response) {
	}
	
	@RequestMapping(value = "/video/search/findByName?title={title}", method = RequestMethod.GET)
	public void findByName(@PathVariable("title") String title, HttpServletResponse response) {
	}
	
	@RequestMapping(value = "/video/search/findByDurationLessThan?duration={duration}", method = RequestMethod.GET)
	public void findByDuration(@PathVariable("title") long duration, HttpServletResponse response) {
	}

	


	
}