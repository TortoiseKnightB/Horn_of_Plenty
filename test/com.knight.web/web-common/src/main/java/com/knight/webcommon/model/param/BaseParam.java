package com.knight.webcommon.model.param;

import com.knight.webcommon.aspect.annotation.NonBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yang.liu22@outlook.com
 * @date 2021/12/09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseParam {

//    @JSONField(name = "")
//    @JsonProperty(value = "CustomerNo")
//    @NotBlank()
    @NonBlank
    private String id;
}
