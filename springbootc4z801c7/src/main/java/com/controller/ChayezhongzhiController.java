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

import com.entity.ChayezhongzhiEntity;
import com.entity.view.ChayezhongzhiView;

import com.service.ChayezhongzhiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 茶叶种植
 * 后端接口
 * @author 
 * @email 
 * @date 2030-11-09 14:37:43
 */
@RestController
@RequestMapping("/chayezhongzhi")
public class ChayezhongzhiController {
    @Autowired
    private ChayezhongzhiService chayezhongzhiService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ChayezhongzhiEntity chayezhongzhi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("chayuanzhuguan")) {
			chayezhongzhi.setZhuguanzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<ChayezhongzhiEntity> ew = new EntityWrapper<ChayezhongzhiEntity>();

		PageUtils page = chayezhongzhiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chayezhongzhi), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ChayezhongzhiEntity chayezhongzhi, 
		HttpServletRequest request){
        EntityWrapper<ChayezhongzhiEntity> ew = new EntityWrapper<ChayezhongzhiEntity>();

		PageUtils page = chayezhongzhiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chayezhongzhi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ChayezhongzhiEntity chayezhongzhi){
       	EntityWrapper<ChayezhongzhiEntity> ew = new EntityWrapper<ChayezhongzhiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( chayezhongzhi, "chayezhongzhi")); 
        return R.ok().put("data", chayezhongzhiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ChayezhongzhiEntity chayezhongzhi){
        EntityWrapper< ChayezhongzhiEntity> ew = new EntityWrapper< ChayezhongzhiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( chayezhongzhi, "chayezhongzhi")); 
		ChayezhongzhiView chayezhongzhiView =  chayezhongzhiService.selectView(ew);
		return R.ok("查询茶叶种植成功").put("data", chayezhongzhiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ChayezhongzhiEntity chayezhongzhi = chayezhongzhiService.selectById(id);
        return R.ok().put("data", chayezhongzhi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ChayezhongzhiEntity chayezhongzhi = chayezhongzhiService.selectById(id);
        return R.ok().put("data", chayezhongzhi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ChayezhongzhiEntity chayezhongzhi, HttpServletRequest request){
    	chayezhongzhi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(chayezhongzhi);
        chayezhongzhiService.insert(chayezhongzhi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ChayezhongzhiEntity chayezhongzhi, HttpServletRequest request){
    	chayezhongzhi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(chayezhongzhi);
        chayezhongzhiService.insert(chayezhongzhi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ChayezhongzhiEntity chayezhongzhi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(chayezhongzhi);
        chayezhongzhiService.updateById(chayezhongzhi);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        chayezhongzhiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	










}
