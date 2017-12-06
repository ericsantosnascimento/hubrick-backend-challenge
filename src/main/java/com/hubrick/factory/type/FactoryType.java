package com.hubrick.factory.type;

/**
 * Created by eric-nasc on 08/03/17.
 */
public class FactoryType {

    public enum Type {
        CSV, JSON;

        public static Type getTypeByName(String name) {
            for (Type type : Type.values()) {
                if (type.name().equalsIgnoreCase(name)) {
                    return type;
                }
            }
            return null;
        }
    }

}
