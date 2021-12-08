package ticket;

import java.io.Serializable;

public enum TicketType implements Serializable {
    VIP,
    USUAL,
    BUDGETARY,
    CHEAP;
}