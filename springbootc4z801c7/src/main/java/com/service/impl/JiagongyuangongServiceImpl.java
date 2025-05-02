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


import com.dao.JiagongyuangongDao;
import com.entity.JiagongyuangongEntity;
import com.service.JiagongyuangongService;
import com.entity.vo.JiagongyuangongVO;
import com.entity.view.JiagongyuangongView;

@Service("jiagongyuangongService")
public class JiagongyuangongServiceImpl extends ServiceImpl<JiagongyuangongDao, JiagongyuangongEntity> implements JiagongyuangongService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JiagongyuangongEntity> page = this.selectPage(
                new Query<JiagongyuangongEntity>(params).getPage(),
                new EntityWrapper<JiagongyuangongEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<JiagongyuangongEntity> wrapper) {
		  Page<JiagongyuangongView> page =new Query<JiagongyuangongView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<JiagongyuangongVO> selectListVO(Wrapper<JiagongyuangongEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public JiagongyuangongVO selectVO(Wrapper<JiagongyuangongEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<JiagongyuangongView> selectListView(Wrapper<JiagongyuangongEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public JiagongyuangongView selectView(Wrapper<JiagongyuangongEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
