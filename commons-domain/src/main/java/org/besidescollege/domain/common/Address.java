package org.besidescollege.domain.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    String houseNumber;
    String streetName;
    String city;
    String pinCode;
}
