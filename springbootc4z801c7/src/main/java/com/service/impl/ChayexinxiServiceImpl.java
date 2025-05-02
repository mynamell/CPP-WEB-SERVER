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


import com.dao.ChayexinxiDao;
import com.entity.ChayexinxiEntity;
import com.service.ChayexinxiService;
import com.entity.vo.ChayexinxiVO;
import com.entity.view.ChayexinxiView;

@Service("chayexinxiService")
public class ChayexinxiServiceImpl extends ServiceImpl<ChayexinxiDao, ChayexinxiEntity> implements ChayexinxiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ChayexinxiEntity> page = this.selectPage(
                new Query<ChayexinxiEntity>(params).getPage(),
                new EntityWrapper<ChayexinxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ChayexinxiEntity> wrapper) {
		  Page<ChayexinxiView> page =new Query<ChayexinxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<ChayexinxiVO> selectListVO(Wrapper<ChayexinxiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ChayexinxiVO selectVO(Wrapper<ChayexinxiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ChayexinxiView> selectListView(Wrapper<ChayexinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ChayexinxiView selectView(Wrapper<ChayexinxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<ChayexinxiEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<ChayexinxiEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<ChayexinxiEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }




}
