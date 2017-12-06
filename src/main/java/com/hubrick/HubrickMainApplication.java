package com.hubrick;

import com.hubrick.factory.FactoryProcessor;
import com.hubrick.factory.type.FactoryType;
import com.hubrick.processor.AbstractProcessor;

public class HubrickMainApplication {

    public static void main(String[] args) {

        FactoryType.Type type = args.length == 1 ? FactoryType.Type.getTypeByName(args[0]) : FactoryType.Type.CSV;
        AbstractProcessor processor = FactoryProcessor.getProcessor(type);
        processor.process();

    }
}
