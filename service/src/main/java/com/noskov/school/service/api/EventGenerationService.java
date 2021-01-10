package com.noskov.school.service.api;

import com.noskov.school.persistent.PrescriptionPO;

public interface EventGenerationService {
    void generateEvents(PrescriptionPO prescription) throws Exception;
}
