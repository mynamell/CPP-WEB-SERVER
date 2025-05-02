package com.dao;

import com.entity.CaizhaixinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.CaizhaixinxiVO;
import com.entity.view.CaizhaixinxiView;


/**
 * 采摘信息
 * 
 * @author 
 * @email 
 * @date 2030-11-09 14:37:44
 */
public interface CaizhaixinxiDao extends BaseMapper<CaizhaixinxiEntity> {
	
	List<CaizhaixinxiVO> selectListVO(@Param("ew") Wrapper<CaizhaixinxiEntity> wrapper);
	
	CaizhaixinxiVO selectVO(@Param("ew") Wrapper<CaizhaixinxiEntity> wrapper);
	
	List<CaizhaixinxiView> selectListView(@Param("ew") Wrapper<CaizhaixinxiEntity> wrapper);

	List<CaizhaixinxiView> selectListView(Pagination page,@Param("ew") Wrapper<CaizhaixinxiEntity> wrapper);
	
	CaizhaixinxiView selectView(@Param("ew") Wrapper<CaizhaixinxiEntity> wrapper);
	

}
