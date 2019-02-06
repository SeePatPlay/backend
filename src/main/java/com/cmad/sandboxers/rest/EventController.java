package com.cmad.sandboxers.rest;

import java.util.List;

import com.cmad.sandboxers.model.EventCounters;
import com.cmad.sandboxers.model.EventV1;
import com.cmad.sandboxers.service.EventService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

/**
 * 
 * @author pkrishne
 *
 */

@RestController
@CrossOrigin
public class EventController {

	@Autowired
	private EventService service;
	/**
	 * 
	 * @param hours - it's optional, by default, 24 is the value (i.e 24 hours data will be returned)
	 * @return
	 */
	@RequestMapping(value = "/counters", method = RequestMethod.GET)
	public ResponseEntity<EventCounters> getEventCounters(@RequestParam(value="hours",required=false,defaultValue = "24") Integer hours, Pageable pageable) {
		System.out.println("Reached point");
		EventCounters ec=service.getEventCounters(hours);
		return new ResponseEntity<EventCounters>(ec,HttpStatus.OK);
	}

	/**
	 * 
	 * @param hours - it's optional, by default, 24 is the value (i.e 24 hours data will be returned)
	 * @return
	 */
	@RequestMapping(value = "/events", method = RequestMethod.GET)
	public ResponseEntity<List<EventV1>> getEvents(@RequestParam(value="hours",required=false,defaultValue = "24") Integer hours, Pageable pageinfo) {
		List<EventV1> e=service.getEventList(hours, pageinfo);

		return new ResponseEntity<List<EventV1>>(e,HttpStatus.OK);
	}
}
