package com.dao;

import com.entity.ChayezhongzhiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ChayezhongzhiVO;
import com.entity.view.ChayezhongzhiView;


/**
 * 茶叶种植
 * 
 * @author 
 * @email 
 * @date 2030-11-09 14:37:43
 */
public interface ChayezhongzhiDao extends BaseMapper<ChayezhongzhiEntity> {
	
	List<ChayezhongzhiVO> selectListVO(@Param("ew") Wrapper<ChayezhongzhiEntity> wrapper);
	
	ChayezhongzhiVO selectVO(@Param("ew") Wrapper<ChayezhongzhiEntity> wrapper);
	
	List<ChayezhongzhiView> selectListView(@Param("ew") Wrapper<ChayezhongzhiEntity> wrapper);

	List<ChayezhongzhiView> selectListView(Pagination page,@Param("ew") Wrapper<ChayezhongzhiEntity> wrapper);
	
	ChayezhongzhiView selectView(@Param("ew") Wrapper<ChayezhongzhiEntity> wrapper);
	

}
