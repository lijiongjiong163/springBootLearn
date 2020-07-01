package com.ljj.springboot.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @Builder 可以通过Builder来创建对象
 *
 * @JsonFormat 用于配置如何序列化属性值的详细信息的通用注释,效果取决于要注释的属性的数据类型,例如加在Date上就可以通过pattern来规定其格式
 * @JsonPropertyOrder({"creatTime","title"}) 在序列化的时候排序
 * @JsonProperty("作者")  在序列化或反序列化的时候，标签的value值可以代表被标签的成员变量，相当于起别名
 * @JsonInclude(JsonInclude.Include.NON_NULL) 序列化的时候把为null的数据屏蔽掉（可用在方法或变量）
 * @JsonIgnore 序列化时忽视这个变量，例如密码啥的，不想往前台传的
 *
 *ObjectMapper类  ：手动进行序列化和反序列化
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"creatTime","title"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Article {
    //@JsonIgnore
    private int id;  //用户id
    private String title;   //书名
    private String content; //目录
    @JsonProperty("作者")
    private String author;  //作者
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date createTime;            //日期
    private List<Reader> readers;   //读者
}
