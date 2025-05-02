package com.entity.vo;

import com.entity.ZhongzhijihuaEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 种植计划
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author 
 * @email 
 * @date 2030-11-09 14:37:43
 */
public class ZhongzhijihuaVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 种植数量
	 */
	
	private Integer zhongzhishuliang;
		
	/**
	 * 种植时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date zhongzhishijian;
		
	/**
	 * 种植位置
	 */
	
	private String zhongzhiweizhi;
		
	/**
	 * 种植状态
	 */
	
	private String zhongzhizhuangtai;
		
	/**
	 * 种植详情
	 */
	
	private String zhongzhixiangqing;
		
	/**
	 * 主管账号
	 */
	
	private String zhuguanzhanghao;
		
	/**
	 * 主管姓名
	 */
	
	private String zhuguanxingming;
		
	/**
	 * 收藏数
	 */
	
	private Integer storeupnum;
				
	
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
	 * 设置：种植时间
	 */
	 
	public void setZhongzhishijian(Date zhongzhishijian) {
		this.zhongzhishijian = zhongzhishijian;
	}
	
	/**
	 * 获取：种植时间
	 */
	public Date getZhongzhishijian() {
		return zhongzhishijian;
	}
				
	
	/**
	 * 设置：种植位置
	 */
	 
	public void setZhongzhiweizhi(String zhongzhiweizhi) {
		this.zhongzhiweizhi = zhongzhiweizhi;
	}
	
	/**
	 * 获取：种植位置
	 */
	public String getZhongzhiweizhi() {
		return zhongzhiweizhi;
	}
				
	
	/**
	 * 设置：种植状态
	 */
	 
	public void setZhongzhizhuangtai(String zhongzhizhuangtai) {
		this.zhongzhizhuangtai = zhongzhizhuangtai;
	}
	
	/**
	 * 获取：种植状态
	 */
	public String getZhongzhizhuangtai() {
		return zhongzhizhuangtai;
	}
				
	
	/**
	 * 设置：种植详情
	 */
	 
	public void setZhongzhixiangqing(String zhongzhixiangqing) {
		this.zhongzhixiangqing = zhongzhixiangqing;
	}
	
	/**
	 * 获取：种植详情
	 */
	public String getZhongzhixiangqing() {
		return zhongzhixiangqing;
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
	 * 设置：收藏数
	 */
	 
	public void setStoreupnum(Integer storeupnum) {
		this.storeupnum = storeupnum;
	}
	
	/**
	 * 获取：收藏数
	 */
	public Integer getStoreupnum() {
		return storeupnum;
	}
			
}
