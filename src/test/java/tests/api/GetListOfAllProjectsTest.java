package tests.api;

import adapters.ProjectAdapter;
import baseEntities.BaseApiTest;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import models.Project;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.json.TypeToken;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GetListOfAllProjectsTest extends BaseApiTest {

    static Logger logger = LogManager.getLogger(GetListOfAllProjectsTest.class);

    @Test
    public void getAllProjectsTest() throws FileNotFoundException {

        Gson gson = new Gson();

        AddProjectTest expectedProject = new AddProjectTest();
        expectedProject.addProject();

        Response response = new ProjectAdapter().getAllProjects();

        Type listType = new TypeToken<ArrayList<Project>>() {
        }.getType();
        JsonObject respAsJsonObject = gson.fromJson(response.getBody().asString(), JsonObject.class);
        JsonArray respAsJsonArray = respAsJsonObject.getAsJsonArray("data");

        List<Project> actualProjects = gson.fromJson(respAsJsonArray, listType);
        logger.info("List of all projects: ");
        for (Project p :
                actualProjects) {
            logger.info(p);
        }

        Assert.assertEquals(expectedProject.getActualProject(), actualProjects.get(actualProjects.size() - 1));
        logger.info("Last added project is in the list");

    }
}
