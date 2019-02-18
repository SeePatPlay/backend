package com.cmad.sandboxers.api;


import org.springframework.data.domain.Pageable;

import java.util.List;

import com.cmad.sandboxers.model.EventCounters;
import com.cmad.sandboxers.model.User;
import com.cmad.sandboxers.model.EventV1;

/**
 * 
 * @author pkrishne
 *
 */
public interface EventAPI {
	
	/**
	 * 
	 * @param hours, specifies the number of hours for which the event has to be returned.
	 * we can filter for only whole number of hours, like 1,2,3 and so on, not possible for 1.5 or 1.7 like that
	 * @return
	 */
	public List<EventV1> getEventList(String name, int hours, Pageable pageInfo);
	
	/**
	 * 
	 * @param hours, specifies the number of hours for which the event counters has to be returned.
	 * @return
	 */
	public EventCounters getEventCounters(String name, int hours);


	public User addUser(String id);

	public List<User> getUsers();
	public User addDevice(String name, List<String>ip);

}
