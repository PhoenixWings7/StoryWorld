module StoryWorld {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;

    opens pl.edu.pjwstk.s24987 to javafx.fxml;
    exports pl.edu.pjwstk.s24987;
}