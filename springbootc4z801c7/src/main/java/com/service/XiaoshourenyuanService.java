package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.XiaoshourenyuanEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.XiaoshourenyuanVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.XiaoshourenyuanView;


/**
 * 销售人员
 *
 * @author 
 * @email 
 * @date 2030-11-09 14:37:42
 */
public interface XiaoshourenyuanService extends IService<XiaoshourenyuanEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<XiaoshourenyuanVO> selectListVO(Wrapper<XiaoshourenyuanEntity> wrapper);
   	
   	XiaoshourenyuanVO selectVO(@Param("ew") Wrapper<XiaoshourenyuanEntity> wrapper);
   	
   	List<XiaoshourenyuanView> selectListView(Wrapper<XiaoshourenyuanEntity> wrapper);
   	
   	XiaoshourenyuanView selectView(@Param("ew") Wrapper<XiaoshourenyuanEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<XiaoshourenyuanEntity> wrapper);
   	

}

