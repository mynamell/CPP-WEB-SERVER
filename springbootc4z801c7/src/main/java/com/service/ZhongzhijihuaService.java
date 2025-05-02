package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ZhongzhijihuaEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ZhongzhijihuaVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ZhongzhijihuaView;


/**
 * 种植计划
 *
 * @author 
 * @email 
 * @date 2030-11-09 14:37:43
 */
public interface ZhongzhijihuaService extends IService<ZhongzhijihuaEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ZhongzhijihuaVO> selectListVO(Wrapper<ZhongzhijihuaEntity> wrapper);
   	
   	ZhongzhijihuaVO selectVO(@Param("ew") Wrapper<ZhongzhijihuaEntity> wrapper);
   	
   	List<ZhongzhijihuaView> selectListView(Wrapper<ZhongzhijihuaEntity> wrapper);
   	
   	ZhongzhijihuaView selectView(@Param("ew") Wrapper<ZhongzhijihuaEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ZhongzhijihuaEntity> wrapper);
   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<ZhongzhijihuaEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<ZhongzhijihuaEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<ZhongzhijihuaEntity> wrapper);



}

