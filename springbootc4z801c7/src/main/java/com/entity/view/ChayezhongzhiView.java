package com.entity.view;

import com.entity.ChayezhongzhiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
 

/**
 * 茶叶种植
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2030-11-09 14:37:43
 */
@TableName("chayezhongzhi")
public class ChayezhongzhiView  extends ChayezhongzhiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public ChayezhongzhiView(){
	}
 
 	public ChayezhongzhiView(ChayezhongzhiEntity chayezhongzhiEntity){
 	try {
			BeanUtils.copyProperties(this, chayezhongzhiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}

}
