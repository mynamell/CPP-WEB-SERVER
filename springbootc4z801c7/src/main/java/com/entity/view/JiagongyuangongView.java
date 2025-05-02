package com.entity.view;

import com.entity.JiagongyuangongEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
 

/**
 * 加工员工
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2030-11-09 14:37:42
 */
@TableName("jiagongyuangong")
public class JiagongyuangongView  extends JiagongyuangongEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public JiagongyuangongView(){
	}
 
 	public JiagongyuangongView(JiagongyuangongEntity jiagongyuangongEntity){
 	try {
			BeanUtils.copyProperties(this, jiagongyuangongEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}

}
