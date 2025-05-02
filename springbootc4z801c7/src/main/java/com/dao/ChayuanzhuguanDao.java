package com.dao;

import com.entity.ChayuanzhuguanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ChayuanzhuguanVO;
import com.entity.view.ChayuanzhuguanView;


/**
 * 茶园主管
 * 
 * @author 
 * @email 
 * @date 2030-11-09 14:37:42
 */
public interface ChayuanzhuguanDao extends BaseMapper<ChayuanzhuguanEntity> {
	
	List<ChayuanzhuguanVO> selectListVO(@Param("ew") Wrapper<ChayuanzhuguanEntity> wrapper);
	
	ChayuanzhuguanVO selectVO(@Param("ew") Wrapper<ChayuanzhuguanEntity> wrapper);
	
	List<ChayuanzhuguanView> selectListView(@Param("ew") Wrapper<ChayuanzhuguanEntity> wrapper);

	List<ChayuanzhuguanView> selectListView(Pagination page,@Param("ew") Wrapper<ChayuanzhuguanEntity> wrapper);
	
	ChayuanzhuguanView selectView(@Param("ew") Wrapper<ChayuanzhuguanEntity> wrapper);
	

}
