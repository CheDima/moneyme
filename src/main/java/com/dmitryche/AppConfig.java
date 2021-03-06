package com.dmitryche;

import com.dmitryche.dataservice.DataService;
import com.dmitryche.dataservice.DataServiceStub;
import com.dmitryche.service.transaction.AccountService;
import com.dmitryche.service.transaction.AccountServiceImpl;
import com.dmitryche.service.validation.ValidationService;
import com.dmitryche.service.validation.ValidationServiceImpl;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class AppConfig extends ResourceConfig {
    public AppConfig() {
        packages("com.dmitryche");
        register(JacksonFeature.class);
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(AccountServiceImpl.class).to(AccountService.class);
                bind(DataServiceStub.class).to(DataService.class);
                bind(ValidationServiceImpl.class).to(ValidationService.class);
                bind(DataServiceStub.class).to(DataService.class);
            }
        });
    }
}