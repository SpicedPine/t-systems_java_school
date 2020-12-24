package persistant;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "prescriptions")
public class PrescriptionPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientPO patient;

    @ManyToOne
    @JoinColumn(name = "proc_or_med_id", nullable = false)
    private ProcedureAndMedicinePO prescriptionType;

    @Column(name = "time_pattern",nullable = false)
    private String timePattern;

    @Column(name = "period", nullable = false)
    private Date period;

    @Column(name = "dose", nullable = true)
    private float dose;
}
