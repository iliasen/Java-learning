package by.iba.sfpetclinic.model;

import java.time.LocalDate;

@Data
public class Pet {
    private PetType petType;
    private Owner owner;
    private LocalDate birthDate;
}
