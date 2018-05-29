package ch.fhnw.oop2.hydropowerfx.presentationmodel;

import java.util.HashMap;
import java.util.Map;

    public enum Status {
        NORMAL("im Normalbetrieb"),
        STILL("stillgelegt"),
        BAU("im Bau");

        private final String name;
        private static final Map<String, Status> lookup = new HashMap<String, Status>();

        static {
            for (Status c : Status.values()) {
                lookup.put(c.getName(), c);
            }
        }

        Status(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static Status getStatus(String name) {
            return lookup.get(name);
        }

    }
