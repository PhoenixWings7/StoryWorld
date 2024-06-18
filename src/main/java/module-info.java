module StoryWorld {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;

    opens pl.edu.pjwstk.s24987 to javafx.fxml;
    opens pl.edu.pjwstk.s24987.model to org.hibernate.orm.core;
    exports pl.edu.pjwstk.s24987;
    exports pl.edu.pjwstk.s24987.model;
}