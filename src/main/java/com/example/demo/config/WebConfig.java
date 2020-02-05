package com.example.demo.config;


import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by wody8674@gmail.com on 2020/01/31.
 */
@Configuration
public class WebConfig {

    /**
     * 데이터 매핑 bean
     * @return modelMapper instance.
     */
    @Bean
    public ModelMapper getModelMapper() {
        final ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration().setMethodAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PUBLIC);
        // 필드 private 매핑
        mapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
        // 프로퍼티가 완전히 동일해야 매핑.
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return mapper;
    }

}
