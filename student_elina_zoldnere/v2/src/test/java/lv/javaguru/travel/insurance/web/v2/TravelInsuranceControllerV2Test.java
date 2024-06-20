package lv.javaguru.travel.insurance.web.v2;

import lv.javaguru.travel.insurance.config.SecurityConfig;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.services.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.dto.v2.DtoV2Converter;
import lv.javaguru.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import lv.javaguru.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;
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

@WebMvcTest(TravelInsuranceControllerV2.class)
@Import(SecurityConfig.class)
class TravelInsuranceControllerV2Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TravelCalculatePremiumService service;
    @MockBean
    private DtoV2Converter dtoV2Converter;

    @Test
    void showForm_ShouldReturnFormView() throws Exception {
        mockMvc.perform(get("/insurance/travel/web/v2"))
                .andExpect(status().isOk())
                .andExpect(view().name("travel-calculate-premium-v2"))
                .andExpect(model().attributeExists("request"));
    }

    @Test
    void processForm_ShouldReturnFormViewWithResponse() throws Exception {
        TravelCalculatePremiumRequestV2 request = new TravelCalculatePremiumRequestV2();
        TravelCalculatePremiumResponseV2 response = new TravelCalculatePremiumResponseV2();

        when(dtoV2Converter.buildCoreCommand(any(TravelCalculatePremiumRequestV2.class)))
                .thenReturn(new TravelCalculatePremiumCoreCommand());
        when(service.calculatePremium(any(TravelCalculatePremiumCoreCommand.class)))
                .thenReturn(new TravelCalculatePremiumCoreResult());
        when(dtoV2Converter.buildResponse(any(TravelCalculatePremiumCoreResult.class)))
                .thenReturn(response);

        mockMvc.perform(post("/insurance/travel/web/v2")
                        .flashAttr("request", request))
                .andExpect(status().isOk())
                .andExpect(view().name("travel-calculate-premium-v2"))
                .andExpect(model().attributeExists("response"))
                .andExpect(model().attribute("response", response));
    }

}