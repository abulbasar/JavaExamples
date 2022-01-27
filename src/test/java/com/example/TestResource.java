package com.example;

import lombok.SneakyThrows;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;


public class TestResource {

    @Test
    public void testResource(){
        String resourceName = "example_resource.txt";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());
        String absolutePath = file.getAbsolutePath();
        System.out.println(absolutePath);
        assertTrue(absolutePath.endsWith("/example_resource.txt"));

    }

}
