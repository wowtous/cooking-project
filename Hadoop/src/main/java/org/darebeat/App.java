package org.darebeat;

import org.darebeat.job.WordCount;

public class App {
    public static void main( String[] args ) {
        if (args.length>0){
            WordCount.start(args);
        }
    }
}
