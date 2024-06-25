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

        component api "REST_elements" {
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
        }
        theme default

    }
}
