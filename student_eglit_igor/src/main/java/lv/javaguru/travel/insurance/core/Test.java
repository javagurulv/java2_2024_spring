package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class Test implements CommandLineRunner {
    private TravelCalculatePremiumServiceImpl service;

    @Autowired
    public Test(TravelCalculatePremiumServiceImpl service) {
        this.service = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(Test.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "Igor",
                "Eglit",
                LocalDate.of(2024, 12, 12),
                LocalDate.of(2024, 12, 14));
        System.out.println(service.calculatePremium(request).getAgreementPrice()+" Agreement price \n" +
                service.calculatePremium(request).getPersonFirstName()+" FIrst Name \n"+
                service.calculatePremium(request).getPersonLastName()+" Second Name \n"+
                service.calculatePremium(request).getAgreementDateFrom()+" Date From \n"+
                service.calculatePremium(request).getAgreementDateTo()+" Date To \n");
    }
}