package cn.izoro.convert;

import cn.izoro.model.entity.SystemUserDO;
import cn.izoro.model.vo.login.UserRespVO;
import cn.izoro.model.vo.login.RegisterReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 系统用户 Convert
 * @Author zoro
 */
@Mapper
public interface SystemUserConvert {
    SystemUserConvert INSTANCE = Mappers.getMapper(SystemUserConvert.class);
    UserRespVO toRespVO(SystemUserDO systemUserDO);

    SystemUserDO toDO(RegisterReqVO authLoginReqVO);
}
