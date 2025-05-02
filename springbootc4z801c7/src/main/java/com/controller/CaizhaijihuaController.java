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

import com.entity.CaizhaijihuaEntity;
import com.entity.view.CaizhaijihuaView;

import com.service.CaizhaijihuaService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 采摘计划
 * 后端接口
 * @author 
 * @email 
 * @date 2030-11-09 14:37:43
 */
@RestController
@RequestMapping("/caizhaijihua")
public class CaizhaijihuaController {
    @Autowired
    private CaizhaijihuaService caizhaijihuaService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,CaizhaijihuaEntity caizhaijihua,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("chayuanzhuguan")) {
			caizhaijihua.setZhuguanzhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("jiagongyuangong")) {
			caizhaijihua.setGonghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<CaizhaijihuaEntity> ew = new EntityWrapper<CaizhaijihuaEntity>();

		PageUtils page = caizhaijihuaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, caizhaijihua), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,CaizhaijihuaEntity caizhaijihua, 
		HttpServletRequest request){
        EntityWrapper<CaizhaijihuaEntity> ew = new EntityWrapper<CaizhaijihuaEntity>();

		PageUtils page = caizhaijihuaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, caizhaijihua), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( CaizhaijihuaEntity caizhaijihua){
       	EntityWrapper<CaizhaijihuaEntity> ew = new EntityWrapper<CaizhaijihuaEntity>();
      	ew.allEq(MPUtil.allEQMapPre( caizhaijihua, "caizhaijihua")); 
        return R.ok().put("data", caizhaijihuaService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(CaizhaijihuaEntity caizhaijihua){
        EntityWrapper< CaizhaijihuaEntity> ew = new EntityWrapper< CaizhaijihuaEntity>();
 		ew.allEq(MPUtil.allEQMapPre( caizhaijihua, "caizhaijihua")); 
		CaizhaijihuaView caizhaijihuaView =  caizhaijihuaService.selectView(ew);
		return R.ok("查询采摘计划成功").put("data", caizhaijihuaView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        CaizhaijihuaEntity caizhaijihua = caizhaijihuaService.selectById(id);
        return R.ok().put("data", caizhaijihua);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        CaizhaijihuaEntity caizhaijihua = caizhaijihuaService.selectById(id);
        return R.ok().put("data", caizhaijihua);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CaizhaijihuaEntity caizhaijihua, HttpServletRequest request){
    	caizhaijihua.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(caizhaijihua);
        caizhaijihuaService.insert(caizhaijihua);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody CaizhaijihuaEntity caizhaijihua, HttpServletRequest request){
    	caizhaijihua.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(caizhaijihua);
        caizhaijihuaService.insert(caizhaijihua);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody CaizhaijihuaEntity caizhaijihua, HttpServletRequest request){
        //ValidatorUtils.validateEntity(caizhaijihua);
        caizhaijihuaService.updateById(caizhaijihua);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        caizhaijihuaService.deleteBatchIds(Arrays.asList(ids));
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
        EntityWrapper<CaizhaijihuaEntity> ew = new EntityWrapper<CaizhaijihuaEntity>();
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("chayuanzhuguan")) {
            ew.eq("zhuguanzhanghao", (String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("jiagongyuangong")) {
            ew.eq("gonghao", (String)request.getSession().getAttribute("username"));
		}
        List<Map<String, Object>> result = caizhaijihuaService.selectValue(params, ew);
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
        EntityWrapper<CaizhaijihuaEntity> ew = new EntityWrapper<CaizhaijihuaEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("chayuanzhuguan")) {
            ew.eq("zhuguanzhanghao", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("jiagongyuangong")) {
            ew.eq("gonghao", (String)request.getSession().getAttribute("username"));
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = caizhaijihuaService.selectValue(params, ew);
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
        EntityWrapper<CaizhaijihuaEntity> ew = new EntityWrapper<CaizhaijihuaEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("chayuanzhuguan")) {
            ew.eq("zhuguanzhanghao", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("jiagongyuangong")) {
            ew.eq("gonghao", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = caizhaijihuaService.selectTimeStatValue(params, ew);
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
        EntityWrapper<CaizhaijihuaEntity> ew = new EntityWrapper<CaizhaijihuaEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("chayuanzhuguan")) {
            ew.eq("zhuguanzhanghao", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("jiagongyuangong")) {
            ew.eq("gonghao", (String)request.getSession().getAttribute("username"));
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = caizhaijihuaService.selectTimeStatValue(params, ew);
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
        EntityWrapper<CaizhaijihuaEntity> ew = new EntityWrapper<CaizhaijihuaEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("chayuanzhuguan")) {
            ew.eq("zhuguanzhanghao", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("jiagongyuangong")) {
            ew.eq("gonghao", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = caizhaijihuaService.selectGroup(params, ew);
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
