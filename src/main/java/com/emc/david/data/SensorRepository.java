package com.emc.david.data;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.service.ServiceInfo;
import org.springframework.cloud.service.common.MongoServiceInfo;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SensorRepository {
	private MongoDbFactory mongoDbFactory;
	private MongoTemplate mongoTemplate;
	
	public static final String collection = "SensorState";
	private static final Logger logger = LoggerFactory.getLogger(SensorRepository.class);

	public SensorRepository(){
		try{
			Cloud cloud = new CloudFactory().getCloud();
			for(int i=0; i<cloud.getServiceInfos().size();i++){
				logger.debug(cloud.getServiceInfos().get(i).getId());
				if(cloud.getServiceInfos().get(i) instanceof MongoServiceInfo){
					String connectionid = cloud.getServiceInfos().get(i).getId();
					mongoDbFactory = cloud.getServiceConnector(connectionid, MongoDbFactory.class, null);
					mongoTemplate = new MongoTemplate(mongoDbFactory);
					return;
				}
			}
		}
		catch(Exception x){
			logger.debug(x.getMessage());
		}
	}
	
	public void addEntry(SensorState state){
		if(mongoTemplate != null){
			if(!mongoTemplate.collectionExists(SensorState.class)){
				mongoTemplate.createCollection(SensorState.class);
			}
			mongoTemplate.insert(state, collection);
		}
	}
	
	public List<SensorState> getAllRecords(){
		List<SensorState> list = null;
		if(mongoTemplate != null){
			list = mongoTemplate.findAll(SensorState.class, collection);
		}
		return list;
	}
}
