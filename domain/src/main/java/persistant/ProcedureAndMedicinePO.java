package persistant;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "proc_and_med")
public class ProcedureAndMedicinePO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    /**
     * true->procedure
     * false->medicine
     */
    @Column(name = "type",nullable = false)
    private boolean type;

    /**
     * List of events for certain procedure or medicine
     */
    @OneToMany(mappedBy = "eventType")
    List<EventPO> eventList = new ArrayList<>();

    /**
     * List of prescriptions for certain prescription or medicine
     */
    @OneToMany(mappedBy = "prescriptionType")
    List<PrescriptionPO> prescriptionList = new ArrayList<>();

}
