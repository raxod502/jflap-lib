package es.usc.citius.jflap.cli;


import edu.duke.cs.jflap.automata.*;
import edu.duke.cs.jflap.automata.fsa.*;
import edu.duke.cs.jflap.file.XMLCodec;
import edu.duke.cs.jflap.grammar.*;
import edu.duke.cs.jflap.gui.action.*;
import edu.duke.cs.jflap.gui.grammar.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public final class IO {
    private IO(){}

    public static FiniteStateAutomaton loadAutomaton(String location){
        // Check if the location is a file
        File f = new File(location);
        if (f.isFile()){
            return loadAutomaton(f);
        }
        // Check if it is a valid URL
        try {
            return (FiniteStateAutomaton)new XMLCodec().decode(new URL(location).openStream());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }



    public static FiniteStateAutomaton loadAutomaton(File file){
        if (!file.isFile()) throw new RuntimeException(file.getAbsolutePath() + " does not exist");
        return (FiniteStateAutomaton)new XMLCodec().decode(file, null);
    }

    public static Grammar loadGrammar(File location) {
        return (Grammar)new XMLCodec().decode(location, null);
    }

}
