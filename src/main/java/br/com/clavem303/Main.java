package br.com.clavem303;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        /* Create the object (jackson-databind extension) responsible for mapping the string or object.*/
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule()); //Register module to work with date.

        /* Deserializing --------------------------------------------------------*/

        /* 1 - Create a json string. */
        String douglasCrockfordJson = """
                {
                    "name": "Douglas",
                    "last_name": "Crockford",
                    "email": "DOUGLAS.CROCKFORD@JSON.COM",
                    "password": "491637",
                    "hobbies": ["read", "swim", "fish"],
                    "created":"10/08/2025 20:51:07"
                }
                """;

        /* 2 - Call the method responsible for converting the json string into a java object*/
        try {
            User douglasCrockfordObject = mapper.readValue(douglasCrockfordJson, User.class);
            System.out.println(douglasCrockfordObject);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        /* Serializing --------------------------------------------------------*/

        /* 1 - Create a java object. */
        List<String> tatuSalorantaHobbies = new ArrayList<>();

        // tatuSalorantaHobbies.add("box"); // Comment to test empty property.

        User tatuSalorantaObject = new User(
                "Tatu",
                "Saloranta",
                "TATU.SALORANTA@FASTERXML.COM",
                "619473",
                tatuSalorantaHobbies,
                Instant.now());

        /* 2 - Call the method responsible for converting the java object into a json string. */
        try {
            String tatuSalorantaJson = mapper.writeValueAsString(tatuSalorantaObject);
            System.out.println(tatuSalorantaJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}