package lv.javaguru.travel.insurance.web.v1;

import lv.javaguru.travel.insurance.config.SecurityConfig;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.services.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.dto.v1.DtoV1Converter;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(TravelInsuranceControllerV1.class)
@Import(SecurityConfig.class)
class TravelInsuranceControllerV1Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TravelCalculatePremiumService service;
    @MockBean
    private DtoV1Converter dtoV1Converter;

    @Test
    void showForm_ShouldReturnFormView() throws Exception {
        mockMvc.perform(get("/insurance/travel/web/v1"))
                .andExpect(status().isOk())
                .andExpect(view().name("travel-calculate-premium-v1"))
                .andExpect(model().attributeExists("request"));
    }

    @Test
    void processForm_ShouldReturnFormViewWithResponse() throws Exception {
        TravelCalculatePremiumRequestV1 request = new TravelCalculatePremiumRequestV1();
        TravelCalculatePremiumResponseV1 response = new TravelCalculatePremiumResponseV1();

        when(dtoV1Converter.buildCoreCommand(any(TravelCalculatePremiumRequestV1.class)))
                .thenReturn(new TravelCalculatePremiumCoreCommand());
        when(service.calculatePremium(any(TravelCalculatePremiumCoreCommand.class)))
                .thenReturn(new TravelCalculatePremiumCoreResult());
        when(dtoV1Converter.buildResponse(any(TravelCalculatePremiumCoreResult.class)))
                .thenReturn(response);

        mockMvc.perform(post("/insurance/travel/web/v1")
                        .flashAttr("request", request))
                .andExpect(status().isOk())
                .andExpect(view().name("travel-calculate-premium-v1"))
                .andExpect(model().attributeExists("response"))
                .andExpect(model().attribute("response", response));
    }

}
