package com.entity.view;

import com.entity.DiscusschayexinxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
 

/**
 * 茶叶信息评论表
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2030-11-09 14:37:45
 */
@TableName("discusschayexinxi")
public class DiscusschayexinxiView  extends DiscusschayexinxiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public DiscusschayexinxiView(){
	}
 
 	public DiscusschayexinxiView(DiscusschayexinxiEntity discusschayexinxiEntity){
 	try {
			BeanUtils.copyProperties(this, discusschayexinxiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}

}
