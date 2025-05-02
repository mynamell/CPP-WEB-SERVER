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


import com.dao.ZhongzhijihuaDao;
import com.entity.ZhongzhijihuaEntity;
import com.service.ZhongzhijihuaService;
import com.entity.vo.ZhongzhijihuaVO;
import com.entity.view.ZhongzhijihuaView;

@Service("zhongzhijihuaService")
public class ZhongzhijihuaServiceImpl extends ServiceImpl<ZhongzhijihuaDao, ZhongzhijihuaEntity> implements ZhongzhijihuaService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZhongzhijihuaEntity> page = this.selectPage(
                new Query<ZhongzhijihuaEntity>(params).getPage(),
                new EntityWrapper<ZhongzhijihuaEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ZhongzhijihuaEntity> wrapper) {
		  Page<ZhongzhijihuaView> page =new Query<ZhongzhijihuaView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<ZhongzhijihuaVO> selectListVO(Wrapper<ZhongzhijihuaEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ZhongzhijihuaVO selectVO(Wrapper<ZhongzhijihuaEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ZhongzhijihuaView> selectListView(Wrapper<ZhongzhijihuaEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ZhongzhijihuaView selectView(Wrapper<ZhongzhijihuaEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<ZhongzhijihuaEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<ZhongzhijihuaEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<ZhongzhijihuaEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }




}
