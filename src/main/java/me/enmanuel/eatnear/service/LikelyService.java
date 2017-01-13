package me.enmanuel.eatnear.service;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

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
 * Date: 13-Jan-17
 * Time: 3:11 PM
 */
@org.springframework.stereotype.Service
public class LikelyService {
    private Invocable engine;

    public LikelyService() {
        try {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
            engine.eval(new FileReader("sylvester.src.js"));
            engine.eval(new FileReader("likely.js"));

            this.engine = (Invocable) engine;
        } catch (ScriptException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ScriptObjectMirror buildModel(List<String> rowLabels, List<String> colLabels, List<List<Integer>> inputMatrix)
            throws ScriptException, NoSuchMethodException {
        return (ScriptObjectMirror) engine.invokeFunction("buildModel", inputMatrix, rowLabels, colLabels);
    }

    public ScriptObjectMirror rankAllItems(ScriptObjectMirror result, Integer id) {
        ScriptObjectMirror rankedAllItems = (ScriptObjectMirror) result.callMember("rankAllItems", id.toString());
        return rankedAllItems;
    }
    public ScriptObjectMirror recommendations(ScriptObjectMirror result, String name) {
        ScriptObjectMirror rankedAllItems = (ScriptObjectMirror) result.callMember("recommendations", name);
        return rankedAllItems;
    }
}
