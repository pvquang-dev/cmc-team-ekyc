package casa.lead.api.response;

import casa.lead.domain.message.MessageEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse<T> {
    private static String SUCCESS = "SUCCESS";
    private static String FAIL = "FAIL";

    private String status;
    private String code;
    private String message;
    private T data;

    public BaseResponse() {
        //Constructor
    }

    public BaseResponse(MessageEnum message, String status) {
        this.code = message.getCode();
        this.message = message.getMessage();
        this.status = status;
    }

    public static <T> BaseResponse<T> success(T data) {
        BaseResponse<T> res = new BaseResponse<>(MessageEnum.OK, BaseResponse.SUCCESS);
        res.setData(data);
        return res;
    }

    public static <T> BaseResponse<T> success() {
        return new BaseResponse<>(MessageEnum.OK, BaseResponse.SUCCESS);
    }

    public static <T> BaseResponse<T> error(MessageEnum message) {
        return new BaseResponse<T>(message, BaseResponse.FAIL);
    }

    public static <T> BaseResponse<T> error(String code, String desc) {
        BaseResponse<T> res = new BaseResponse<T>();
        res.setCode(code);
        res.setMessage(desc);
        return res;
    }

    public static <T> BaseResponse<T> error(MessageEnum message, T data) {
        BaseResponse<T> res = new BaseResponse<T>(message, BaseResponse.FAIL);
        res.setData(data);
        return res;
    }

    public static <T> BaseResponse<T> error(String code, String desc, T data) {
        BaseResponse<T> res = new BaseResponse<T>();
        res.setCode(code);
        res.setMessage(desc);
        res.setData(data);
        return res;
    }

}
