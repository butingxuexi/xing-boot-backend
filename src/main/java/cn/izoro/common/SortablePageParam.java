package cn.izoro.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 可排序的分页参数
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SortablePageParam extends PageParam {
    /**
     * 排序字段
     */
    private List<SortingField> sortingFields;

}