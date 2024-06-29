package org.javaguru.travel.insurance.core.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "agreements_xml_export")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgreementXmlExportEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "agreement_uuid", nullable = false)
    private String agreementUuid;

    @Column(name = "already_exported", nullable = false)
    private Boolean alreadyExported;

}
