package me.enmanuel;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import me.enmanuel.eatnear.domain.Recommendation;
import me.enmanuel.eatnear.entity.Restaurant;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel Reyes
 * Date: 10-Jan-17
 * Time: 9:31 AM
 */
public class TestLikely {
    public static void main(String[] args) throws FileNotFoundException, ScriptException, NoSuchMethodException {
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

        engine.eval(new FileReader("sylvester.src.js"));
        engine.eval(new FileReader("likely.js"));
        Invocable invocable = (Invocable) engine;
        List<List<Integer>> inputMatrix = Arrays.asList(Arrays.asList(1, 2, 3, 0), Arrays.asList(4, 0, 5, 6),
                Arrays.asList(7, 8, 0, 9));
        List<String> rowLabels = Arrays.asList("John", "Sue", "Joe");
        List<String> colLabels = Arrays.asList("Red", "Blue", "Green", "Purple");
        ScriptObjectMirror result = (ScriptObjectMirror) invocable.invokeFunction("buildModel", inputMatrix,rowLabels,colLabels);
        System.out.println(result);
        System.out.println(result.getClass());
        final ScriptObjectMirror x = (ScriptObjectMirror) result.callMember("recommendations", "John");
        String string = Arrays.toString(x.to(String[].class));
        string = string.replaceAll("\\[","");
        string = string.replaceAll("]","");
        System.out.println(string);
        Recommendation recommendation = new Recommendation();
        recommendation.setRestaurant(new Restaurant(Integer.valueOf(string.split(",")[0])));
        recommendation.setWtf(Double.parseDouble(string.split(",")[1]));
        System.out.println(recommendation);
    }
}
