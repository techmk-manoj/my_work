package com.m3bi.common.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.m3bi.common.config.deserializer.ZonedDateTimeDeserializer;
import com.m3bi.common.config.serializer.ZonedDateTimeSerializer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.TimeZone;

import static com.m3bi.common.Constant.DATE_FORMAT;
import static com.m3bi.common.Constant.LOCALE;

@Component
public class CustomJackson2ObjectMapperBuilder extends Jackson2ObjectMapperBuilder {
    @Override
    public void configure(ObjectMapper objectMapper) {
        super.configure(objectMapper);

        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setDateFormat(new SimpleDateFormat(DATE_FORMAT, LOCALE));
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT"));
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_ABSENT);
        objectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        objectMapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);

        objectMapper.registerModule(new SimpleModule(){{
            addSerializer(new ZonedDateTimeSerializer());
            addDeserializer(ZonedDateTime.class, new ZonedDateTimeDeserializer());
        }});
    }
}
