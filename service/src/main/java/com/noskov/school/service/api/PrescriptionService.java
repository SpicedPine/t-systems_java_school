package com.noskov.school.service.api;


import com.noskov.school.dto.PrescriptionDTO;

import java.util.List;

public interface PrescriptionService {
    List<PrescriptionDTO> getAll();

    PrescriptionDTO getOne(Long id);

    void add(PrescriptionDTO prescription);

    void delete(Long id);

    void update(PrescriptionDTO prescription);
}
