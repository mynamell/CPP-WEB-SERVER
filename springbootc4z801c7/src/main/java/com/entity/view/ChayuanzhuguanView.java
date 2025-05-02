package com.entity.view;

import com.entity.ChayuanzhuguanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
 

/**
 * 茶园主管
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2030-11-09 14:37:42
 */
@TableName("chayuanzhuguan")
public class ChayuanzhuguanView  extends ChayuanzhuguanEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public ChayuanzhuguanView(){
	}
 
 	public ChayuanzhuguanView(ChayuanzhuguanEntity chayuanzhuguanEntity){
 	try {
			BeanUtils.copyProperties(this, chayuanzhuguanEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}

}
