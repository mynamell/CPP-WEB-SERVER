package com.dao;

import com.entity.WuzikucunEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.WuzikucunVO;
import com.entity.view.WuzikucunView;


/**
 * 物资库存
 * 
 * @author 
 * @email 
 * @date 2030-11-09 14:37:43
 */
public interface WuzikucunDao extends BaseMapper<WuzikucunEntity> {
	
	List<WuzikucunVO> selectListVO(@Param("ew") Wrapper<WuzikucunEntity> wrapper);
	
	WuzikucunVO selectVO(@Param("ew") Wrapper<WuzikucunEntity> wrapper);
	
	List<WuzikucunView> selectListView(@Param("ew") Wrapper<WuzikucunEntity> wrapper);

	List<WuzikucunView> selectListView(Pagination page,@Param("ew") Wrapper<WuzikucunEntity> wrapper);
	
	WuzikucunView selectView(@Param("ew") Wrapper<WuzikucunEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<WuzikucunEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<WuzikucunEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<WuzikucunEntity> wrapper);



}
