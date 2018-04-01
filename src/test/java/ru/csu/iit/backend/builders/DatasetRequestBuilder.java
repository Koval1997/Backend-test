package ru.csu.iit.backend.builders;

import io.restassured.specification.RequestSpecification;

public class DatasetRequestBuilder {
    private RequestSpecification requestSpecification;

    public DatasetRequestBuilder(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    public DatasetRequestBuilder top(int top) {
        requestSpecification.queryParams("$top", top);
        return this;
    }

    public DatasetRequestBuilder filter(String filter) {
        requestSpecification.queryParams("$filter", filter);
        return this;
    }

    public DatasetRequestBuilder contains(String attributeName, String attributeValueSubstring) {
        return filter(String.format("substringof('%s', %s) eq true", attributeValueSubstring, attributeName));
    }

    public DatasetRequestBuilder attrubutesEq(Attribute... attrs) {
        String filter = new String("");
        filter += String.format("%s eq %s", attrs[0].name, attrs[0].value);
        for (int i = 1; i < attrs.length; i++)
        {
            filter += String.format(" and %s eq %s", attrs[i].name, attrs[i].value);
        }
        return filter(filter);
    }

    public DatasetRequestBuilder getFields(String... fields) {
        requestSpecification.body(fields);
        return this;
    }

    public RequestSpecification build() {
        return requestSpecification;
    }
}
