package com.entity.vo;

import com.entity.CaizhaijihuaEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 采摘计划
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author 
 * @email 
 * @date 2030-11-09 14:37:43
 */
public class CaizhaijihuaVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 采摘数量
	 */
	
	private Integer caizhaishuliang;
		
	/**
	 * 计划状态
	 */
	
	private String jihuazhuangtai;
		
	/**
	 * 采摘日期
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date caizhairiqi;
		
	/**
	 * 计划说明
	 */
	
	private String jihuashuoming;
		
	/**
	 * 主管账号
	 */
	
	private String zhuguanzhanghao;
		
	/**
	 * 主管姓名
	 */
	
	private String zhuguanxingming;
		
	/**
	 * 工号
	 */
	
	private String gonghao;
		
	/**
	 * 姓名
	 */
	
	private String xingming;
				
	
	/**
	 * 设置：采摘数量
	 */
	 
	public void setCaizhaishuliang(Integer caizhaishuliang) {
		this.caizhaishuliang = caizhaishuliang;
	}
	
	/**
	 * 获取：采摘数量
	 */
	public Integer getCaizhaishuliang() {
		return caizhaishuliang;
	}
				
	
	/**
	 * 设置：计划状态
	 */
	 
	public void setJihuazhuangtai(String jihuazhuangtai) {
		this.jihuazhuangtai = jihuazhuangtai;
	}
	
	/**
	 * 获取：计划状态
	 */
	public String getJihuazhuangtai() {
		return jihuazhuangtai;
	}
				
	
	/**
	 * 设置：采摘日期
	 */
	 
	public void setCaizhairiqi(Date caizhairiqi) {
		this.caizhairiqi = caizhairiqi;
	}
	
	/**
	 * 获取：采摘日期
	 */
	public Date getCaizhairiqi() {
		return caizhairiqi;
	}
				
	
	/**
	 * 设置：计划说明
	 */
	 
	public void setJihuashuoming(String jihuashuoming) {
		this.jihuashuoming = jihuashuoming;
	}
	
	/**
	 * 获取：计划说明
	 */
	public String getJihuashuoming() {
		return jihuashuoming;
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
				
	
	/**
	 * 设置：工号
	 */
	 
	public void setGonghao(String gonghao) {
		this.gonghao = gonghao;
	}
	
	/**
	 * 获取：工号
	 */
	public String getGonghao() {
		return gonghao;
	}
				
	
	/**
	 * 设置：姓名
	 */
	 
	public void setXingming(String xingming) {
		this.xingming = xingming;
	}
	
	/**
	 * 获取：姓名
	 */
	public String getXingming() {
		return xingming;
	}
			
}
