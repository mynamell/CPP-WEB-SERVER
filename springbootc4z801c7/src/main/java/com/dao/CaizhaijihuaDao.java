package com.dao;

import com.entity.CaizhaijihuaEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.CaizhaijihuaVO;
import com.entity.view.CaizhaijihuaView;


/**
 * 采摘计划
 * 
 * @author 
 * @email 
 * @date 2030-11-09 14:37:43
 */
public interface CaizhaijihuaDao extends BaseMapper<CaizhaijihuaEntity> {
	
	List<CaizhaijihuaVO> selectListVO(@Param("ew") Wrapper<CaizhaijihuaEntity> wrapper);
	
	CaizhaijihuaVO selectVO(@Param("ew") Wrapper<CaizhaijihuaEntity> wrapper);
	
	List<CaizhaijihuaView> selectListView(@Param("ew") Wrapper<CaizhaijihuaEntity> wrapper);

	List<CaizhaijihuaView> selectListView(Pagination page,@Param("ew") Wrapper<CaizhaijihuaEntity> wrapper);
	
	CaizhaijihuaView selectView(@Param("ew") Wrapper<CaizhaijihuaEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<CaizhaijihuaEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<CaizhaijihuaEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<CaizhaijihuaEntity> wrapper);



}
