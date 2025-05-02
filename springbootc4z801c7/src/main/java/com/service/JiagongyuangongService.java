package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.JiagongyuangongEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.JiagongyuangongVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.JiagongyuangongView;


/**
 * 加工员工
 *
 * @author 
 * @email 
 * @date 2030-11-09 14:37:42
 */
public interface JiagongyuangongService extends IService<JiagongyuangongEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JiagongyuangongVO> selectListVO(Wrapper<JiagongyuangongEntity> wrapper);
   	
   	JiagongyuangongVO selectVO(@Param("ew") Wrapper<JiagongyuangongEntity> wrapper);
   	
   	List<JiagongyuangongView> selectListView(Wrapper<JiagongyuangongEntity> wrapper);
   	
   	JiagongyuangongView selectView(@Param("ew") Wrapper<JiagongyuangongEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<JiagongyuangongEntity> wrapper);
   	

}

