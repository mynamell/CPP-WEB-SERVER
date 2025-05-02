<template>
	<div>
		<div class="container" :style='{"minHeight":"100vh","alignItems":"center","background":"url(http://codegen.caihongy.cn/20230830/25e4efeaf5f14216b2e7e70a43d1c06e.jpg)","display":"flex","width":"100%","backgroundSize":"cover","backgroundPosition":"center top","backgroundRepeat":"no-repeat","justifyContent":"center"}'>
			<el-form v-if="pageFlag=='register'" :style='{"border":"0px solid #eee","minHeight":"690px","padding":"100px 60px 20px 600px","boxShadow":"0 0px 0px rgba(64, 158, 255, .6)","margin":"0","borderRadius":"10px","color":"#999","background":"url(http://codegen.caihongy.cn/20230801/017937c712194d5e8a38f9bca47c4a73.png) no-repeat center top / 100% 100%","width":"1200px","height":"auto"}' ref="rgsForm" class="rgs-form" :model="rgsForm" :rules="rules">
				<div v-if="true" :style='{"padding":"10px","margin":"0 auto 30px","color":"#56b2c6","textAlign":"center","background":"none","width":"96%","lineHeight":"40px","fontSize":"24px","textShadow":"4px 4px 2px rgba(64, 158, 255, .1)"}' class="title">基于Spring Boot的茶叶种植管理系统注册</div>
				<el-form-item :style='{"width":"80%","padding":"0 100px","margin":"0 auto 10px","position":"relative","height":"auto"}' class="list-item" v-if="tableName=='kehu'">
					<div v-if="true" :style='{"padding":"0 8px 0 0","color":"inherit","textAlign":"right","left":"-120px","width":"120px","lineHeight":"40px","fontSize":"16px","position":"absolute"}' class="lable" :class="changeRules('zhanghao')?'required':''">账号：</div>
					<el-input  v-model="ruleForm.zhanghao"  autocomplete="off" placeholder="账号"  type="text"  />
				</el-form-item>
				<el-form-item :style='{"width":"80%","padding":"0 100px","margin":"0 auto 10px","position":"relative","height":"auto"}' class="list-item" v-if="tableName=='kehu'">
					<div v-if="true" :style='{"padding":"0 8px 0 0","color":"inherit","textAlign":"right","left":"-120px","width":"120px","lineHeight":"40px","fontSize":"16px","position":"absolute"}' class="lable" :class="changeRules('mima')?'required':''">密码：</div>
					<el-input  v-model="ruleForm.mima"  autocomplete="off" placeholder="密码"  type="password"  />
				</el-form-item>
				<el-form-item :style='{"width":"80%","padding":"0 100px","margin":"0 auto 10px","position":"relative","height":"auto"}' class="list-item" v-if="tableName=='kehu'">
					<div v-if="true" :style='{"padding":"0 8px 0 0","color":"inherit","textAlign":"right","left":"-120px","width":"120px","lineHeight":"40px","fontSize":"16px","position":"absolute"}' class="lable" :class="changeRules('mima')?'required':''">确认密码：</div>
					<el-input  v-model="ruleForm.mima2" autocomplete="off" placeholder="确认密码" type="password" />
				</el-form-item>
				<el-form-item :style='{"width":"80%","padding":"0 100px","margin":"0 auto 10px","position":"relative","height":"auto"}' class="list-item" v-if="tableName=='kehu'">
					<div v-if="true" :style='{"padding":"0 8px 0 0","color":"inherit","textAlign":"right","left":"-120px","width":"120px","lineHeight":"40px","fontSize":"16px","position":"absolute"}' class="lable" :class="changeRules('xingming')?'required':''">姓名：</div>
					<el-input  v-model="ruleForm.xingming"  autocomplete="off" placeholder="姓名"  type="text"  />
				</el-form-item>
				<el-form-item :style='{"width":"80%","padding":"0 100px","margin":"0 auto 10px","position":"relative","height":"auto"}' class="list-item" v-if="tableName=='kehu'">
					<div v-if="true" :style='{"padding":"0 8px 0 0","color":"inherit","textAlign":"right","left":"-120px","width":"120px","lineHeight":"40px","fontSize":"16px","position":"absolute"}' class="lable" :class="changeRules('nianling')?'required':''">年龄：</div>
					<el-input  v-model="ruleForm.nianling"  autocomplete="off" placeholder="年龄"  type="text"  />
				</el-form-item>
				<el-form-item :style='{"width":"80%","padding":"0 100px","margin":"0 auto 10px","position":"relative","height":"auto"}' class="list-item" v-if="tableName=='kehu'">
					<div v-if="true" :style='{"padding":"0 8px 0 0","color":"inherit","textAlign":"right","left":"-120px","width":"120px","lineHeight":"40px","fontSize":"16px","position":"absolute"}' class="lable" :class="changeRules('xingbie')?'required':''">性别：</div>
                    <el-select v-model="ruleForm.xingbie" placeholder="请选择性别" >
                        <el-option
                            v-for="(item,index) in kehuxingbieOptions"
                            v-bind:key="index"
                            :label="item"
                            :value="item">
                        </el-option>
                    </el-select>
				</el-form-item>
				<el-form-item :style='{"width":"80%","padding":"0 100px","margin":"0 auto 10px","position":"relative","height":"auto"}' class="list-item" v-if="tableName=='kehu'">
					<div v-if="true" :style='{"padding":"0 8px 0 0","color":"inherit","textAlign":"right","left":"-120px","width":"120px","lineHeight":"40px","fontSize":"16px","position":"absolute"}' class="lable" :class="changeRules('shouji')?'required':''">手机：</div>
					<el-input  v-model="ruleForm.shouji"  autocomplete="off" placeholder="手机"  type="text"  />
				</el-form-item>
				<el-form-item :style='{"width":"80%","padding":"0 100px","margin":"0 auto 10px","position":"relative","height":"auto"}' class="list-item" v-if="tableName=='kehu'">
					<div v-if="true" :style='{"padding":"0 8px 0 0","color":"inherit","textAlign":"right","left":"-120px","width":"120px","lineHeight":"40px","fontSize":"16px","position":"absolute"}' class="lable" :class="changeRules('touxiang')?'required':''">头像：</div>
                    <file-upload
                        tip="点击上传头像"
                        action="file/upload"
                        :limit="3"
                        :multiple="true"
                        :fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
                        @change="kehutouxiangUploadChange"
                    ></file-upload>
				</el-form-item>
				<el-form-item :style='{"width":"80%","padding":"0 100px","margin":"0 auto 10px","position":"relative","height":"auto"}' class="list-item" v-if="tableName=='chayuanzhuguan'">
					<div v-if="true" :style='{"padding":"0 8px 0 0","color":"inherit","textAlign":"right","left":"-120px","width":"120px","lineHeight":"40px","fontSize":"16px","position":"absolute"}' class="lable" :class="changeRules('zhuguanzhanghao')?'required':''">主管账号：</div>
					<el-input  v-model="ruleForm.zhuguanzhanghao"  autocomplete="off" placeholder="主管账号"  type="text"  />
				</el-form-item>
				<el-form-item :style='{"width":"80%","padding":"0 100px","margin":"0 auto 10px","position":"relative","height":"auto"}' class="list-item" v-if="tableName=='chayuanzhuguan'">
					<div v-if="true" :style='{"padding":"0 8px 0 0","color":"inherit","textAlign":"right","left":"-120px","width":"120px","lineHeight":"40px","fontSize":"16px","position":"absolute"}' class="lable" :class="changeRules('mima')?'required':''">密码：</div>
					<el-input  v-model="ruleForm.mima"  autocomplete="off" placeholder="密码"  type="password"  />
				</el-form-item>
				<el-form-item :style='{"width":"80%","padding":"0 100px","margin":"0 auto 10px","position":"relative","height":"auto"}' class="list-item" v-if="tableName=='chayuanzhuguan'">
					<div v-if="true" :style='{"padding":"0 8px 0 0","color":"inherit","textAlign":"right","left":"-120px","width":"120px","lineHeight":"40px","fontSize":"16px","position":"absolute"}' class="lable" :class="changeRules('mima')?'required':''">确认密码：</div>
					<el-input  v-model="ruleForm.mima2" autocomplete="off" placeholder="确认密码" type="password" />
				</el-form-item>
				<el-form-item :style='{"width":"80%","padding":"0 100px","margin":"0 auto 10px","position":"relative","height":"auto"}' class="list-item" v-if="tableName=='chayuanzhuguan'">
					<div v-if="true" :style='{"padding":"0 8px 0 0","color":"inherit","textAlign":"right","left":"-120px","width":"120px","lineHeight":"40px","fontSize":"16px","position":"absolute"}' class="lable" :class="changeRules('zhuguanxingming')?'required':''">主管姓名：</div>
					<el-input  v-model="ruleForm.zhuguanxingming"  autocomplete="off" placeholder="主管姓名"  type="text"  />
				</el-form-item>
				<el-form-item :style='{"width":"80%","padding":"0 100px","margin":"0 auto 10px","position":"relative","height":"auto"}' class="list-item" v-if="tableName=='chayuanzhuguan'">
					<div v-if="true" :style='{"padding":"0 8px 0 0","color":"inherit","textAlign":"right","left":"-120px","width":"120px","lineHeight":"40px","fontSize":"16px","position":"absolute"}' class="lable" :class="changeRules('nianling')?'required':''">年龄：</div>
					<el-input  v-model="ruleForm.nianling"  autocomplete="off" placeholder="年龄"  type="text"  />
				</el-form-item>
				<el-form-item :style='{"width":"80%","padding":"0 100px","margin":"0 auto 10px","position":"relative","height":"auto"}' class="list-item" v-if="tableName=='chayuanzhuguan'">
					<div v-if="true" :style='{"padding":"0 8px 0 0","color":"inherit","textAlign":"right","left":"-120px","width":"120px","lineHeight":"40px","fontSize":"16px","position":"absolute"}' class="lable" :class="changeRules('xingbie')?'required':''">性别：</div>
                    <el-select v-model="ruleForm.xingbie" placeholder="请选择性别" >
                        <el-option
                            v-for="(item,index) in chayuanzhuguanxingbieOptions"
                            v-bind:key="index"
                            :label="item"
                            :value="item">
                        </el-option>
                    </el-select>
				</el-form-item>
				<el-form-item :style='{"width":"80%","padding":"0 100px","margin":"0 auto 10px","position":"relative","height":"auto"}' class="list-item" v-if="tableName=='chayuanzhuguan'">
					<div v-if="true" :style='{"padding":"0 8px 0 0","color":"inherit","textAlign":"right","left":"-120px","width":"120px","lineHeight":"40px","fontSize":"16px","position":"absolute"}' class="lable" :class="changeRules('shouji')?'required':''">手机：</div>
					<el-input  v-model="ruleForm.shouji"  autocomplete="off" placeholder="手机"  type="text"  />
				</el-form-item>
				<el-form-item :style='{"width":"80%","padding":"0 100px","margin":"0 auto 10px","position":"relative","height":"auto"}' class="list-item" v-if="tableName=='chayuanzhuguan'">
					<div v-if="true" :style='{"padding":"0 8px 0 0","color":"inherit","textAlign":"right","left":"-120px","width":"120px","lineHeight":"40px","fontSize":"16px","position":"absolute"}' class="lable" :class="changeRules('touxiang')?'required':''">头像：</div>
                    <file-upload
                        tip="点击上传头像"
                        action="file/upload"
                        :limit="3"
                        :multiple="true"
                        :fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
                        @change="chayuanzhuguantouxiangUploadChange"
                    ></file-upload>
				</el-form-item>
				<el-form-item :style='{"width":"80%","padding":"0 100px","margin":"0 auto 10px","position":"relative","height":"auto"}' class="list-item" v-if="tableName=='jiagongyuangong'">
					<div v-if="true" :style='{"padding":"0 8px 0 0","color":"inherit","textAlign":"right","left":"-120px","width":"120px","lineHeight":"40px","fontSize":"16px","position":"absolute"}' class="lable" :class="changeRules('gonghao')?'required':''">工号：</div>
					<el-input  v-model="ruleForm.gonghao"  autocomplete="off" placeholder="工号"  type="text"  />
				</el-form-item>
				<el-form-item :style='{"width":"80%","padding":"0 100px","margin":"0 auto 10px","position":"relative","height":"auto"}' class="list-item" v-if="tableName=='jiagongyuangong'">
					<div v-if="true" :style='{"padding":"0 8px 0 0","color":"inherit","textAlign":"right","left":"-120px","width":"120px","lineHeight":"40px","fontSize":"16px","position":"absolute"}' class="lable" :class="changeRules('mima')?'required':''">密码：</div>
					<el-input  v-model="ruleForm.mima"  autocomplete="off" placeholder="密码"  type="password"  />
				</el-form-item>
				<el-form-item :style='{"width":"80%","padding":"0 100px","margin":"0 auto 10px","position":"relative","height":"auto"}' class="list-item" v-if="tableName=='jiagongyuangong'">
					<div v-if="true" :style='{"padding":"0 8px 0 0","color":"inherit","textAlign":"right","left":"-120px","width":"120px","lineHeight":"40px","fontSize":"16px","position":"absolute"}' class="lable" :class="changeRules('mima')?'required':''">确认密码：</div>
					<el-input  v-model="ruleForm.mima2" autocomplete="off" placeholder="确认密码" type="password" />
				</el-form-item>
				<el-form-item :style='{"width":"80%","padding":"0 100px","margin":"0 auto 10px","position":"relative","height":"auto"}' class="list-item" v-if="tableName=='jiagongyuangong'">
					<div v-if="true" :style='{"padding":"0 8px 0 0","color":"inherit","textAlign":"right","left":"-120px","width":"120px","lineHeight":"40px","fontSize":"16px","position":"absolute"}' class="lable" :class="changeRules('xingming')?'required':''">姓名：</div>
					<el-input  v-model="ruleForm.xingming"  autocomplete="off" placeholder="姓名"  type="text"  />
				</el-form-item>
				<el-form-item :style='{"width":"80%","padding":"0 100px","margin":"0 auto 10px","position":"relative","height":"auto"}' class="list-item" v-if="tableName=='jiagongyuangong'">
					<div v-if="true" :style='{"padding":"0 8px 0 0","color":"inherit","textAlign":"right","left":"-120px","width":"120px","lineHeight":"40px","fontSize":"16px","position":"absolute"}' class="lable" :class="changeRules('xingbie')?'required':''">性别：</div>
                    <el-select v-model="ruleForm.xingbie" placeholder="请选择性别" >
                        <el-option
                            v-for="(item,index) in jiagongyuangongxingbieOptions"
                            v-bind:key="index"
                            :label="item"
                            :value="item">
                        </el-option>
                    </el-select>
				</el-form-item>
				<el-form-item :style='{"width":"80%","padding":"0 100px","margin":"0 auto 10px","position":"relative","height":"auto"}' class="list-item" v-if="tableName=='jiagongyuangong'">
					<div v-if="true" :style='{"padding":"0 8px 0 0","color":"inherit","textAlign":"right","left":"-120px","width":"120px","lineHeight":"40px","fontSize":"16px","position":"absolute"}' class="lable" :class="changeRules('nianling')?'required':''">年龄：</div>
					<el-input  v-model="ruleForm.nianling"  autocomplete="off" placeholder="年龄"  type="text"  />
				</el-form-item>
				<el-form-item :style='{"width":"80%","padding":"0 100px","margin":"0 auto 10px","position":"relative","height":"auto"}' class="list-item" v-if="tableName=='jiagongyuangong'">
					<div v-if="true" :style='{"padding":"0 8px 0 0","color":"inherit","textAlign":"right","left":"-120px","width":"120px","lineHeight":"40px","fontSize":"16px","position":"absolute"}' class="lable" :class="changeRules('shouji')?'required':''">手机：</div>
					<el-input  v-model="ruleForm.shouji"  autocomplete="off" placeholder="手机"  type="text"  />
				</el-form-item>
				<el-form-item :style='{"width":"80%","padding":"0 100px","margin":"0 auto 10px","position":"relative","height":"auto"}' class="list-item" v-if="tableName=='jiagongyuangong'">
					<div v-if="true" :style='{"padding":"0 8px 0 0","color":"inherit","textAlign":"right","left":"-120px","width":"120px","lineHeight":"40px","fontSize":"16px","position":"absolute"}' class="lable" :class="changeRules('touxiang')?'required':''">头像：</div>
                    <file-upload
                        tip="点击上传头像"
                        action="file/upload"
                        :limit="3"
                        :multiple="true"
                        :fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
                        @change="jiagongyuangongtouxiangUploadChange"
                    ></file-upload>
				</el-form-item>
				<el-form-item :style='{"width":"80%","padding":"0 100px","margin":"0 auto 10px","position":"relative","height":"auto"}' class="list-item" v-if="tableName=='xiaoshourenyuan'">
					<div v-if="true" :style='{"padding":"0 8px 0 0","color":"inherit","textAlign":"right","left":"-120px","width":"120px","lineHeight":"40px","fontSize":"16px","position":"absolute"}' class="lable" :class="changeRules('xiaoshouzhanghao')?'required':''">销售账号：</div>
					<el-input  v-model="ruleForm.xiaoshouzhanghao"  autocomplete="off" placeholder="销售账号"  type="text"  />
				</el-form-item>
				<el-form-item :style='{"width":"80%","padding":"0 100px","margin":"0 auto 10px","position":"relative","height":"auto"}' class="list-item" v-if="tableName=='xiaoshourenyuan'">
					<div v-if="true" :style='{"padding":"0 8px 0 0","color":"inherit","textAlign":"right","left":"-120px","width":"120px","lineHeight":"40px","fontSize":"16px","position":"absolute"}' class="lable" :class="changeRules('mima')?'required':''">密码：</div>
					<el-input  v-model="ruleForm.mima"  autocomplete="off" placeholder="密码"  type="password"  />
				</el-form-item>
				<el-form-item :style='{"width":"80%","padding":"0 100px","margin":"0 auto 10px","position":"relative","height":"auto"}' class="list-item" v-if="tableName=='xiaoshourenyuan'">
					<div v-if="true" :style='{"padding":"0 8px 0 0","color":"inherit","textAlign":"right","left":"-120px","width":"120px","lineHeight":"40px","fontSize":"16px","position":"absolute"}' class="lable" :class="changeRules('mima')?'required':''">确认密码：</div>
					<el-input  v-model="ruleForm.mima2" autocomplete="off" placeholder="确认密码" type="password" />
				</el-form-item>
				<el-form-item :style='{"width":"80%","padding":"0 100px","margin":"0 auto 10px","position":"relative","height":"auto"}' class="list-item" v-if="tableName=='xiaoshourenyuan'">
					<div v-if="true" :style='{"padding":"0 8px 0 0","color":"inherit","textAlign":"right","left":"-120px","width":"120px","lineHeight":"40px","fontSize":"16px","position":"absolute"}' class="lable" :class="changeRules('xiaoshouxingming')?'required':''">销售姓名：</div>
					<el-input  v-model="ruleForm.xiaoshouxingming"  autocomplete="off" placeholder="销售姓名"  type="text"  />
				</el-form-item>
				<el-form-item :style='{"width":"80%","padding":"0 100px","margin":"0 auto 10px","position":"relative","height":"auto"}' class="list-item" v-if="tableName=='xiaoshourenyuan'">
					<div v-if="true" :style='{"padding":"0 8px 0 0","color":"inherit","textAlign":"right","left":"-120px","width":"120px","lineHeight":"40px","fontSize":"16px","position":"absolute"}' class="lable" :class="changeRules('xingbie')?'required':''">性别：</div>
                    <el-select v-model="ruleForm.xingbie" placeholder="请选择性别" >
                        <el-option
                            v-for="(item,index) in xiaoshourenyuanxingbieOptions"
                            v-bind:key="index"
                            :label="item"
                            :value="item">
                        </el-option>
                    </el-select>
				</el-form-item>
				<el-form-item :style='{"width":"80%","padding":"0 100px","margin":"0 auto 10px","position":"relative","height":"auto"}' class="list-item" v-if="tableName=='xiaoshourenyuan'">
					<div v-if="true" :style='{"padding":"0 8px 0 0","color":"inherit","textAlign":"right","left":"-120px","width":"120px","lineHeight":"40px","fontSize":"16px","position":"absolute"}' class="lable" :class="changeRules('nianling')?'required':''">年龄：</div>
					<el-input  v-model="ruleForm.nianling"  autocomplete="off" placeholder="年龄"  type="text"  />
				</el-form-item>
				<el-form-item :style='{"width":"80%","padding":"0 100px","margin":"0 auto 10px","position":"relative","height":"auto"}' class="list-item" v-if="tableName=='xiaoshourenyuan'">
					<div v-if="true" :style='{"padding":"0 8px 0 0","color":"inherit","textAlign":"right","left":"-120px","width":"120px","lineHeight":"40px","fontSize":"16px","position":"absolute"}' class="lable" :class="changeRules('shouji')?'required':''">手机：</div>
					<el-input  v-model="ruleForm.shouji"  autocomplete="off" placeholder="手机"  type="text"  />
				</el-form-item>
				<el-form-item :style='{"width":"80%","padding":"0 100px","margin":"0 auto 10px","position":"relative","height":"auto"}' class="list-item" v-if="tableName=='xiaoshourenyuan'">
					<div v-if="true" :style='{"padding":"0 8px 0 0","color":"inherit","textAlign":"right","left":"-120px","width":"120px","lineHeight":"40px","fontSize":"16px","position":"absolute"}' class="lable" :class="changeRules('touxiang')?'required':''">头像：</div>
                    <file-upload
                        tip="点击上传头像"
                        action="file/upload"
                        :limit="3"
                        :multiple="true"
                        :fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
                        @change="xiaoshourenyuantouxiangUploadChange"
                    ></file-upload>
				</el-form-item>
				<button :style='{"border":"0","cursor":"pointer","padding":"0 24px","boxShadow":"0 0 0px rgba(64, 158, 255, .5)","margin":"20px auto 10px","color":"#fff","display":"block","outline":"none","borderRadius":"30px","background":"#56b2c6","width":"200px","fontSize":"16px","height":"40px"}' type="button" class="r-btn" @click="login()">注册</button>
				<div :style='{"cursor":"pointer","padding":"0","color":"rgba(159, 159, 159, 1)","textAlign":"right","display":"inline-block","width":"80%","lineHeight":"1","fontSize":"14px","textDecoration":"underline"}' class="r-login" @click="close()">已有账号，直接登录</div>
			</el-form>
			
		</div>
	</div>
