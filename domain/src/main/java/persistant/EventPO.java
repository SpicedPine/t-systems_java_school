package persistant;

import utils.EventStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "events")
public class EventPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id",nullable = false)
    private PatientPO patient;

    @Column(name = "date_and_time",  nullable = false)
    private Date dateAndTime;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @ManyToOne
    @JoinColumn(name = "proc_or_medic_id",nullable = false)
    private ProcedureAndMedicinePO eventType;
}
