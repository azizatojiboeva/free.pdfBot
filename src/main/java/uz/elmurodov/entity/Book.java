package uz.elmurodov.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Book extends Auditable{
    private String bookName;
    private String id;
    private String description;
    private String Author;
    private String category;
    private String ownerId;
}
