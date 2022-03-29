package uz.elmurodov.http;

import lombok.Getter;

/**
 * @author Elmurodov Javohir, Wed 11:36 AM. 12/15/2021
 */
@Getter
public class Data<T> {
    private T data;
    private Integer total;

    public Data(T data) {
        this(data, null);
    }

    public Data(T data, Integer total) {
        this.data = data;
        this.total = total;
    }
}
