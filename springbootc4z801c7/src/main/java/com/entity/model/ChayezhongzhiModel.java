package com.entity.model;

import com.entity.ChayezhongzhiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 茶叶种植
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2030-11-09 14:37:43
 */
public class ChayezhongzhiModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 种植数量
	 */
	
	private Integer zhongzhishuliang;
		
	/**
	 * 种植日期
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
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
