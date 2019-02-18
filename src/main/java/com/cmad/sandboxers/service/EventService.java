package com.cmad.sandboxers.service;

import java.util.List;

import com.cmad.sandboxers.api.EventAPI;
import com.cmad.sandboxers.data.EventRepository;
import com.cmad.sandboxers.model.EventCounters;
import com.cmad.sandboxers.model.EventType;
import com.cmad.sandboxers.model.EventV1;
import com.cmad.sandboxers.data.UserRepository;
import com.cmad.sandboxers.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



/**
 * 
 * @author pkrishne
 *
 */
@Service
public class EventService implements EventAPI {

	@Autowired
	private EventRepository eventRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<EventV1> getEventList(String name, int hours, Pageable pageInfo) {
		long epochCur = System.currentTimeMillis() / 1000;

		long inTime = hours * 3600;
		User u=userRepo.findByNameLike(name);
		Page<EventV1> eventList = eventRepo.findBySourceInAndTimestampAfter(u.getDevices(), (epochCur - inTime), pageInfo);
		// System.out.print(epochCur-inTime);
		//List<EventV1> eventList = new ArrayList<EventV1>();
		//Page<EventV1> eventList3 = eventRepo.findByTimestampAfter((epochCur - inTime), pageInfo);

		//System.out.print(eventList);
		return eventList.getContent();
	}

	public EventCounters getEventCounters(String name, int hours) {
		long millis=System.currentTimeMillis()/1000;
		long inmilli=hours*3600;

		User u=userRepo.findByNameLike(name);
		List<EventV1> eventList=eventRepo.findBySourceInAndTimestampAfter(u.getDevices(), millis-inmilli);

		EventCounters ecnt=new EventCounters();

		for (EventV1 e : eventList) {
			//System.out.print(e);
			EventType et= e.getEvent_type();
			switch(et.getType()) {
				case "ERROR":
					ecnt.incError();
				break;
				case "WARNING":
					ecnt.incWarn();
				break;
				case "NOTIFICATION":
					ecnt.incNotif();
				break;

				case "DEBUG":
					ecnt.incDebug();
				break;

				case "INFO":
					ecnt.incInfo();
				break;

				case "ALERT":
					ecnt.incAlert();
				break;

			}
		}

		return ecnt;
	}


	public User addUser(String id) {
		User u = new User();
		u.setName(id);
		
		return userRepo.save(u);
	}

	public List<User> getUsers() {
		return userRepo.findAll();
	}

	public User addDevice(String name, List<String>ip) {
		User u=userRepo.findByNameLike(name);
		u.setDevices(ip);
		userRepo.save(u);
		return u;
	}
}
