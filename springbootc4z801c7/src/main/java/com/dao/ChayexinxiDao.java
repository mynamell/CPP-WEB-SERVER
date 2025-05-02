package com.dao;

import com.entity.ChayexinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ChayexinxiVO;
import com.entity.view.ChayexinxiView;


/**
 * 茶叶信息
 * 
 * @author 
 * @email 
 * @date 2030-11-09 14:37:44
 */
public interface ChayexinxiDao extends BaseMapper<ChayexinxiEntity> {
	
	List<ChayexinxiVO> selectListVO(@Param("ew") Wrapper<ChayexinxiEntity> wrapper);
	
	ChayexinxiVO selectVO(@Param("ew") Wrapper<ChayexinxiEntity> wrapper);
	
	List<ChayexinxiView> selectListView(@Param("ew") Wrapper<ChayexinxiEntity> wrapper);

	List<ChayexinxiView> selectListView(Pagination page,@Param("ew") Wrapper<ChayexinxiEntity> wrapper);
	
	ChayexinxiView selectView(@Param("ew") Wrapper<ChayexinxiEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<ChayexinxiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<ChayexinxiEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<ChayexinxiEntity> wrapper);



}
