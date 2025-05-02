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


import com.dao.ChayezhongzhiDao;
import com.entity.ChayezhongzhiEntity;
import com.service.ChayezhongzhiService;
import com.entity.vo.ChayezhongzhiVO;
import com.entity.view.ChayezhongzhiView;

@Service("chayezhongzhiService")
public class ChayezhongzhiServiceImpl extends ServiceImpl<ChayezhongzhiDao, ChayezhongzhiEntity> implements ChayezhongzhiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ChayezhongzhiEntity> page = this.selectPage(
                new Query<ChayezhongzhiEntity>(params).getPage(),
                new EntityWrapper<ChayezhongzhiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ChayezhongzhiEntity> wrapper) {
		  Page<ChayezhongzhiView> page =new Query<ChayezhongzhiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<ChayezhongzhiVO> selectListVO(Wrapper<ChayezhongzhiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ChayezhongzhiVO selectVO(Wrapper<ChayezhongzhiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ChayezhongzhiView> selectListView(Wrapper<ChayezhongzhiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ChayezhongzhiView selectView(Wrapper<ChayezhongzhiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
