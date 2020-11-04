package org.besidescollege.domain.account;

import lombok.*;
import org.besidescollege.domain.common.Address;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "account")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Account {

    @Id
    private String id;
    private String name;
    private Address address;
    private String phone;
    private String phoneAlternate;
}
