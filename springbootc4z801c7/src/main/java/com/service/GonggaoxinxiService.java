package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.GonggaoxinxiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.GonggaoxinxiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.GonggaoxinxiView;


/**
 * 公告信息
 *
 * @author 
 * @email 
 * @date 2030-11-09 14:37:42
 */
public interface GonggaoxinxiService extends IService<GonggaoxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<GonggaoxinxiVO> selectListVO(Wrapper<GonggaoxinxiEntity> wrapper);
   	
   	GonggaoxinxiVO selectVO(@Param("ew") Wrapper<GonggaoxinxiEntity> wrapper);
   	
   	List<GonggaoxinxiView> selectListView(Wrapper<GonggaoxinxiEntity> wrapper);
   	
   	GonggaoxinxiView selectView(@Param("ew") Wrapper<GonggaoxinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<GonggaoxinxiEntity> wrapper);
   	

}

