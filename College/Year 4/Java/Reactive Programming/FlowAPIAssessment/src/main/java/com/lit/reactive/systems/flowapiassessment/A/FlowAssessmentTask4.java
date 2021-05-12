/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lit.reactive.systems.flowapiassessment.A;

import com.lit.reactive.systems.flowapiassessment.operators.Emitter;
import com.lit.reactive.systems.flowapiassessment.operators.FlatMapper;
import com.lit.reactive.systems.flowapiassessment.operators.Filter;
import com.lit.reactive.systems.flowapiassessment.subscribers.Printer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 *
 * @author James
 */
public class FlowAssessmentTask4 {

    public static void main(String[] args) {

        BufferedReader br = getBufferedReaderForFile("/shakespeare.txt");

        SubmissionPublisher<String> submissionPublisher = new Emitter();
        Flow.Processor<String, String> mapper = new FlatMapper<>(s -> Arrays.stream(s.split(" ")).filter(it->!it.isBlank()));
        Flow.Subscriber<String> printer = new Printer();
        Flow.Processor<String, String> filter = new Filter(s -> s.contains("woman"));

        filter.subscribe(printer);
        mapper.subscribe(filter);
        submissionPublisher.subscribe(mapper);

        br.lines().forEach(submissionPublisher::submit);
        submissionPublisher.close();
    }



    private static void sleep(long millisecond) {
        try {
            Thread.sleep(millisecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static BufferedReader getBufferedReaderForFile(String fileName) {

        URL resource = FlowAssessmentTask1.class.getResource(fileName);
        File inFile;
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            inFile = new File(resource.getFile());
        }

        FileReader fr = null;
        try {
            fr = new FileReader(inFile);
        } catch (FileNotFoundException ex) {
            System.out.println("Error" + ex);
        }

        assert fr != null;
        return new BufferedReader(fr);
    }

}
