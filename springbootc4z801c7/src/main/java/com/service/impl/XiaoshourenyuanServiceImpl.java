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


import com.dao.XiaoshourenyuanDao;
import com.entity.XiaoshourenyuanEntity;
import com.service.XiaoshourenyuanService;
import com.entity.vo.XiaoshourenyuanVO;
import com.entity.view.XiaoshourenyuanView;

@Service("xiaoshourenyuanService")
public class XiaoshourenyuanServiceImpl extends ServiceImpl<XiaoshourenyuanDao, XiaoshourenyuanEntity> implements XiaoshourenyuanService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<XiaoshourenyuanEntity> page = this.selectPage(
                new Query<XiaoshourenyuanEntity>(params).getPage(),
                new EntityWrapper<XiaoshourenyuanEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<XiaoshourenyuanEntity> wrapper) {
		  Page<XiaoshourenyuanView> page =new Query<XiaoshourenyuanView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<XiaoshourenyuanVO> selectListVO(Wrapper<XiaoshourenyuanEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public XiaoshourenyuanVO selectVO(Wrapper<XiaoshourenyuanEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<XiaoshourenyuanView> selectListView(Wrapper<XiaoshourenyuanEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public XiaoshourenyuanView selectView(Wrapper<XiaoshourenyuanEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
