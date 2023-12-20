package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.response;

import lombok.ToString;

@ToString
public enum ApiResponseCode {

    SUCCESS("200", "OK"),
    CREATED("201", "Created"),
    ACCEPTED("202", "Accepted"),
    NON_AUTHORITATIVE_INFO("203", "Non-Authoritative Information"),
    NO_CONTENT("204", "No Content"),
    RESET_CONTENT("205", "Reset Content"),
    PARTIAL_CONTENT("206", "Partial Content"),

    MULTIPLE_CHOICES("300", "Multiple Choices"),
    MOVED_PERMANENTLY("301", "Moved Permanently"),
    FOUND("302", "Found"),
    SEE_OTHER("303", "See Other"),
    NOT_MODIFIED("304", "Not Modified"),
    USE_PROXY("305", "Use Proxy"),
    TEMPORARY_REDIRECT("307", "Temporary Redirect"),
    PERMANENT_REDIRECT("308", "Permanent Redirect"),

    BAD_REQUEST("400", "Bad Request"),
    UNAUTHORIZED_ERROR("401", "Unauthorized Error"),
    PAYMENT_REQUIRED("402", "Payment Required"),
    FORBIDDEN_ERROR("403", "Forbidden Error"),
    NOT_FOUND_ERROR("404", "Not Found Error"),
    METHOD_NOT_ALLOWED_ERROR("405", "Method Not Allowed Error"),
    NOT_ACCEPTABLE("406", "Not Acceptable"),
    PROXY_AUTH_REQUIRED("407", "Proxy Authentication Required"),
    REQUEST_TIMEOUT("408", "Request Timeout"),
    CONFLICT("409", "Conflict"),
    GONE("410", "Gone"),
    LENGTH_REQUIRED("411", "Length Required"),
    PRECONDITION_FAILED("412", "Precondition Failed"),
    PAYLOAD_TOO_LARGE("413", "Payload Too Large"),
    URI_TOO_LONG("414", "URI Too Long"),
    UNSUPPORTED_MEDIA_TYPE("415", "Unsupported Media Type"),
    RANGE_NOT_SATISFIABLE("416", "Range Not Satisfiable"),
    EXPECTATION_FAILED("417", "Expectation Failed"),
    IM_A_TEAPOT("418", "I'm a teapot"),

    UPGRADE_REQUIRED("426", "Upgrade Required"),
    PRECONDITION_REQUIRED("428", "Precondition Required"),
    TOO_MANY_REQUESTS("429", "Too Many Requests"),

    INTERNAL_SERVER_ERROR("500", "Internal Server Error"),
    NOT_IMPLEMENTED("501", "Not Implemented"),
    BAD_GATEWAY_ERROR("502", "Bad Gateway Error"),
    SERVICE_UNAVAILABLE_ERROR("503", "Service Unavailable Error"),
    GATEWAY_TIMEOUT_ERROR("504", "Gateway Timeout Error"),
    HTTP_VERSION_NOT_SUPPORTED("505", "HTTP Version Not Supported"),
    VARIANT_ALSO_NEGOTIATES("506", "Variant Also Negotiates"),
    INSUFFICIENT_STORAGE("507", "Insufficient Storage"),
    LOOP_DETECTED("508", "Loop Detected"),
    NOT_EXTENDED("510", "Not Extended"),
    NETWORK_AUTH_REQUIRED("511", "Network Authentication Required"),
    UNKNOWN_ERROR("999", "Unknown Error");


    private final String code;

    private final String defaultMessage;

    ApiResponseCode(String code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    public String getCode() {
        return this.code;
    }

    public String getDefaultMessage() {
        return this.defaultMessage;
    }
}
