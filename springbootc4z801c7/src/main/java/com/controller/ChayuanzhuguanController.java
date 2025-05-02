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

import com.entity.ChayuanzhuguanEntity;
import com.entity.view.ChayuanzhuguanView;

import com.service.ChayuanzhuguanService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 茶园主管
 * 后端接口
 * @author 
 * @email 
 * @date 2030-11-09 14:37:42
 */
@RestController
@RequestMapping("/chayuanzhuguan")
public class ChayuanzhuguanController {
    @Autowired
    private ChayuanzhuguanService chayuanzhuguanService;



    
	@Autowired
	private TokenService tokenService;
	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		ChayuanzhuguanEntity u = chayuanzhuguanService.selectOne(new EntityWrapper<ChayuanzhuguanEntity>().eq("zhuguanzhanghao", username));
		if(u==null || !u.getMima().equals(password)) {
			return R.error("账号或密码不正确");
		}
		
		String token = tokenService.generateToken(u.getId(), username,"chayuanzhuguan",  "茶园主管" );
		return R.ok().put("token", token);
	}

	
	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody ChayuanzhuguanEntity chayuanzhuguan){
    	//ValidatorUtils.validateEntity(chayuanzhuguan);
    	ChayuanzhuguanEntity u = chayuanzhuguanService.selectOne(new EntityWrapper<ChayuanzhuguanEntity>().eq("zhuguanzhanghao", chayuanzhuguan.getZhuguanzhanghao()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
		Long uId = new Date().getTime();
		chayuanzhuguan.setId(uId);
        chayuanzhuguanService.insert(chayuanzhuguan);
        return R.ok();
    }

	
	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}
	
	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        ChayuanzhuguanEntity u = chayuanzhuguanService.selectById(id);
        return R.ok().put("data", u);
    }
    
    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	ChayuanzhuguanEntity u = chayuanzhuguanService.selectOne(new EntityWrapper<ChayuanzhuguanEntity>().eq("zhuguanzhanghao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        u.setMima("123456");
        chayuanzhuguanService.updateById(u);
        return R.ok("密码已重置为：123456");
    }


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ChayuanzhuguanEntity chayuanzhuguan,
		HttpServletRequest request){
        EntityWrapper<ChayuanzhuguanEntity> ew = new EntityWrapper<ChayuanzhuguanEntity>();

		PageUtils page = chayuanzhuguanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chayuanzhuguan), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ChayuanzhuguanEntity chayuanzhuguan, 
		HttpServletRequest request){
        EntityWrapper<ChayuanzhuguanEntity> ew = new EntityWrapper<ChayuanzhuguanEntity>();

		PageUtils page = chayuanzhuguanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chayuanzhuguan), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ChayuanzhuguanEntity chayuanzhuguan){
       	EntityWrapper<ChayuanzhuguanEntity> ew = new EntityWrapper<ChayuanzhuguanEntity>();
      	ew.allEq(MPUtil.allEQMapPre( chayuanzhuguan, "chayuanzhuguan")); 
        return R.ok().put("data", chayuanzhuguanService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ChayuanzhuguanEntity chayuanzhuguan){
        EntityWrapper< ChayuanzhuguanEntity> ew = new EntityWrapper< ChayuanzhuguanEntity>();
 		ew.allEq(MPUtil.allEQMapPre( chayuanzhuguan, "chayuanzhuguan")); 
		ChayuanzhuguanView chayuanzhuguanView =  chayuanzhuguanService.selectView(ew);
		return R.ok("查询茶园主管成功").put("data", chayuanzhuguanView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ChayuanzhuguanEntity chayuanzhuguan = chayuanzhuguanService.selectById(id);
        return R.ok().put("data", chayuanzhuguan);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ChayuanzhuguanEntity chayuanzhuguan = chayuanzhuguanService.selectById(id);
        return R.ok().put("data", chayuanzhuguan);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ChayuanzhuguanEntity chayuanzhuguan, HttpServletRequest request){
    	chayuanzhuguan.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(chayuanzhuguan);
    	ChayuanzhuguanEntity u = chayuanzhuguanService.selectOne(new EntityWrapper<ChayuanzhuguanEntity>().eq("zhuguanzhanghao", chayuanzhuguan.getZhuguanzhanghao()));
		if(u!=null) {
			return R.error("用户已存在");
		}
		chayuanzhuguan.setId(new Date().getTime());
        chayuanzhuguanService.insert(chayuanzhuguan);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ChayuanzhuguanEntity chayuanzhuguan, HttpServletRequest request){
    	chayuanzhuguan.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(chayuanzhuguan);
    	ChayuanzhuguanEntity u = chayuanzhuguanService.selectOne(new EntityWrapper<ChayuanzhuguanEntity>().eq("zhuguanzhanghao", chayuanzhuguan.getZhuguanzhanghao()));
		if(u!=null) {
			return R.error("用户已存在");
		}
		chayuanzhuguan.setId(new Date().getTime());
        chayuanzhuguanService.insert(chayuanzhuguan);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ChayuanzhuguanEntity chayuanzhuguan, HttpServletRequest request){
        //ValidatorUtils.validateEntity(chayuanzhuguan);
        chayuanzhuguanService.updateById(chayuanzhuguan);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        chayuanzhuguanService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	










}
