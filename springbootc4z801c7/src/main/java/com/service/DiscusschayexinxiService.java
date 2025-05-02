package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscusschayexinxiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscusschayexinxiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscusschayexinxiView;


/**
 * 茶叶信息评论表
 *
 * @author 
 * @email 
 * @date 2030-11-09 14:37:45
 */
public interface DiscusschayexinxiService extends IService<DiscusschayexinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscusschayexinxiVO> selectListVO(Wrapper<DiscusschayexinxiEntity> wrapper);
   	
   	DiscusschayexinxiVO selectVO(@Param("ew") Wrapper<DiscusschayexinxiEntity> wrapper);
   	
   	List<DiscusschayexinxiView> selectListView(Wrapper<DiscusschayexinxiEntity> wrapper);
   	
   	DiscusschayexinxiView selectView(@Param("ew") Wrapper<DiscusschayexinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscusschayexinxiEntity> wrapper);
   	

}

