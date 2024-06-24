workspace {

    model {
        u = person "Insurance broker"
        ss = softwareSystem "Web Application" {
            rest = container "REST" "Facilitates creating RESTful endpoints for HTTP interactions." {

                controller = component "TravelCalculatePremiumController" {
                    description "Processes requests and delegates to TravelCalculatePremiumService."
                }
            }
            core = container "CORE" "Manages HTTP requests/responses, session data, and provides foundational interfaces for extending web functionalities." {
                service = component "TravelCalculatePremiumService" {
                    description "Calculates insurance premiums."
                }
                dateTimeService = component "DateTimeService" {
                    description "Calculates date differences."
                }
                validator = component "TravelCalculatePremiumRequestValidator" {
                    description "Validates premium calculation requests."
                }
            }
            dto = container "DTO" "Used to transfer data between different layers of an application." {
                request = component "TravelCalculatePremiumRequest" {
                    description "Handles incoming requests."
                }
                response = component "TravelCalculatePremiumResponse" {
                    description "Handles outgoing responses."
                }
                coreResponse = component "CoreResponse" {
                    description "Base response class for handling errors."
                }
                validationError = component "ValidationError"{
                    description "Represents an error in validation."
                }

            }
        }

        u -> ss "Uses"
        u -> rest "Uses"
        rest -> core "calculate response"
        request -> controller "Receives"
        controller -> service "Calls for premium calculation"
        service -> dateTimeService "Date from/Date to"
        response -> controller "Returns to"
        controller -> u "request/response"
        controller -> validator "Validates request"
        coreResponse -> response "extends coreResponse"
        coreResponse -> validationError "uses"
    }

    views {
        systemContext ss "BusinessView" {
            include *
            autolayout lr
        }

        container ss "ContainerView" {
            include *
            autolayout lr
        }

        component rest "REST_elements" {
            include *
            autolayout lr
        }
        component core "CORE_elements" {
            include *
            autolayout lr
        }
        component dto "DTO_elements" {
            include *
            autolayout lr
        }

        theme default
    }

}
