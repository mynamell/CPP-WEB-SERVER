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

import com.entity.CaizhaixinxiEntity;
import com.entity.view.CaizhaixinxiView;

import com.service.CaizhaixinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 采摘信息
 * 后端接口
 * @author 
 * @email 
 * @date 2030-11-09 14:37:44
 */
@RestController
@RequestMapping("/caizhaixinxi")
public class CaizhaixinxiController {
    @Autowired
    private CaizhaixinxiService caizhaixinxiService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,CaizhaixinxiEntity caizhaixinxi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("chayuanzhuguan")) {
			caizhaixinxi.setZhuguanzhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("jiagongyuangong")) {
			caizhaixinxi.setGonghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<CaizhaixinxiEntity> ew = new EntityWrapper<CaizhaixinxiEntity>();

		PageUtils page = caizhaixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, caizhaixinxi), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,CaizhaixinxiEntity caizhaixinxi, 
		HttpServletRequest request){
        EntityWrapper<CaizhaixinxiEntity> ew = new EntityWrapper<CaizhaixinxiEntity>();

		PageUtils page = caizhaixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, caizhaixinxi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( CaizhaixinxiEntity caizhaixinxi){
       	EntityWrapper<CaizhaixinxiEntity> ew = new EntityWrapper<CaizhaixinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( caizhaixinxi, "caizhaixinxi")); 
        return R.ok().put("data", caizhaixinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(CaizhaixinxiEntity caizhaixinxi){
        EntityWrapper< CaizhaixinxiEntity> ew = new EntityWrapper< CaizhaixinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( caizhaixinxi, "caizhaixinxi")); 
		CaizhaixinxiView caizhaixinxiView =  caizhaixinxiService.selectView(ew);
		return R.ok("查询采摘信息成功").put("data", caizhaixinxiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        CaizhaixinxiEntity caizhaixinxi = caizhaixinxiService.selectById(id);
        return R.ok().put("data", caizhaixinxi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        CaizhaixinxiEntity caizhaixinxi = caizhaixinxiService.selectById(id);
        return R.ok().put("data", caizhaixinxi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CaizhaixinxiEntity caizhaixinxi, HttpServletRequest request){
    	caizhaixinxi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(caizhaixinxi);
        caizhaixinxiService.insert(caizhaixinxi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody CaizhaixinxiEntity caizhaixinxi, HttpServletRequest request){
    	caizhaixinxi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(caizhaixinxi);
        caizhaixinxiService.insert(caizhaixinxi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody CaizhaixinxiEntity caizhaixinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(caizhaixinxi);
        caizhaixinxiService.updateById(caizhaixinxi);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        caizhaixinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	










}
