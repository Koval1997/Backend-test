package ru.csu.iit.backend.models;

import com.fasterxml.jackson.annotation.*;

import java.util.Map;

@JsonIgnoreProperties({"Number"})
public class ProjectionDatasetModel {
    @JsonProperty(value = "global_id")
    private int id;
    @JsonProperty(value = "Cells")
    private Map<String,String> cells;

    public int getGlobalId() {
        return id;
    }

    public void setGlobalId(int id) {
        this.id = id;
    }

    public Map<String,String> getCells() {
        return cells;
    }

    public void setCells(Map<String,String> cells) {
        this.cells = cells;
    }


    @Override
    public String toString() {
        return "ProjectionDatasetModel{" +
                "id=" + id +
                ", name='" + cells.get("Name") + '\'' +
                '}';
    }
}
