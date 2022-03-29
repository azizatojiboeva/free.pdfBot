package uz.elmurodov.http;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Elmurodov Javohir, Mon 12:28 PM. 11/29/2021
 */

@Getter
@AllArgsConstructor
public enum HttpStatus {
    HTTP_100(100, "Continue"),
    HTTP_101(101, "Switching Protocols"),
    HTTP_200(200, "OK"),
    HTTP_201(201, "Created"),
    HTTP_202(202, "Accepted"),
    HTTP_203(203, "Non-authoritative Information"),
    HTTP_204(204, "No Content"),
    HTTP_205(205, "Reset Content"),
    HTTP_206(206, "Partial Content"),
    HTTP_300(300, "Multiple Choices"),
    HTTP_301(301, "Moved Permanently"),
    HTTP_302(302, "Found"),
    HTTP_303(303, "See Other"),
    HTTP_304(304, "Not Modified"),
    HTTP_305(305, "Use Proxy"),
    HTTP_306(306, "Unused"),
    HTTP_307(307, "Temporary Redirect"),
    HTTP_400(400, "Bad Request"),
    HTTP_401(401, "UnAuthorized"),
    HTTP_402(402, "Payment Required"),
    HTTP_403(403, "Forbidden"),
    HTTP_404(404, "Not Found"),
    HTTP_405(405, "Method not allowed"),
    HTTP_406(406, "Not Acceptance"),
    HTTP_407(407, "Proxy Authentication Required"),
    HTTP_408(408, "Request Timeout"),
    HTTP_409(409, "Conflict"),
    HTTP_410(410, "Gone"),
    HTTP_411(411, "Length Required"),
    HTTP_412(412, "Precondition Failed"),
    HTTP_413(413, "Request Entity Too Large"),
    HTTP_414(414, "Request-url Too Long"),
    HTTP_415(415, "Unsupported Media Type"),
    HTTP_417(417, "Expectation Failed"),
    HTTP_500(500, "Internal Server Error"),
    HTTP_501(501, "Not Implemented"),
    HTTP_502(502, "Bad Gateway"),
    HTTP_503(503, "Service Unavailable"),
    HTTP_504(504, "Gateway Timeout"),
    HTTP_505(505, "HTTP Version Not Supported"),
    // custome status codes
    HTTP_STATUS_CODE_NOT_FOUND(600, "Status code is invalid"),

    UNDEFINED(-1, "-1");
    private Integer code;
    private String description;

    public static HttpStatus getStatusByCode(Integer code) {
        for (HttpStatus httpStatus : values()) {
            if (httpStatus.getCode().equals(code)) return httpStatus;
        }
        return UNDEFINED;
    }
}
