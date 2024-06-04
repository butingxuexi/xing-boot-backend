package cn.izoro.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author zoro
 */
@Data
@AllArgsConstructor
public class ErrorCode {

    /**
     * 错误码
     */
    private final Integer code;
    /**
     * 错误提示
     */
    private final String msg;
}
