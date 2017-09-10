package com.miner.common.exception;

import com.miner.common.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * 异常处理器
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年10月27日 下午10:16:19
 */
@RestControllerAdvice()
public class MinerExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 自定义异常
	 */
	@ExceptionHandler(MinerException.class)
	public R handleMinerException(MinerException e){
		R r = new R();
		r.put("code", e.getCode());
		r.put("msg", e.getMessage());
		return r;
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public R handleDuplicateKeyException(DuplicateKeyException e){
		logger.error(e.getMessage(), e);
		return R.error("数据库中已存在该记录");
	}

    @ExceptionHandler(NoHandlerFoundException.class)
    public R handNoHandlerFoundException(NoHandlerFoundException e){
        logger.error(e.getMessage(), e);
        return R.error("不要瞎猜我们的接口！");
    }
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public R handleValidateException(MethodArgumentNotValidException e){
        logger.error(e.getMessage(), e);
        //按需重新封装需要返回的错误信息
        List<ArgumentInvalidResult> invalidArguments = new ArrayList<>();
        //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            ArgumentInvalidResult invalidArgument = new ArgumentInvalidResult();
            invalidArgument.setDefaultMessage(error.getDefaultMessage());
            invalidArgument.setField(error.getField());
            invalidArgument.setRejectedValue(error.getRejectedValue());
            invalidArguments.add(invalidArgument);
        }
        return R.error(40001,"参数不正确").put("arg_errors",invalidArguments);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public R handleAccessDeniedException(AccessDeniedException e){
        logger.error(e.getMessage(), e);
        return R.error(401,"没有权限，请联系管理员授权");
    }
    @ExceptionHandler(DisabledException.class)
    public R handleDisableException(DisabledException e){
        logger.error(e.getMessage(), e);
        return R.error(408,"账号已锁定，请联系管理员");
    }
    @ExceptionHandler(AuthenticationException.class)
    public R handleAuthenticationException(AuthenticationException e){
        logger.error(e.getMessage(), e);
        return R.error(402,"用户名或密码错误");
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public R HandleUserNameNotFoundException(UsernameNotFoundException e){
        logger.error(e.getMessage(), e);
        return R.error();
    }
}
