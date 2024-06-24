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
                    validator = component "Validation Classes" {
                        description "TravelCalculatePremiumRequestValidator interface."
                        properties {
                            image "validations.png"
                        }
                    }
                    serviceInterface = component "TravelCalculatePremiumService" {
                        description "Declares a method 'calculatePremium'."
                    }
                    premiumUnderwriting = component "TravelPremiumUnderwriting" {
                        description "Calculates the premium."
                    }
                    util = component "util" {
                        tags "util"
                        description "Contains DateTimeUtil, ErrorCodeUtil"
                        properties {
                            image "util.png"
                        }
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
                resources = container "Resources" "Contains errorCodes"{
                    tags "container"
                    errorCodes = component "errorCodes.properties" {
                        description "Contains list of error codes"
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
        rest -> core "Delegates request to core for processing"
        request -> controller "Receives"
        controller -> service "Calls for response composition"
        premiumUnderwriting -> util "Uses for date calculations"
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
        service -> util "Uses for error descriptions"
        util -> errorCodes "Loads error codes to memory"

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
        image util {
            image util.png
        }
        image validator {
            image validations.png
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
            element "util" {
                icon util.png
            }
        }
        theme default

    }
}
