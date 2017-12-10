//package com.hubrick.factory;
//
//import com.hubrick.factory.type.FactoryType;
//import com.hubrick.processor.AbstractProcessor;
//import com.hubrick.writer.impl.CSVWriter;
//
///**
// * Provide type to get the processor implementation.
// *
// * Created by eric.nascimento.
// */
//public class FactoryProcessor {
//
//    public static AbstractProcessor getProcessor(FactoryType.Type type) {
//
//        if (type == FactoryType.Type.CSV) {
//            return new CSVWriter();
//        } else {
//            throw new UnsupportedOperationException();
//        }
//
//    }
//}
