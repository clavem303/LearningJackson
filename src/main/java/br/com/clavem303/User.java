package br.com.clavem303;

import com.fasterxml.jackson.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;

    @JsonAlias({"last_name", "lastName"}) // For use without @JsonProperty
    private String lastName;

    private String email;

    @JsonIgnore // Don't show in serialization.
    private String password;

    @JsonInclude(JsonInclude.Include.NON_EMPTY) // Don't show if empty.
    private List<String> hobbies = new ArrayList<>();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
    private final Instant created;

    @JsonCreator
    public User(
            @JsonProperty("name") String name,
            @JsonProperty("last_name") String lastName,
            @JsonProperty("email") String email,
            @JsonProperty("password") String password,
            @JsonProperty("hobbies")  List<String> hobbies,
            @JsonProperty("created")  Instant created
    ) {
        validate(name, lastName, email, password);
        this.hobbies = hobbies;
        this.created = created;
    }

    private void validate(String name, String lastName, String email, String password) {
        this.name = name.trim().toUpperCase();
        this.lastName = lastName.trim().toUpperCase();
        this.email = email.trim().toLowerCase();
        this.password = password.trim();
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + maskPassword(password) + '\'' +
                ", hobbies=" + hobbies + '\'' +
                ", created=" + created +
                '}';
    }

    private String maskPassword(String password) {
        return "*".repeat(password.length());
    }
}
