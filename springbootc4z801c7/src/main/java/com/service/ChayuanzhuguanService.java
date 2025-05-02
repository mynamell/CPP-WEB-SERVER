package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ChayuanzhuguanEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ChayuanzhuguanVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ChayuanzhuguanView;


/**
 * 茶园主管
 *
 * @author 
 * @email 
 * @date 2030-11-09 14:37:42
 */
public interface ChayuanzhuguanService extends IService<ChayuanzhuguanEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ChayuanzhuguanVO> selectListVO(Wrapper<ChayuanzhuguanEntity> wrapper);
   	
   	ChayuanzhuguanVO selectVO(@Param("ew") Wrapper<ChayuanzhuguanEntity> wrapper);
   	
   	List<ChayuanzhuguanView> selectListView(Wrapper<ChayuanzhuguanEntity> wrapper);
   	
   	ChayuanzhuguanView selectView(@Param("ew") Wrapper<ChayuanzhuguanEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ChayuanzhuguanEntity> wrapper);
   	

}

