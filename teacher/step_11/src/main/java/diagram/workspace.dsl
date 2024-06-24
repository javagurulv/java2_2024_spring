workspace {

    model {
        u = person "Insurance broker"
        ss = softwareSystem "Web Application" {
            rest = container "REST" "Facilitates creating RESTful endpoints for HTTP interactions." {
                request = component "TravelCalculatePremiumRequest" {
                    description "Handles incoming requests."
                }
                response = component "TravelCalculatePremiumResponse" {
                    description "Handles outgoing responses."
                }
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

        theme default
    }

}
