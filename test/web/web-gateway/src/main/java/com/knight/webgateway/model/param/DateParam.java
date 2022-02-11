package com.knight.webgateway.model.param;

import cn.hutool.core.date.DateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 时间格式测试参数
 * <p>
 * 尽量不要使用 Date、DateTime，时区问题很乱，使用 jave.time 中的 LocalDate、LocalDateTime 类替代
 * <p>
 * 注解 @JsonFormat 确定接收参数的时间格式
 *
 * @author TortoiseKnightB
 * @date 2022/02/11
 */
@Data
public class DateParam {

    @ApiModelProperty(value = "Date")
    @JsonProperty("Date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date;

    @ApiModelProperty(value = "DateTime")
    @JsonProperty("DateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private DateTime dateTime;

    @ApiModelProperty(value = "LocalDate")
    @JsonProperty("LocalDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate localDate;

    @ApiModelProperty(value = "LocalDateTime")
    @JsonProperty("LocalDateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime localDateTime;
}
