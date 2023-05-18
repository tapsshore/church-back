package com.shoshore.churchback.config;

import com.shoshore.churchback.repository.CellGroupRepository;
import com.shoshore.churchback.services.CellGroupService;
import com.shoshore.churchback.services.CellGroupServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author : tapiwanasheshoshore
 * @mailto : tapsshore@gmail.com
 * @created : 18/5/2023, Thursday
 **/
@Configuration
@EnableAsync
public class BeanConfig {
    @Bean
    public CellGroupService cellGroupService(CellGroupRepository cellGroupRepository){
        return new CellGroupServiceImpl(cellGroupRepository);
    }
}
