package application.model;

public class Priority {
        public String priority;
        public String desc;

        @Override
        public String toString() {
            return priority + ": " + desc;
        }

}
