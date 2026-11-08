module com.example.ufcgram {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires atlantafx.base;
    requires atlantafx.validation;
    requires java.prefs;
    requires password4j;
    requires org.postgresql.jdbc;

    exports com.example.ufcgram;

    opens com.example.ufcgram to
            javafx.fxml;
    opens com.example.ufcgram.controller to
            javafx.fxml;
}
