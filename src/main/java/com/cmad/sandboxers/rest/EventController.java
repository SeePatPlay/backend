package com.cmad.sandboxers.rest;

import java.util.List;

import com.cmad.sandboxers.model.EventCounters;
import com.cmad.sandboxers.model.EventV1;
import com.cmad.sandboxers.service.EventService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import com.cmad.sandboxers.model.User;

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
	@RequestMapping(value = "/{name}/counters", method = RequestMethod.GET)
	public ResponseEntity<EventCounters> getEventCounters(@PathVariable("name") String name,
								@RequestParam(value="hours",
												required=false,
												defaultValue = "24") Integer hours, 
								Pageable pageable) {
		System.out.println("Reached point");
		EventCounters ec=service.getEventCounters(name,hours);
		return new ResponseEntity<EventCounters>(ec,HttpStatus.OK);
	}

	/**
	 * 
	 * @param hours - it's optional, by default, 24 is the value (i.e 24 hours data will be returned)
	 * @return
	 */
	@RequestMapping(value = "/{name}/events", method = RequestMethod.GET)
	public ResponseEntity<List<EventV1>> getEvents(@PathVariable("name") String name,
							@RequestParam(value="hours",required=false,defaultValue = "24") Integer hours,
							Pageable pageinfo) {
		System.out.printf("Fetching logs for %s\n",name);
		List<EventV1> e=service.getEventList(name,hours, pageinfo);

		return new ResponseEntity<List<EventV1>>(e,HttpStatus.OK);
	}


	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getUsers() {
		List<User> ulist=service.getUsers();

		return new ResponseEntity<List<User>>(ulist,HttpStatus.OK);
	}

	@RequestMapping(path = "/user", method = RequestMethod.POST)
	public ResponseEntity<User> addUser(@RequestBody String name, UriComponentsBuilder builder) {
		System.out.println("Adding user");
		User u = service.addUser(name);
		HttpHeaders headers = new HttpHeaders();
		//headers.setLocation(builder.path("/school/{id}").buildAndExpand(u.getId()).toUri());
		return new ResponseEntity<User>(u, headers, HttpStatus.CREATED);
	}

	@RequestMapping(path = "/user/{name}/devices", method = RequestMethod.POST)
	public ResponseEntity<User> addDevice(@PathVariable("name") String name, 
											@RequestBody List<String> devices, 
											UriComponentsBuilder builder) {
		System.out.println("Adding device");
		System.out.println(devices);
		User u = service.addDevice(name,devices);
		HttpHeaders headers = new HttpHeaders();
		//headers.setLocation(builder.path("/school/{id}").buildAndExpand(u.getId()).toUri());
		return new ResponseEntity<User>(u, headers, HttpStatus.CREATED);
	}
}
