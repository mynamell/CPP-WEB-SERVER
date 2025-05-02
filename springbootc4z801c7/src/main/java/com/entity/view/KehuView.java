package com.entity.view;

import com.entity.KehuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
 

/**
 * 客户
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2030-11-09 14:37:42
 */
@TableName("kehu")
public class KehuView  extends KehuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public KehuView(){
	}
 
 	public KehuView(KehuEntity kehuEntity){
 	try {
			BeanUtils.copyProperties(this, kehuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}

}
