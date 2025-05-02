package com.dao;

import com.entity.ZhongzhijihuaEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ZhongzhijihuaVO;
import com.entity.view.ZhongzhijihuaView;


/**
 * 种植计划
 * 
 * @author 
 * @email 
 * @date 2030-11-09 14:37:43
 */
public interface ZhongzhijihuaDao extends BaseMapper<ZhongzhijihuaEntity> {
	
	List<ZhongzhijihuaVO> selectListVO(@Param("ew") Wrapper<ZhongzhijihuaEntity> wrapper);
	
	ZhongzhijihuaVO selectVO(@Param("ew") Wrapper<ZhongzhijihuaEntity> wrapper);
	
	List<ZhongzhijihuaView> selectListView(@Param("ew") Wrapper<ZhongzhijihuaEntity> wrapper);

	List<ZhongzhijihuaView> selectListView(Pagination page,@Param("ew") Wrapper<ZhongzhijihuaEntity> wrapper);
	
	ZhongzhijihuaView selectView(@Param("ew") Wrapper<ZhongzhijihuaEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<ZhongzhijihuaEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<ZhongzhijihuaEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<ZhongzhijihuaEntity> wrapper);



}
