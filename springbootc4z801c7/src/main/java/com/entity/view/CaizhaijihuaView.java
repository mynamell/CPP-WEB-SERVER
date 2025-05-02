package com.entity.view;

import com.entity.CaizhaijihuaEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
 

/**
 * 采摘计划
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2030-11-09 14:37:43
 */
@TableName("caizhaijihua")
public class CaizhaijihuaView  extends CaizhaijihuaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public CaizhaijihuaView(){
	}
 
 	public CaizhaijihuaView(CaizhaijihuaEntity caizhaijihuaEntity){
 	try {
			BeanUtils.copyProperties(this, caizhaijihuaEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}

}
