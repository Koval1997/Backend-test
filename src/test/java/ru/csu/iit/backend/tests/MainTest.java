package ru.csu.iit.backend.tests;

import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import ru.csu.iit.backend.models.DatasetModel;
import ru.csu.iit.backend.models.ProjectionDatasetModel;
import ru.csu.iit.backend.services.DatasetsService;
import ru.csu.iit.backend.builders.Attribute;
import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MainTest extends BaseTest {

    @Test(groups = "iit-62")
    public void getCountOfRows() {
        DatasetsService datasetsService = new DatasetsService(properties);
        RequestSpecification requestSpecification = datasetsService.request()
                .build();

        Response count = datasetsService.executeCountWithId(requestSpecification, getDatasetId());

        assertThat(count.body().print(), is("354"));
    }

    @Test(groups = "iit-57")
    public void getName() {
        DatasetsService datasetsService = new DatasetsService(properties);
        RequestSpecification requestSpecification = datasetsService.request()
                .top(1)
                .getFields("Name")
                .build();
        ProjectionDatasetModel[] data = datasetsService.executeWithId(requestSpecification, getDatasetId());
        assertThat(data.length,is(1));
        assertThat(data[0].getCells(),notNullValue());
        assertThat(data[0].getCells().containsKey("Name"),is(true));
    }

    private Integer getDatasetId() {
        DatasetsService datasetsService = new DatasetsService(properties);
        RequestSpecification requestSpecification = datasetsService.request()
                .attrubutesEq(new Attribute("Caption", "'Парковки такси'"))
                .getFields("Id")
                .build();
        DatasetModel[] datasets = datasetsService.execute(requestSpecification);

        return datasets[0].getId();
    }

    @Test(groups = "id")
    public void gettingDatasetId()
    {
        assertThat(getDatasetId(),is(621));
    }
}
