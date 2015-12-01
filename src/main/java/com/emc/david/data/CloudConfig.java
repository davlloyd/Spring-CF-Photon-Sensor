package com.emc.david.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.app.ApplicationInstanceInfo;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.cloud.service.ServiceInfo;
import org.springframework.cloud.service.common.MongoServiceInfo;
import org.springframework.cloud.service.common.MysqlServiceInfo;
import org.springframework.cloud.service.common.RedisServiceInfo;
import org.springframework.cloud.service.common.RelationalServiceInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.util.ClassUtils;

public class CloudConfig extends AbstractCloudConfig {

	public List<String> cloudServices(){
		List<String> services = new ArrayList<String>();

		Cloud cloud = new CloudFactory().getCloud();
		List<ServiceInfo> serviceInfos = cloud.getServiceInfos();
		for (ServiceInfo serviceInfo : serviceInfos) {
		    if (serviceInfo instanceof RelationalServiceInfo) {
		        services.add(((RelationalServiceInfo) serviceInfo).getJdbcUrl());
		    }else if (serviceInfo instanceof RedisServiceInfo) {
	    		services.add(((RedisServiceInfo) serviceInfo).getUri());
		    }else if (serviceInfo instanceof MysqlServiceInfo) {
	    		services.add(((MysqlServiceInfo) serviceInfo).getJdbcUrl());
		    }else if(serviceInfo instanceof MongoServiceInfo){
		        services.add(((MongoServiceInfo) serviceInfo).getUri());
		    }
		}	

		ApplicationInstanceInfo appInstanceInfo = cloud.getApplicationInstanceInfo();
		services.add("Application id: " + appInstanceInfo.getAppId());
		services.add("Application instance id: " + appInstanceInfo.getInstanceId());
		for (Map.Entry<String, Object> entry: appInstanceInfo.getProperties().entrySet()) {
			services.add("Application property: " + entry.getKey() + "=" + entry.getValue());
		}
		
		return services;
	}

	@Bean
	public DataSource dataSource() {
    	return connectionFactory().dataSource();
	}
    
	
	@Bean
	public MongoDbFactory documentMongoDbFactory() {
    	return connectionFactory().mongoDbFactory();
	}
}
