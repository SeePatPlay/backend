package com.cmad.sandboxers.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.cmad.sandboxers.api.EventAPI;
import com.cmad.sandboxers.data.EventRepository;
import com.cmad.sandboxers.model.EventCounters;
import com.cmad.sandboxers.model.EventType;
import com.cmad.sandboxers.model.EventV1;

/**
 * 
 * @author pkrishne
 *
 */
@Service
public class EventService implements EventAPI {

	@Autowired
	private EventRepository eventRepo;
	
	public List<EventV1> getEventList(int hours) {
		long epochCur=System.currentTimeMillis()/1000;

		long inTime=hours*3600;

		//System.out.print(epochCur-inTime);
		List<EventV1> eventList = new ArrayList<EventV1>();
		eventList=eventRepo.findByTimestampAfter(epochCur-inTime);

		//System.out.print(eventList);
		return eventList;
	}

	public EventCounters getEventCounters(int hours) {
		long millis=System.currentTimeMillis()/1000;
		long inmilli=hours*3600;

		EventCounters ecnt=new EventCounters();

		List<EventV1> eventList=eventRepo.findByTimestampAfter(millis-inmilli);
		//List<EventV1> eventList=eventRepo.findAll();
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

}
