package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.ChayuanzhuguanDao;
import com.entity.ChayuanzhuguanEntity;
import com.service.ChayuanzhuguanService;
import com.entity.vo.ChayuanzhuguanVO;
import com.entity.view.ChayuanzhuguanView;

@Service("chayuanzhuguanService")
public class ChayuanzhuguanServiceImpl extends ServiceImpl<ChayuanzhuguanDao, ChayuanzhuguanEntity> implements ChayuanzhuguanService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ChayuanzhuguanEntity> page = this.selectPage(
                new Query<ChayuanzhuguanEntity>(params).getPage(),
                new EntityWrapper<ChayuanzhuguanEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ChayuanzhuguanEntity> wrapper) {
		  Page<ChayuanzhuguanView> page =new Query<ChayuanzhuguanView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<ChayuanzhuguanVO> selectListVO(Wrapper<ChayuanzhuguanEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ChayuanzhuguanVO selectVO(Wrapper<ChayuanzhuguanEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ChayuanzhuguanView> selectListView(Wrapper<ChayuanzhuguanEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ChayuanzhuguanView selectView(Wrapper<ChayuanzhuguanEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
