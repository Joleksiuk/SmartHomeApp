package pl.smarthome.Services;

import org.junit.jupiter.api.Test;
import pl.smarthome.Models.Device;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SceneServiceTest {

    @Test
    void testRetain(){
        List<String> list1 = new ArrayList<>(Arrays.asList("A", "B", "C"));
        List<String> list2 = new ArrayList<>(List.of("B"));
        list1.removeAll(list2);
        System.out.println(list1);
    }

}