workspace {

    model {
        u = person "Insurance broker"
        ss = softwareSystem "Web Application" {
            rest = container "REST" "Facilitates creating RESTful endpoints for HTTP interactions." {
                u -> this "sends HTTP Request"
                request = component "TravelCalculatePremiumRequest" {
                    description "Handles incoming requests."
                }
                response = component "TravelCalculatePremiumResponse" {
                    description "Handles outcoming response"
                }
                controller = component "Controller Rest API" {
                    u -> this "HTTP Request"
                    description "Processes requests and delegates to TravelCalculatePremiumService."
                }
            }
            core = container "CORE" "Manages HTTP requests/responses, session data, and provides foundational interfaces for extending web functionalities." {
                service = component "TravelCalculatePremiumService" {
                    description "Process premium calculation and return response"
                }
            }
        }



        rest -> core "sends json request"
        request -> controller "POST request to map"
        controller -> service "Calls for premium calculation"
        service -> response "Returns calculated premium"
        response -> controller "response to map"
        controller -> u "forward response to broker browser"
    }

    views {
        systemContext ss "BusinessView" {
            include *

        }

        container ss "REST" {
            include *

        }

        component rest "REST_elements" {
            include *

        }
        component core "CORE_elements" {
            include *

        }

        theme default
    }

}
