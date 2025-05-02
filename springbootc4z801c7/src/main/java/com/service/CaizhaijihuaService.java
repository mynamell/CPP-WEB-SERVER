package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.CaizhaijihuaEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.CaizhaijihuaVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.CaizhaijihuaView;


/**
 * 采摘计划
 *
 * @author 
 * @email 
 * @date 2030-11-09 14:37:43
 */
public interface CaizhaijihuaService extends IService<CaizhaijihuaEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<CaizhaijihuaVO> selectListVO(Wrapper<CaizhaijihuaEntity> wrapper);
   	
   	CaizhaijihuaVO selectVO(@Param("ew") Wrapper<CaizhaijihuaEntity> wrapper);
   	
   	List<CaizhaijihuaView> selectListView(Wrapper<CaizhaijihuaEntity> wrapper);
   	
   	CaizhaijihuaView selectView(@Param("ew") Wrapper<CaizhaijihuaEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<CaizhaijihuaEntity> wrapper);
   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<CaizhaijihuaEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<CaizhaijihuaEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<CaizhaijihuaEntity> wrapper);



}

