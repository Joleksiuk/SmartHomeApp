package pl.smarthome.Services;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;


class SceneServiceTest {

    @Test
    void testRetain(){
        List<String> list1 = new ArrayList<>(Arrays.asList("A", "B", "C"));
        List<String> list2 = new ArrayList<>(List.of("B"));
        list1.removeAll(list2);
        System.out.println(list1);
    }

}