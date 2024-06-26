workspace {

    model {
        u = person "Insurance broker" "Browser"
        group "Insurance Application" {

            core = softwareSystem "CORE" {
                description "Core system for managing insurance calculations."
                api = container "API" "API for core, on which REST and WEB depend." {
                    tags "container"
                    command = component "COMMAND" {
                        tags "command"
                        description "Handles command operations."
                    }
                    dto = component "DTO" {
                        tags "dto"
                        description "Data Transfer Objects for API interactions."
                    }
                }
                util = container "UTIL" "Utility functions used across the application." {
                    tags "container"
                    utilComponent = component "UTIL Component" {
                        tags "utilComponent"
                        description "Provides utility functions."
                    }
                }
                domain = container "DOMAIN" "Domain entities representing core concepts." {
                    tags "container"
                    domainComponent = component "DOMAIN Component" {
                        tags "domainComponent"
                        description "Contains domain entities and their logic."
                    }
                }
                repositories = container "REPOSITORIES" "Data access layer for domain entities." {
                    tags "container"
                    repositoriesComponent = component "REPOSITORIES Component" {
                        tags "repositoriesComponent"
                        description "Handles data access for domain entities."
                    }
                }
                validations = container "VALIDATIONS" "Validation logic for business rules." {
                    tags "container"
                    validationsComponent = component "VALIDATIONS Component" {
                        tags "validationsComponent"
                        description "Validates inputs and business rules."
                    }
                }

                underwriting = container "UNDERWRITING" "Handles insurance underwriting logic." {
                    tags "container"
                    underwritingComponent = component "UNDERWRITING Component" {
                        tags "underwritingComponent"
                        description "Processes underwriting rules and calculations."
                    }
                }

                services = container "SERVICES" "Business logic layer that orchestrates processes." {
                    tags "container"
                    servicesComponent = component "SERVICES Component" {
                        tags "servicesComponent"
                        description "Orchestrates core business processes and calls."
                    }
                }
                resources = container "Resources" "Contains errorCodes" {
                    tags "container"
                    errorCodes = component "errorCodes.properties" {
                        description "Contains list of error codes"
                    }
                    db = component "db_changelog" {
                        description "Liquibase"
                    }
                    templates = component "templates" {
                        description "WebPage template on Thymeleaf"
                    }
                }

            }
            database = softwaresystem "DataBase" "mySQL" {
                tags "Database"
                tables = container "Database tables" {
                    classifiers = component "Classifiers" {
                        description "Contains the list of classifiers.\n(title, description)"
                    }
                    classifier_values = component "ClassifierValues" {
                        description "Contains values related to each classifier.\nclassifier_id, ic, description)"
                    }
                    countryDayRate = component "COUNTRY_DEFAULT_DAY_RATE" {
                        description "Contains rates for countries.\n(country_ic, default_day_rate)"
                    }
                    ageCoefficient = component "AGE_COEFFICIENT" {
                        description "Contains coefficients corresponding to age. \n(age_from, age_to, coefficient)"
                    }
                    medicalRiskLimit = component "MEDICAL_RISK_LIMIT_LEVEL" {
                        description "Contains limits and it rates.\n(MEDICAL_RISK_LIMIT_LEVEL_IC, COEFFICIENT)"
                    }

                }
            }
        }

        u -> core "Uses API"
        core -> database
        core -> database "Stores data in"
        api -> database "Accesses data from"

        repositoriesComponent -> domainComponent "Depends on"
        validationsComponent -> utilComponent "Depends on"
        validationsComponent -> domainComponent "Depends on"
        validationsComponent -> repositoriesComponent "Depends on"
        underwritingComponent -> utilComponent "Depends on"
        underwritingComponent -> domainComponent "Depends on"
        underwritingComponent -> repositoriesComponent "Depends on"
        servicesComponent -> validationsComponent "Depends on"
        servicesComponent -> underwritingComponent "Depends on"
    }



    views {
        /*systemlandscape "SystemLandscape" {
            include *
            autolayout lr
        }*/
        systemContext core "CotextView" {
            include *
            autolayout lr
        }

        container core "ContainerView" {
            include *
            autolayout lr
        }

        component api "API_view" {
            include *
            autolayout lr
        }

        component util "UTIL_view" {
            include *
            autolayout lr
        }

        component domain "DOMAIN_view" {
            include *
            autolayout lr
        }

        component repositories "REPOSITORIES_view" {
            include *
            autolayout lr
        }

        component validations "VALIDATIONS_view" {
            include *
            autolayout lr
        }

        component underwriting "UNDERWRITING_view" {
            include *
            autolayout lr
        }

        component services "SERVICES_view" {
            include *
            autolayout lr
        }
        component resources "Resources_elements" {
            include *
            autolayout lr
        }
        component tables "Database_tables" {
            include *
            autolayout lr
        }

        image command {
            image command.png
        }
        image dto {
            image dto.png
        }
        image domain {
            image domain.png
        }
        image repositories {
            image repositories.png
        }
        image util {
            image util.png
        }
        image validations {
            image validations.svg
        }
        image underwriting {
            image underwriting.png
        }
        image services {
            image services.png
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
            element "command" {
                icon command.png
            }
            element "dto" {
                icon dto.png
            }
            element "utilComponent" {
                icon util.png
            }
            element "domainComponent" {
                icon domain.png
            }
            element "repositoriesComponent" {
                icon repositories.png
            }
            element "validationsComponent" {
                icon validations.svg
            }
            element "underwritingComponent" {
                icon underwriting.png
            }
            element "servicesComponent" {
                icon services.png
            }
        }
        theme default

    }
}
