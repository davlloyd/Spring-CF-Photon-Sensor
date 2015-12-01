package com.emc.david;

import com.emc.david.*;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.service.ServiceInfo;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import com.emc.david.data.CloudConfig;
import com.emc.david.data.SensorRepository;
import com.emc.david.data.SensorState;

@RestController
public class SensorController {
	@Autowired
	private SensorRepository repo;

	
	@RequestMapping(value="/sensor/current", method={RequestMethod.GET})
	public SensorState current(){
		Calendar cal = Calendar.getInstance();

		return new SensorState("here", cal.get(Calendar.SECOND));
	}
	
	@RequestMapping(value="/sensor", method={RequestMethod.GET})
	public List<SensorState> list(){
		List<SensorState> list = repo.getAllRecords();
		return list;
	}
	
	@RequestMapping(value="/sensor/add/{item}/{state}", method={RequestMethod.POST})
	public void add(@PathVariable String item, @PathVariable Integer state){
		repo.addEntry(new SensorState(item, state));
	}

	@RequestMapping(value="/sensor/services", method={RequestMethod.GET})
	public List<String> services(){
		CloudConfig config = new CloudConfig();
		return config.cloudServices();
	}
}