</template>

<script>
export default {
	data() {
		return {
			ruleForm: {
			},

            pageFlag : '',
			tableName:"",
			rules: {},
            kehuxingbieOptions: [],
            chayuanzhuguanxingbieOptions: [],
            jiagongyuangongxingbieOptions: [],
            xiaoshourenyuanxingbieOptions: [],
		};
	},
	mounted(){
        this.pageFlag = this.$storage.get("pageFlag");
		let table = this.$storage.get("loginTable");
		this.tableName = table;
		if(this.tableName=='kehu'){
			this.ruleForm = {
                zhanghao: '',
                mima: '',
                xingming: '',
                nianling: '',
                xingbie: '',
                shouji: '',
                touxiang: '',
                money: '',
			}
		}
		if(this.tableName=='chayuanzhuguan'){
			this.ruleForm = {
                zhuguanzhanghao: '',
                mima: '',
                zhuguanxingming: '',
                nianling: '',
                xingbie: '',
                shouji: '',
                touxiang: '',
                money: '',
			}
		}
		if(this.tableName=='jiagongyuangong'){
			this.ruleForm = {
                gonghao: '',
                mima: '',
                xingming: '',
                xingbie: '',
                nianling: '',
                shouji: '',
                touxiang: '',
                money: '',
			}
		}
		if(this.tableName=='xiaoshourenyuan'){
			this.ruleForm = {
                xiaoshouzhanghao: '',
                mima: '',
                xiaoshouxingming: '',
                xingbie: '',
                nianling: '',
                shouji: '',
                touxiang: '',
                money: '',
			}
		}
		if ('kehu' == this.tableName) {
			this.rules.zhanghao = [{ required: true, message: '请输入账号', trigger: 'blur' }]
		}
		if ('kehu' == this.tableName) {
			this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
		}
		if ('kehu' == this.tableName) {
			this.rules.xingming = [{ required: true, message: '请输入姓名', trigger: 'blur' }]
		}
		if ('chayuanzhuguan' == this.tableName) {
			this.rules.zhuguanzhanghao = [{ required: true, message: '请输入主管账号', trigger: 'blur' }]
		}
		if ('chayuanzhuguan' == this.tableName) {
			this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
		}
		if ('chayuanzhuguan' == this.tableName) {
			this.rules.zhuguanxingming = [{ required: true, message: '请输入主管姓名', trigger: 'blur' }]
		}
		if ('jiagongyuangong' == this.tableName) {
			this.rules.gonghao = [{ required: true, message: '请输入工号', trigger: 'blur' }]
		}
		if ('jiagongyuangong' == this.tableName) {
			this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
		}
		if ('jiagongyuangong' == this.tableName) {
			this.rules.xingming = [{ required: true, message: '请输入姓名', trigger: 'blur' }]
		}
		if ('xiaoshourenyuan' == this.tableName) {
			this.rules.xiaoshouzhanghao = [{ required: true, message: '请输入销售账号', trigger: 'blur' }]
		}
		if ('xiaoshourenyuan' == this.tableName) {
			this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
		}
		if ('xiaoshourenyuan' == this.tableName) {
			this.rules.xiaoshouxingming = [{ required: true, message: '请输入销售姓名', trigger: 'blur' }]
		}
        this.kehuxingbieOptions = "男,女".split(',')
        this.chayuanzhuguanxingbieOptions = "男,女".split(',')
        this.jiagongyuangongxingbieOptions = "男,女".split(',')
        this.xiaoshourenyuanxingbieOptions = "男,女".split(',')
	},
	created() {},
	destroyed() {
		  	},
	methods: {
		changeRules(name){
			if(this.rules[name]){
				return true
			}
			return false
		},
		// 获取uuid
		getUUID () {
			return new Date().getTime();
		},
		close(){
			this.$router.push({ path: "/login" });
		},
        kehutouxiangUploadChange(fileUrls) {
            this.ruleForm.touxiang = fileUrls;
        },
        chayuanzhuguantouxiangUploadChange(fileUrls) {
            this.ruleForm.touxiang = fileUrls;
        },
        jiagongyuangongtouxiangUploadChange(fileUrls) {
            this.ruleForm.touxiang = fileUrls;
        },
        xiaoshourenyuantouxiangUploadChange(fileUrls) {
            this.ruleForm.touxiang = fileUrls;
        },

        // 多级联动参数


		// 注册
		login() {
			var url=this.tableName+"/register";
					if((!this.ruleForm.zhanghao) && `kehu` == this.tableName){
						this.$message.error(`账号不能为空`);
						return
					}
					if((!this.ruleForm.mima) && `kehu` == this.tableName){
						this.$message.error(`密码不能为空`);
						return
					}
					if((this.ruleForm.mima!=this.ruleForm.mima2) && `kehu` == this.tableName){
						this.$message.error(`两次密码输入不一致`);
						return
					}
					if((!this.ruleForm.xingming) && `kehu` == this.tableName){
						this.$message.error(`姓名不能为空`);
						return
					}
					if(`kehu` == this.tableName && this.ruleForm.nianling &&(!this.$validate.isIntNumer(this.ruleForm.nianling))){
						this.$message.error(`年龄应输入整数`);
						return
					}
					if(`kehu` == this.tableName && this.ruleForm.shouji &&(!this.$validate.isMobile(this.ruleForm.shouji))){
						this.$message.error(`手机应输入手机格式`);
						return
					}
            if(this.ruleForm.touxiang!=null) {
                this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
            }
					if((!this.ruleForm.zhuguanzhanghao) && `chayuanzhuguan` == this.tableName){
						this.$message.error(`主管账号不能为空`);
						return
					}
					if((!this.ruleForm.mima) && `chayuanzhuguan` == this.tableName){
						this.$message.error(`密码不能为空`);
						return
					}
					if((this.ruleForm.mima!=this.ruleForm.mima2) && `chayuanzhuguan` == this.tableName){
						this.$message.error(`两次密码输入不一致`);
						return
					}
					if((!this.ruleForm.zhuguanxingming) && `chayuanzhuguan` == this.tableName){
						this.$message.error(`主管姓名不能为空`);
						return
					}
					if(`chayuanzhuguan` == this.tableName && this.ruleForm.nianling &&(!this.$validate.isIntNumer(this.ruleForm.nianling))){
						this.$message.error(`年龄应输入整数`);
						return
					}
					if(`chayuanzhuguan` == this.tableName && this.ruleForm.shouji &&(!this.$validate.isMobile(this.ruleForm.shouji))){
						this.$message.error(`手机应输入手机格式`);
						return
					}
            if(this.ruleForm.touxiang!=null) {
                this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
            }
					if((!this.ruleForm.gonghao) && `jiagongyuangong` == this.tableName){
						this.$message.error(`工号不能为空`);
						return
					}
					if((!this.ruleForm.mima) && `jiagongyuangong` == this.tableName){
						this.$message.error(`密码不能为空`);
						return
					}
					if((this.ruleForm.mima!=this.ruleForm.mima2) && `jiagongyuangong` == this.tableName){
						this.$message.error(`两次密码输入不一致`);
						return
					}
					if((!this.ruleForm.xingming) && `jiagongyuangong` == this.tableName){
						this.$message.error(`姓名不能为空`);
						return
					}
					if(`jiagongyuangong` == this.tableName && this.ruleForm.shouji &&(!this.$validate.isMobile(this.ruleForm.shouji))){
						this.$message.error(`手机应输入手机格式`);
						return
					}
            if(this.ruleForm.touxiang!=null) {
                this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
            }
					if((!this.ruleForm.xiaoshouzhanghao) && `xiaoshourenyuan` == this.tableName){
						this.$message.error(`销售账号不能为空`);
						return
					}
					if((!this.ruleForm.mima) && `xiaoshourenyuan` == this.tableName){
						this.$message.error(`密码不能为空`);
						return
					}
					if((this.ruleForm.mima!=this.ruleForm.mima2) && `xiaoshourenyuan` == this.tableName){
						this.$message.error(`两次密码输入不一致`);
						return
					}
					if((!this.ruleForm.xiaoshouxingming) && `xiaoshourenyuan` == this.tableName){
						this.$message.error(`销售姓名不能为空`);
						return
					}
					if(`xiaoshourenyuan` == this.tableName && this.ruleForm.shouji &&(!this.$validate.isMobile(this.ruleForm.shouji))){
						this.$message.error(`手机应输入手机格式`);
						return
					}
            if(this.ruleForm.touxiang!=null) {
                this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
            }
			this.$http({
				url: url,
				method: "post",
				data:this.ruleForm
			}).then(({ data }) => {
				if (data && data.code === 0) {
					this.$message({
						message: "注册成功",
						type: "success",
						duration: 1500,
						onClose: () => {
							this.$router.replace({ path: "/login" });
						}
					});
				} else {
					this.$message.error(data.msg);
				}
			});
		}
	}
};
</script>

