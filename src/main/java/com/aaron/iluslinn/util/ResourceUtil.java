package com.aaron.iluslinn.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;


public class ResourceUtil {

    public static <T> T getDataFromResourceFile(String resourcePath, Class<T> clazz) {
        ClassPathResource pathResource = new ClassPathResource(resourcePath);
        InputStream inputStream = null;

        try {
            inputStream = pathResource.getInputStream();
            String text = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));


            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(text, clazz);

        } catch (IOException e) {
            e.printStackTrace();
            return null;

        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                System.out.println("Failed to close streams");
            }
        }


    }
}
