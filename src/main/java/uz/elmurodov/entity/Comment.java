package uz.elmurodov.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Narzullayev Husan, вс 14:13. 02.01.2022
 */
@Getter
@Setter

public class Comment extends Auditable{

    private String chatId;
    private String fullName;
    private String userName;
    private String comment;


}
