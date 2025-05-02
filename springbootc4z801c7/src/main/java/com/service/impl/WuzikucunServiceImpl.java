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


import com.dao.WuzikucunDao;
import com.entity.WuzikucunEntity;
import com.service.WuzikucunService;
import com.entity.vo.WuzikucunVO;
import com.entity.view.WuzikucunView;

@Service("wuzikucunService")
public class WuzikucunServiceImpl extends ServiceImpl<WuzikucunDao, WuzikucunEntity> implements WuzikucunService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<WuzikucunEntity> page = this.selectPage(
                new Query<WuzikucunEntity>(params).getPage(),
                new EntityWrapper<WuzikucunEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<WuzikucunEntity> wrapper) {
		  Page<WuzikucunView> page =new Query<WuzikucunView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<WuzikucunVO> selectListVO(Wrapper<WuzikucunEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public WuzikucunVO selectVO(Wrapper<WuzikucunEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<WuzikucunView> selectListView(Wrapper<WuzikucunEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public WuzikucunView selectView(Wrapper<WuzikucunEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<WuzikucunEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<WuzikucunEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<WuzikucunEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }




}
