package cn.izoro.convert;

import cn.izoro.model.VO.userRespVO;
import cn.izoro.model.VO.RegisterReqVO;
import cn.izoro.model.dataobject.SystemUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 系统用户 Convert
 * @Author zoro
 */
@Mapper
public interface SystemUserConvert {
    SystemUserConvert INSTANCE = Mappers.getMapper(SystemUserConvert.class);
    userRespVO toRespVO(SystemUser systemUser);

    SystemUser toDO(RegisterReqVO authLoginReqVO);
}
