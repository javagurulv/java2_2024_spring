package lv.javaguru.travel.insurance.rest.internal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance/travel/api/internal/agreement")
class TravelGetAgreementControllerInternal {

    @GetMapping(path = "/{uuid}",
            produces = "application/json")
    public String getAgreement(@PathVariable String uuid) {
        return uuid;
    }

}