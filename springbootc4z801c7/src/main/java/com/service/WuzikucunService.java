package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.WuzikucunEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.WuzikucunVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.WuzikucunView;


/**
 * 物资库存
 *
 * @author 
 * @email 
 * @date 2030-11-09 14:37:43
 */
public interface WuzikucunService extends IService<WuzikucunEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<WuzikucunVO> selectListVO(Wrapper<WuzikucunEntity> wrapper);
   	
   	WuzikucunVO selectVO(@Param("ew") Wrapper<WuzikucunEntity> wrapper);
   	
   	List<WuzikucunView> selectListView(Wrapper<WuzikucunEntity> wrapper);
   	
   	WuzikucunView selectView(@Param("ew") Wrapper<WuzikucunEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<WuzikucunEntity> wrapper);
   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<WuzikucunEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<WuzikucunEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<WuzikucunEntity> wrapper);



}

