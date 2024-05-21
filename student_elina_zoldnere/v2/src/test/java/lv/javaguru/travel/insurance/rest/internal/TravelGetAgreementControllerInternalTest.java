package lv.javaguru.travel.insurance.rest.internal;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lv.javaguru.travel.insurance.core.domain.entities.AgreementEntity;;
import lv.javaguru.travel.insurance.rest.JsonFileReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class TravelGetAgreementControllerInternalTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JsonFileReader reader;
    @Autowired
    private Gson gson;
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void controller_ShouldFindExistingUuid() throws Exception {
        JsonObject testDataJson = gson.fromJson(reader.readJsonFromFile(
                        "internal/ControllerInternalTest_Combined_9.01_all_fields_are_present_and_valid.json"),
                JsonObject.class);
        JsonObject setUpRequestJson = testDataJson.getAsJsonObject("set-up request");
        JsonObject expectedResponseJson = testDataJson.getAsJsonObject("expectedResponse");

        MockHttpServletResponse setUpResponse = mockMvc.perform(post("/insurance/travel/api/v2/")
                        .content(setUpRequestJson.toString())
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        AgreementEntity insertedAgreement = findLastInsertedAgreement();
        String insertedUuid = insertedAgreement.getUuid();

        expectedResponseJson.addProperty("uuid", insertedUuid);

        MockHttpServletResponse response = mockMvc.perform(get("/insurance/travel/api/internal/agreement/" + insertedUuid)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        String expectedResponseAsString = expectedResponseJson.toString();
        String actualResponseAsString = response.getContentAsString(StandardCharsets.UTF_8);

        assertJson(actualResponseAsString)
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(expectedResponseAsString);
    }

    private AgreementEntity findLastInsertedAgreement() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<AgreementEntity> cq = cb.createQuery(AgreementEntity.class);
        Root<AgreementEntity> root = cq.from(AgreementEntity.class);
        cq.orderBy(cb.desc(root.get("id")));
        TypedQuery<AgreementEntity> query = entityManager.createQuery(cq);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

}
