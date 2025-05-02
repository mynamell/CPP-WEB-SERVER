package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.CaizhaixinxiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.CaizhaixinxiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.CaizhaixinxiView;


/**
 * 采摘信息
 *
 * @author 
 * @email 
 * @date 2030-11-09 14:37:44
 */
public interface CaizhaixinxiService extends IService<CaizhaixinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<CaizhaixinxiVO> selectListVO(Wrapper<CaizhaixinxiEntity> wrapper);
   	
   	CaizhaixinxiVO selectVO(@Param("ew") Wrapper<CaizhaixinxiEntity> wrapper);
   	
   	List<CaizhaixinxiView> selectListView(Wrapper<CaizhaixinxiEntity> wrapper);
   	
   	CaizhaixinxiView selectView(@Param("ew") Wrapper<CaizhaixinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<CaizhaixinxiEntity> wrapper);
   	

}

