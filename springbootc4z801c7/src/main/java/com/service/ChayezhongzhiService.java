package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ChayezhongzhiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ChayezhongzhiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ChayezhongzhiView;


/**
 * 茶叶种植
 *
 * @author 
 * @email 
 * @date 2030-11-09 14:37:43
 */
public interface ChayezhongzhiService extends IService<ChayezhongzhiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ChayezhongzhiVO> selectListVO(Wrapper<ChayezhongzhiEntity> wrapper);
   	
   	ChayezhongzhiVO selectVO(@Param("ew") Wrapper<ChayezhongzhiEntity> wrapper);
   	
   	List<ChayezhongzhiView> selectListView(Wrapper<ChayezhongzhiEntity> wrapper);
   	
   	ChayezhongzhiView selectView(@Param("ew") Wrapper<ChayezhongzhiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ChayezhongzhiEntity> wrapper);
   	

}

