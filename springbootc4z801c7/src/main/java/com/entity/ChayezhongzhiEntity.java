package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 茶叶种植
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2030-11-09 14:37:43
 */
@TableName("chayezhongzhi")
public class ChayezhongzhiEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public ChayezhongzhiEntity() {
		
	}
	
	public ChayezhongzhiEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
	@TableId
	private Long id;
	/**
	 * 茶叶名称
	 */
					
	private String chayemingcheng;
	
	/**
	 * 种植数量
	 */
					
	private Integer zhongzhishuliang;
	
	/**
	 * 种植日期
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat 		
	private Date zhongzhiriqi;
	
	/**
	 * 种植说明
	 */
					
	private String zhongzhishuoming;
	
	/**
	 * 主管账号
	 */
					
	private String zhuguanzhanghao;
	
	/**
	 * 主管姓名
	 */
					
	private String zhuguanxingming;
	
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：茶叶名称
	 */
	public void setChayemingcheng(String chayemingcheng) {
		this.chayemingcheng = chayemingcheng;
	}
	/**
	 * 获取：茶叶名称
	 */
	public String getChayemingcheng() {
		return chayemingcheng;
	}
	/**
	 * 设置：种植数量
	 */
	public void setZhongzhishuliang(Integer zhongzhishuliang) {
		this.zhongzhishuliang = zhongzhishuliang;
	}
	/**
	 * 获取：种植数量
	 */
	public Integer getZhongzhishuliang() {
		return zhongzhishuliang;
	}
	/**
	 * 设置：种植日期
	 */
	public void setZhongzhiriqi(Date zhongzhiriqi) {
		this.zhongzhiriqi = zhongzhiriqi;
	}
	/**
	 * 获取：种植日期
	 */
	public Date getZhongzhiriqi() {
		return zhongzhiriqi;
	}
	/**
	 * 设置：种植说明
	 */
	public void setZhongzhishuoming(String zhongzhishuoming) {
		this.zhongzhishuoming = zhongzhishuoming;
	}
	/**
	 * 获取：种植说明
	 */
	public String getZhongzhishuoming() {
		return zhongzhishuoming;
	}
	/**
	 * 设置：主管账号
	 */
	public void setZhuguanzhanghao(String zhuguanzhanghao) {
		this.zhuguanzhanghao = zhuguanzhanghao;
	}
	/**
	 * 获取：主管账号
	 */
	public String getZhuguanzhanghao() {
		return zhuguanzhanghao;
	}
	/**
	 * 设置：主管姓名
	 */
	public void setZhuguanxingming(String zhuguanxingming) {
		this.zhuguanxingming = zhuguanxingming;
	}
	/**
	 * 获取：主管姓名
	 */
	public String getZhuguanxingming() {
		return zhuguanxingming;
	}

}
