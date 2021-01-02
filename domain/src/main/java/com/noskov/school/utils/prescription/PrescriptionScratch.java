package com.noskov.school.utils.prescription;

import com.noskov.school.utils.TherapyType;

public class PrescriptionScratch {
    class Type{
        private TherapyType type;
    }

    class TimePattern{
        private int quantity;
        private TimePeriods frequency;
        private String additionalInformation;
    }

    class Period{
        private int quantity;
        private TimePeriods timePeriod;
    }

    class Dose{
        private String dose;
    }
}
