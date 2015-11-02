package com.cloudsherpas.droolsample.api.resource;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RMPader
 */
public class SuggestionResource {

    private List<String> suggestions = new ArrayList<>();

    public List<String> getSuggestions() {
        return suggestions;
    }

    public void addSuggestion(String suggestion) {
        suggestions.add(suggestion);
    }
}
