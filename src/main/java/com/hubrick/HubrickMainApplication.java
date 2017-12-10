package com.hubrick;

import com.hubrick.processor.impl.CSVProcessor;

public class HubrickMainApplication {

    public static void main(String[] args) {

//        FactoryType.Type type = args.length == 1 ? FactoryType.Type.getTypeByName(args[0]) : FactoryType.Type.CSV;
        CSVProcessor csvProcessor = new CSVProcessor();
        csvProcessor.process();

    }
}
