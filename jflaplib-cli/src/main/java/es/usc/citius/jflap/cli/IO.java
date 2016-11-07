package es.usc.citius.jflap.cli;


import edu.duke.cs.jflap.automata.Automaton;
import edu.duke.cs.jflap.file.XMLCodec;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public final class IO {
    private IO(){}

    public static Automaton loadAutomaton(String location){
        // Check if the location is a file
        File f = new File(location);
        if (f.isFile()){
            return loadAutomaton(f);
        }
        // Check if it is a valid URL
        try {
            return (Automaton)new XMLCodec().decode(new URL(location).openStream());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }



    public static Automaton loadAutomaton(File file){
        if (!file.isFile()) throw new RuntimeException(file.getAbsolutePath() + " does not exist");
        return (Automaton)new XMLCodec().decode(file, null);
    }

}
