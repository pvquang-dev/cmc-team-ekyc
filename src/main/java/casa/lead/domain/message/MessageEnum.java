package casa.lead.domain.message;

import lombok.Getter;

@Getter
public enum MessageEnum {
    OK("00", "Success"),
    UNHANDLED_ERROR("401", "Hệ thống xảy ra lỗi, vui lòng thử lại", "%s"),
    CALL_HTTP_ERROR("500", "Call Http Has Error", "%s"),
    REQUEST_TIMEOUT("501", "Request time out", "%s");

    private final String code;
    private String message;
    private String messageFormat;


    MessageEnum(String code, String msgDefault) {
        this.code = code;
        this.message = msgDefault;
    }

    MessageEnum(String code, String message, String messageFormat) {
        this.code = code;
        this.message = message;
        this.messageFormat = messageFormat;
    }

    public MessageEnum format(Object... str) {
        if (this.messageFormat != null) {
            this.message = String.format(this.messageFormat, str);
        }
        return this;
    }
}
