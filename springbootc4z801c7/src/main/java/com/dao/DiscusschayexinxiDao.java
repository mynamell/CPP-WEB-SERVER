package com.dao;

import com.entity.DiscusschayexinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscusschayexinxiVO;
import com.entity.view.DiscusschayexinxiView;


/**
 * 茶叶信息评论表
 * 
 * @author 
 * @email 
 * @date 2030-11-09 14:37:45
 */
public interface DiscusschayexinxiDao extends BaseMapper<DiscusschayexinxiEntity> {
	
	List<DiscusschayexinxiVO> selectListVO(@Param("ew") Wrapper<DiscusschayexinxiEntity> wrapper);
	
	DiscusschayexinxiVO selectVO(@Param("ew") Wrapper<DiscusschayexinxiEntity> wrapper);
	
	List<DiscusschayexinxiView> selectListView(@Param("ew") Wrapper<DiscusschayexinxiEntity> wrapper);

	List<DiscusschayexinxiView> selectListView(Pagination page,@Param("ew") Wrapper<DiscusschayexinxiEntity> wrapper);
	
	DiscusschayexinxiView selectView(@Param("ew") Wrapper<DiscusschayexinxiEntity> wrapper);
	

}
