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

import com.entity.ZhongzhijihuaEntity;
import com.entity.view.ZhongzhijihuaView;

import com.service.ZhongzhijihuaService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;
import com.service.StoreupService;
import com.entity.StoreupEntity;

/**
 * 种植计划
 * 后端接口
 * @author 
 * @email 
 * @date 2030-11-09 14:37:43
 */
@RestController
@RequestMapping("/zhongzhijihua")
public class ZhongzhijihuaController {
    @Autowired
    private ZhongzhijihuaService zhongzhijihuaService;

    @Autowired
    private StoreupService storeupService;


    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZhongzhijihuaEntity zhongzhijihua,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("chayuanzhuguan")) {
			zhongzhijihua.setZhuguanzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<ZhongzhijihuaEntity> ew = new EntityWrapper<ZhongzhijihuaEntity>();

		PageUtils page = zhongzhijihuaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhongzhijihua), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ZhongzhijihuaEntity zhongzhijihua, 
		HttpServletRequest request){
        EntityWrapper<ZhongzhijihuaEntity> ew = new EntityWrapper<ZhongzhijihuaEntity>();

		PageUtils page = zhongzhijihuaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhongzhijihua), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ZhongzhijihuaEntity zhongzhijihua){
       	EntityWrapper<ZhongzhijihuaEntity> ew = new EntityWrapper<ZhongzhijihuaEntity>();
      	ew.allEq(MPUtil.allEQMapPre( zhongzhijihua, "zhongzhijihua")); 
        return R.ok().put("data", zhongzhijihuaService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZhongzhijihuaEntity zhongzhijihua){
        EntityWrapper< ZhongzhijihuaEntity> ew = new EntityWrapper< ZhongzhijihuaEntity>();
 		ew.allEq(MPUtil.allEQMapPre( zhongzhijihua, "zhongzhijihua")); 
		ZhongzhijihuaView zhongzhijihuaView =  zhongzhijihuaService.selectView(ew);
		return R.ok("查询种植计划成功").put("data", zhongzhijihuaView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZhongzhijihuaEntity zhongzhijihua = zhongzhijihuaService.selectById(id);
        return R.ok().put("data", zhongzhijihua);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZhongzhijihuaEntity zhongzhijihua = zhongzhijihuaService.selectById(id);
        return R.ok().put("data", zhongzhijihua);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ZhongzhijihuaEntity zhongzhijihua, HttpServletRequest request){
    	zhongzhijihua.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(zhongzhijihua);
        zhongzhijihuaService.insert(zhongzhijihua);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ZhongzhijihuaEntity zhongzhijihua, HttpServletRequest request){
    	zhongzhijihua.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(zhongzhijihua);
        zhongzhijihuaService.insert(zhongzhijihua);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ZhongzhijihuaEntity zhongzhijihua, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zhongzhijihua);
        zhongzhijihuaService.updateById(zhongzhijihua);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        zhongzhijihuaService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	






    /**
     * （按值统计）
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}")
    public R value(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        EntityWrapper<ZhongzhijihuaEntity> ew = new EntityWrapper<ZhongzhijihuaEntity>();
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("chayuanzhuguan")) {
            ew.eq("zhuguanzhanghao", (String)request.getSession().getAttribute("username"));
		}
        List<Map<String, Object>> result = zhongzhijihuaService.selectValue(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }

    /**
     * （按值统计(多)）
     */
    @RequestMapping("/valueMul/{xColumnName}")
    public R valueMul(@PathVariable("xColumnName") String xColumnName,@RequestParam String yColumnNameMul, HttpServletRequest request) {
        String[] yColumnNames = yColumnNameMul.split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        EntityWrapper<ZhongzhijihuaEntity> ew = new EntityWrapper<ZhongzhijihuaEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("chayuanzhuguan")) {
            ew.eq("zhuguanzhanghao", (String)request.getSession().getAttribute("username"));
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = zhongzhijihuaService.selectValue(params, ew);
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }

    /**
     * （按值统计）时间统计类型
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}/{timeStatType}")
    public R valueDay(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        params.put("timeStatType", timeStatType);
        EntityWrapper<ZhongzhijihuaEntity> ew = new EntityWrapper<ZhongzhijihuaEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("chayuanzhuguan")) {
            ew.eq("zhuguanzhanghao", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = zhongzhijihuaService.selectTimeStatValue(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }

    /**
     * （按值统计）时间统计类型(多)
     */
    @RequestMapping("/valueMul/{xColumnName}/{timeStatType}")
    public R valueMulDay(@PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,@RequestParam String yColumnNameMul,HttpServletRequest request) {
        String[] yColumnNames = yColumnNameMul.split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("timeStatType", timeStatType);
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        EntityWrapper<ZhongzhijihuaEntity> ew = new EntityWrapper<ZhongzhijihuaEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("chayuanzhuguan")) {
            ew.eq("zhuguanzhanghao", (String)request.getSession().getAttribute("username"));
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = zhongzhijihuaService.selectTimeStatValue(params, ew);
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }

    /**
     * 分组统计
     */
    @RequestMapping("/group/{columnName}")
    public R group(@PathVariable("columnName") String columnName,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("column", columnName);
        EntityWrapper<ZhongzhijihuaEntity> ew = new EntityWrapper<ZhongzhijihuaEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("chayuanzhuguan")) {
            ew.eq("zhuguanzhanghao", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = zhongzhijihuaService.selectGroup(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }







}
