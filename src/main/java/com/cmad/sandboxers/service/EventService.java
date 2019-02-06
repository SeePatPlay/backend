package com.cmad.sandboxers.service;

import java.util.List;

import com.cmad.sandboxers.api.EventAPI;
import com.cmad.sandboxers.data.EventRepository;
import com.cmad.sandboxers.model.EventCounters;
import com.cmad.sandboxers.model.EventType;
import com.cmad.sandboxers.model.EventV1;

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

	@Override
	public List<EventV1> getEventList(int hours, Pageable pageInfo) {
		long epochCur = System.currentTimeMillis() / 1000;

		long inTime = hours * 3600;

		// System.out.print(epochCur-inTime);
		//List<EventV1> eventList = new ArrayList<EventV1>();
		Page<EventV1> eventList3 = eventRepo.findByTimestampAfter((epochCur - inTime), pageInfo);

		//System.out.print(eventList);
		return eventList3.getContent();
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
