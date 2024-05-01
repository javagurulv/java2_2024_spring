package lv.javaguru.travel.insurance.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "classifier_values")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassifierValue {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "classifier_id", nullable = false)
    private Classifier classifier;

    @Column(name = "ic")
    private String ic;

    @Column(name = "description")
    private String description;

}
