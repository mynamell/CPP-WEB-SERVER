package com.dao;

import com.entity.JiagongyuangongEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.JiagongyuangongVO;
import com.entity.view.JiagongyuangongView;


/**
 * 加工员工
 * 
 * @author 
 * @email 
 * @date 2030-11-09 14:37:42
 */
public interface JiagongyuangongDao extends BaseMapper<JiagongyuangongEntity> {
	
	List<JiagongyuangongVO> selectListVO(@Param("ew") Wrapper<JiagongyuangongEntity> wrapper);
	
	JiagongyuangongVO selectVO(@Param("ew") Wrapper<JiagongyuangongEntity> wrapper);
	
	List<JiagongyuangongView> selectListView(@Param("ew") Wrapper<JiagongyuangongEntity> wrapper);

	List<JiagongyuangongView> selectListView(Pagination page,@Param("ew") Wrapper<JiagongyuangongEntity> wrapper);
	
	JiagongyuangongView selectView(@Param("ew") Wrapper<JiagongyuangongEntity> wrapper);
	

}
