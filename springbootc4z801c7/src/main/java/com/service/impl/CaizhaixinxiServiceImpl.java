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


import com.dao.CaizhaixinxiDao;
import com.entity.CaizhaixinxiEntity;
import com.service.CaizhaixinxiService;
import com.entity.vo.CaizhaixinxiVO;
import com.entity.view.CaizhaixinxiView;

@Service("caizhaixinxiService")
public class CaizhaixinxiServiceImpl extends ServiceImpl<CaizhaixinxiDao, CaizhaixinxiEntity> implements CaizhaixinxiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CaizhaixinxiEntity> page = this.selectPage(
                new Query<CaizhaixinxiEntity>(params).getPage(),
                new EntityWrapper<CaizhaixinxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<CaizhaixinxiEntity> wrapper) {
		  Page<CaizhaixinxiView> page =new Query<CaizhaixinxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<CaizhaixinxiVO> selectListVO(Wrapper<CaizhaixinxiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public CaizhaixinxiVO selectVO(Wrapper<CaizhaixinxiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<CaizhaixinxiView> selectListView(Wrapper<CaizhaixinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public CaizhaixinxiView selectView(Wrapper<CaizhaixinxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
