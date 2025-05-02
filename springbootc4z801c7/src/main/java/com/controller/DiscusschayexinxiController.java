package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.DiscusschayexinxiEntity;
import com.entity.view.DiscusschayexinxiView;

import com.service.DiscusschayexinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 茶叶信息评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2030-11-09 14:37:45
 */
@RestController
@RequestMapping("/discusschayexinxi")
public class DiscusschayexinxiController {
    @Autowired
    private DiscusschayexinxiService discusschayexinxiService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscusschayexinxiEntity discusschayexinxi,
		HttpServletRequest request){
        EntityWrapper<DiscusschayexinxiEntity> ew = new EntityWrapper<DiscusschayexinxiEntity>();

		PageUtils page = discusschayexinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusschayexinxi), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DiscusschayexinxiEntity discusschayexinxi, 
		HttpServletRequest request){
        EntityWrapper<DiscusschayexinxiEntity> ew = new EntityWrapper<DiscusschayexinxiEntity>();

		PageUtils page = discusschayexinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusschayexinxi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscusschayexinxiEntity discusschayexinxi){
       	EntityWrapper<DiscusschayexinxiEntity> ew = new EntityWrapper<DiscusschayexinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discusschayexinxi, "discusschayexinxi")); 
        return R.ok().put("data", discusschayexinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscusschayexinxiEntity discusschayexinxi){
        EntityWrapper< DiscusschayexinxiEntity> ew = new EntityWrapper< DiscusschayexinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discusschayexinxi, "discusschayexinxi")); 
		DiscusschayexinxiView discusschayexinxiView =  discusschayexinxiService.selectView(ew);
		return R.ok("查询茶叶信息评论表成功").put("data", discusschayexinxiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscusschayexinxiEntity discusschayexinxi = discusschayexinxiService.selectById(id);
        return R.ok().put("data", discusschayexinxi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscusschayexinxiEntity discusschayexinxi = discusschayexinxiService.selectById(id);
        return R.ok().put("data", discusschayexinxi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DiscusschayexinxiEntity discusschayexinxi, HttpServletRequest request){
    	discusschayexinxi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(discusschayexinxi);
        discusschayexinxiService.insert(discusschayexinxi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DiscusschayexinxiEntity discusschayexinxi, HttpServletRequest request){
    	discusschayexinxi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(discusschayexinxi);
        discusschayexinxiService.insert(discusschayexinxi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody DiscusschayexinxiEntity discusschayexinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discusschayexinxi);
        discusschayexinxiService.updateById(discusschayexinxi);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        discusschayexinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	










}
