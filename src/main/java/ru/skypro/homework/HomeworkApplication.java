package ru.skypro.homework;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.CreateOrUpdateAd;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class HomeworkApplication {
  public static void main(String[] args) {
    SpringApplication.run(HomeworkApplication.class, args);
  }
    @Component
    @RequiredArgsConstructor
    public static class StringToUserConverter implements Converter<String, CreateOrUpdateAd> {

        private final ObjectMapper objectMapper;

        @Override
        @SneakyThrows
        public CreateOrUpdateAd convert(String source) {
            return objectMapper.readValue(source, CreateOrUpdateAd.class);
        }
    }
}
