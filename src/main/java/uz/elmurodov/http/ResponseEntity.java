package uz.elmurodov.http;

import lombok.Getter;

import java.util.Date;

/**
 * @author Elmurodov Javohir, Wed 11:14 AM. 12/15/2021
 */
@Getter
public class ResponseEntity<B> {
    private B body;
    private Integer code;
    private Date time;

    public ResponseEntity(B body) {
        this(body, HttpStatus.HTTP_200);
    }

    public ResponseEntity(B body, HttpStatus status) {
        this.body = body;
        this.code = status.getCode();
        this.time = new Date();
    }
}