<style lang="scss" scoped>
	.container {
	  position: relative;
	  background: url(http://codegen.caihongy.cn/20230830/25e4efeaf5f14216b2e7e70a43d1c06e.jpg);

		.el-date-editor.el-input {
		  width: 100%;
		}
		
		.rgs-form .el-input /deep/ .el-input__inner {
						border-radius: 0px;
						padding: 0 10px;
						box-shadow: 0 0 0px rgba(64, 158, 255, .3);
						outline: none;
						color: inherit;
						background: none;
						width: calc(100% - 0px);
						font-size: 14px;
						border-color: #e0e0e0;
						border-width: 0 0 2px;
						border-style: solid;
						height: 40px;
					}
		
		.rgs-form .el-select /deep/ .el-input__inner {
						border-radius: 0px;
						padding: 0 10px;
						box-shadow: 0 0 0px rgba(64, 158, 255, .3);
						outline: none;
						color: inherit;
						background: none;
						width: 100%;
						font-size: 14px;
						border-color: #e0e0e0;
						border-width: 0 0 2px;
						border-style: solid;
						height: 40px;
					}
		
		.rgs-form .el-date-editor /deep/ .el-input__inner {
						border-radius: 0px;
						padding: 0 10px 0 30px;
						box-shadow: 0 0 0px rgba(64, 158, 255, .3);
						outline: none;
						color: inherit;
						background: none;
						width: 288px;
						font-size: 14px;
						border-color: #e0e0e0;
						border-width: 0 0 2px;
						border-style: solid;
						height: 40px;
					}
		
		.rgs-form .el-date-editor /deep/ .el-input__inner {
						border-radius: 0px;
						padding: 0 10px 0 30px;
						box-shadow: 0 0 0px rgba(64, 158, 255, .3);
						outline: none;
						color: inherit;
						background: none;
						width: 288px;
						font-size: 14px;
						border-color: #e0e0e0;
						border-width: 0 0 2px;
						border-style: solid;
						height: 40px;
					}
		
		.rgs-form /deep/ .el-upload--picture-card {
			background: transparent;
			border: 0;
			border-radius: 0;
			width: auto;
			height: auto;
			line-height: initial;
			vertical-align: middle;
		}
		
		.rgs-form /deep/ .upload .upload-img {
		  		  border: 2px solid #e0e0e0;
		  		  cursor: pointer;
		  		  border-radius: 8px;
		  		  color: inherit;
		  		  width: 160px;
		  		  font-size: 32px;
		  		  line-height: 80px;
		  		  text-align: center;
		  		  height: 80px;
		  		}
		
		.rgs-form /deep/ .el-upload-list .el-upload-list__item {
		  		  border: 2px solid #e0e0e0;
		  		  cursor: pointer;
		  		  border-radius: 8px;
		  		  color: inherit;
		  		  width: 160px;
		  		  font-size: 32px;
		  		  line-height: 80px;
		  		  text-align: center;
		  		  height: 80px;
		  		}
		
		.rgs-form /deep/ .el-upload .el-icon-plus {
		  		  border: 2px solid #e0e0e0;
		  		  cursor: pointer;
		  		  border-radius: 8px;
		  		  color: inherit;
		  		  width: 160px;
		  		  font-size: 32px;
		  		  line-height: 80px;
		  		  text-align: center;
		  		  height: 80px;
		  		}
	}
	.required {
		position: relative;
	}
	.required::after{
				color: red;
				position: absolute;
				right: 5px;
				content: "*";
			}
	.editor>.avatar-uploader {
		line-height: 0;
		height: 0;
	}
</style>
