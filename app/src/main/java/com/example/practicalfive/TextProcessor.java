package com.example.practicalfive;

public class TextProcessor {

    private static final String[] SUPPORTED_MUNICIPALITIES = {
            "Beijing",
            "Shanghai",
            "Tianjin",
            "Chongqing"
    };

    public String processMunicipality(String municipality) {
        if (municipality == null) {
            return null;
        }

        String candidate = municipality.trim();

        if (candidate.isEmpty()) {
            return null;
        }

        for (String name : SUPPORTED_MUNICIPALITIES) {
            if (name.equalsIgnoreCase(candidate)) {
                return name;
            }
        }

        return null;
    }
}
