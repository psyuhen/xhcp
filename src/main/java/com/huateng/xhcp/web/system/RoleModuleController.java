package com.huateng.xhcp.web.system;
 
import java.util.ArrayList;
import java.util.List;
 
import lombok.Getter;
import lombok.Setter;
 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
 
import com.huateng.xhcp.model.ResponseInfo;
import com.huateng.xhcp.model.system.Module;
import com.huateng.xhcp.model.system.RoleModule;
import com.huateng.xhcp.service.system.RoleModuleService;
import com.huateng.xhcp.util.HttpUtil;
 
/**
 * 角色模块管理
 * @author chengz
 * */
@Controller
public class RoleModuleController {
        private static final Log LOGGER =  LogFactory.getLog(RoleModuleController.class);
        private @Autowired @Setter @Getter RoleModuleService roleModuleModuleService;
        
        /**
         * 查询角色模块
         * @param roleModule
         * @return
         */
        @ResponseBody
        @RequestMapping(value = "/mgr/roleModule/queryRoleModule", method = RequestMethod.POST)
        public List<RoleModule> queryRole(RoleModule roleModule){
                return this.roleModuleModuleService.queryRoleModule(roleModule);
        }
        
        /**
         * 更新角色模块
         * @param addRoleModuleList
         *                              新增的模块List
         * @param delRoleModuleList
         *                              删除的模块List
         * @return
         */
        @ResponseBody
        @RequestMapping(value = "/mgr/roleModule/updateRoleModule", method = RequestMethod.POST)
        public ResponseEntity<ResponseInfo> addRoleModule(
                        @RequestBody ArrayList<ArrayList<RoleModule>> roleModuleList){
                try{
                        this.roleModuleModuleService.updateRoleModule(roleModuleList);
                }catch(Exception e){
                        LOGGER.error(e.getMessage(), e);
                        return HttpUtil.failure("更新角色模块失败!");
                }
                
                return HttpUtil.success("更新角色模块成功!");
        }
        
        /**
         * 只查询用户可支配的菜单
         * @param account_id
         *                              用户
         * @return
         */
        @ResponseBody
        @RequestMapping(value = "/mgr/roleModule/findModuleByAccount", method = RequestMethod.POST)
        public List<Module> findModuleByAccount(String account_id){
                return this.roleModuleModuleService.findModuleByAccount(account_id);
        }
}