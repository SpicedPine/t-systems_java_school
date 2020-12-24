package persistant;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patients")
public class PatientPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "diagnose", nullable = false)
    private String diagnose;

    @Column(name = "social_number", nullable = false)
    private int socialNumber;

    @Column(name = "physician", nullable = false)
    private String physician;

    /**
     * true->treated
     * false->treating
     */
    @Column(name = "status", nullable = false)
    private boolean status;

    /**
     * List with prescriptions for a certain patient
     */
    @OneToMany(mappedBy = "patient")
    List<PrescriptionPO> prescriptionList = new ArrayList<>();

    /**
     * List of events for a certain patient
     */
    @OneToMany(mappedBy = "patient")
    List<EventPO> eventList = new ArrayList<>();
}

