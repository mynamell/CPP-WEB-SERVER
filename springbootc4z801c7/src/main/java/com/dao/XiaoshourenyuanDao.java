package com.dao;

import com.entity.XiaoshourenyuanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.XiaoshourenyuanVO;
import com.entity.view.XiaoshourenyuanView;


/**
 * 销售人员
 * 
 * @author 
 * @email 
 * @date 2030-11-09 14:37:42
 */
public interface XiaoshourenyuanDao extends BaseMapper<XiaoshourenyuanEntity> {
	
	List<XiaoshourenyuanVO> selectListVO(@Param("ew") Wrapper<XiaoshourenyuanEntity> wrapper);
	
	XiaoshourenyuanVO selectVO(@Param("ew") Wrapper<XiaoshourenyuanEntity> wrapper);
	
	List<XiaoshourenyuanView> selectListView(@Param("ew") Wrapper<XiaoshourenyuanEntity> wrapper);

	List<XiaoshourenyuanView> selectListView(Pagination page,@Param("ew") Wrapper<XiaoshourenyuanEntity> wrapper);
	
	XiaoshourenyuanView selectView(@Param("ew") Wrapper<XiaoshourenyuanEntity> wrapper);
	

}
