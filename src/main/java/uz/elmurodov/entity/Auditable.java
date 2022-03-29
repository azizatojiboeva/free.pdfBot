package uz.elmurodov.entity;

import lombok.*;

import java.util.Date;

/**
 * @author Elmurodov Javohir, Fri 5:16 PM. 12/17/2021
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Auditable implements BaseEntity {
    private String id;
    private Date createdAt;
}
