package org.besidescollege.domain.common;

import lombok.Data;

import java.sql.Date;

@Data
public abstract class Transaction {
    String fromAccountId;
    String toAccountId;
    Double amount;
    Date date;
}
