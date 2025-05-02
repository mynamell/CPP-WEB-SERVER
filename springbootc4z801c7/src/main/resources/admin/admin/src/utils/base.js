const base = {
    get() {
        return {
            url : "http://localhost:8080/springbootc4z801c7/",
            name: "springbootc4z801c7",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/springbootc4z801c7/front/dist/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "基于Spring Boot的茶叶种植管理系统"
        } 
    }
}
export default base
