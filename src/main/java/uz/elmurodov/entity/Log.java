package uz.elmurodov.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author Elmurodov Javohir, Fri 5:44 PM. 12/17/2021
 */
@Getter
@Setter
@NoArgsConstructor
public class Log extends Auditable {
    private String data;
    private Long chatId;


    @Builder(builderMethodName = "childBuilder")
    public Log(String id, Date createdAt, String data, Long chatId) {
        super(id, createdAt);
        this.data = data;
        this.chatId = chatId;
    }

}
