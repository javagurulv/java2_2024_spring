workspace {

    model {
        u = person "Insurance broker" "Browser"
        group "Insurance Application" {

            ss = softwareSystem "Web Application" "Spring Web Java" {
                rest = container "REST" "Facilitates creating RESTful endpoints for HTTP interactions." {
                    tags "container"
                    controller = component "TravelCalculatePremiumController" {
                        tags "component"
                        description "Processes requests and delegates to TravelCalculatePremiumService."
                    }
                    requestLogger = component "TravelCalculatePremiumRequest/ResponseLogger" {
                        description "Logs the request/response as JSON."
                    }
                    timeLogger = component "TravelCalculatePremiumRequestExecutionTimeLogger" {
                        description "Logs execution time."
                    }
                }
                core = container "CORE" "Manages HTTP requests/responses, session data, and provides foundational interfaces for extending web functionalities." {
                    tags "container"
                    service = component "TravelCalculatePremiumServiceImpl" {
                        description "Calculates insurance premiums and returns response."
                    }
                    dateTimeService = component "DateTimeService" {
                        description "Calculates date differences."
                    }
                    validator = component "Validation Classes" {
                        description "TravelCalculatePremiumRequestValidator interface."
                    }
                    serviceInterface = component "TravelCalculatePremiumService" {
                        description "Declares a method 'calculatePremium'."
                    }
                    premiumUnderwriting = component "TravelPremiumUnderwriting" {
                        description "Calculates the premium."
                    }
                }
                dto = container "DTO" "Used to transfer data between different layers of an application." {
                    tags "container"
                    request = component "TravelCalculatePremiumRequest" {
                        description "Handles incoming requests."
                    }
                    response = component "TravelCalculatePremiumResponse" {
                        description "Handles outgoing responses."
                    }
                    coreResponse = component "CoreResponse" {
                        description "Base response class for handling errors."
                    }
                    validationError = component "ValidationError" {
                        description "Represents an error in validation."
                    }
                }
            }
            database = softwaresystem "DataBase" "mySQL" {
                tags "Database"
                tables = container "Database tables" {
                    classifiers = component "Classifiers" {
                        description "Contains the list of classifiers."
                    }
                    classifier_values = component "ClassifierValues" {
                        description "Contains values related to each classifier."
                    }

                }
            }
        }




        u -> ss "Uses"
        u -> rest "Uses"
        rest -> core "Delegates to core for processing"
        request -> controller "Receives"
        controller -> service "Calls for response composition"
        service -> dateTimeService "Uses for date calculations"
        response -> controller "Returns to"
        controller -> u "request/response"
        controller -> validator "Validates request"
        coreResponse -> response "Extends CoreResponse"
        validationError -> coreResponse "Represents validation errors"
        service -> serviceInterface "Implements"
        service -> premiumUnderwriting "Uses for premium calculation"
        controller -> requestLogger "Logs request"
        controller -> timeLogger "Logs execution time"
        ss -> database "reads coef."

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
        component tables "Database_tables" {
            include *
            autolayout lr
        }
        styles {
            element "Database" {
                shape Cylinder
            }
            element "container" {
                shape Component
            }
            element "component" {
                shape RoundedBox
            }
        }
        theme default

    }
}
