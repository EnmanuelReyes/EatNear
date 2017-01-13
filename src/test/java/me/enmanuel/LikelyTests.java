package me.enmanuel;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import me.enmanuel.eatnear.service.LikelyService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.script.ScriptException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel Reyes
 * Date: 13-Jan-17
 * Time: 3:58 PM
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LikelyService.class)
public class LikelyTests {

    @Autowired
    LikelyService likelyService;

    @Test
    public void testLikely() throws ScriptException, NoSuchMethodException {
        List<List<Integer>> inputMatrix = Arrays.asList(Arrays.asList(1, 2, 3, 0), Arrays.asList(4, 0, 5, 6),
                Arrays.asList(7, 8, 0, 9));
        List<String> rowLabels = Arrays.asList("John", "Sue", "Joe");
        List<String> colLabels = Arrays.asList("Red", "Blue", "Green", "Purple");
        ScriptObjectMirror model = likelyService.buildModel(rowLabels, colLabels, inputMatrix);
        ScriptObjectMirror recommendations = likelyService.recommendations(model, "John") ;
        final String[] arr = recommendations.to(String[].class);
        System.out.println(Arrays.toString(arr));
        Assert.assertEquals("Purple",arr[0].split(",")[0]);
    }
}
