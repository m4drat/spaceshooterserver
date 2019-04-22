package com.madrat.spaceshooter.apiserver.resourcereprs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.ArrayList;
import java.util.HashMap;

public class Help {

    private HashMap<String, ArrayList<Endpoint>> help;

    public Help() {
        help = new HashMap<>();
        ArrayList<Endpoint> endpoints = new ArrayList<>();

        HashMap <String, String> parameterRows1 = new HashMap<>();
        parameterRows1.put("count", "number how much users to show (Integer)");

        HashMap <String, String> example1 = new HashMap<>();
        example1.put("request", "/scoreboard?count=4}");
        example1.put("response", "{\"users\": [{\"score\": 1000, \"username\": \"admin\", \"serverUUID\": \"AAAA-BBBB-CCCC-1234\"}, {\"score\": 800, \"username\": \"madrat\", \"serverUUID\": \"1234-BBBB-CCCC-1234\"}, {\"score\": 600, \"username\": \"umfc\", \"serverUUID\": \"AAAA-1234-CCCC-1234\"}, {\"score\": 400, \"username\": \"lithium\", \"serverUUID\": \"AAAA-BBBB-1234-1234\"}]}");

        endpoints.add(new Endpoint("/scoreboard", "/scoreboard?count={count}", parameterRows1, example1, "Get users from scoreboard (count - how much)"));

        HashMap <String, String> parameterRows2 = new HashMap<>();
        parameterRows2.put("clientUUID", "unique client identifier (String)");
        parameterRows2.put("username", "username (alpha-numeric value)(String)");
        parameterRows2.put("score", "new score to set (Integer)");

        HashMap<String, String> example2 = new HashMap<>();
        example2.put("request", "/updatescore/1111-2222-AAAA-BBBB/admin?score=7700");
        example2.put("response", "{\"answer\": \"OK\"}");
        endpoints.add(new Endpoint("/updatescore", "/updatescore/{client_UUID}/{username}?score={score}", parameterRows2, example2, "set new score (will only be set if the previous one was smaller) (clientUUID - client unique user ID, username - actual username, score - score to set)"));

        help.put("endpoints", endpoints);
    }

    public HashMap<String, ArrayList<Endpoint>> getHelp() {
        return help;
    }

    private class Endpoint {
        private String endpoint;
        private String skeleton;
        private HashMap<String, String> parameters;
        private HashMap<String, String> example;
        private String description;

        public Endpoint(String endpoint, String skeleton, HashMap<String, String> parameters, HashMap<String, String> example, String description) {
            this.endpoint = endpoint;
            this.skeleton = skeleton;
            this.parameters = parameters;
            this.example = example;
            this.description = description;
        }

        public String getEndpoint() {
            return endpoint;
        }

        public String getSkeleton() {
            return skeleton;
        }

        public HashMap<String, String> getParameters() {
            return parameters;
        }

        public HashMap<String, String> getExample() {
            return example;
        }

        public String getDescription() {
            return description;
        }
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();

        String jsonString = "";
        try {
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            jsonString = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return jsonString;
    }
}
